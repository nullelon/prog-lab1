package ua.kpi.lab1.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ua.kpi.lab1.model.Faculty;
import ua.kpi.lab1.model.Student;
import ua.kpi.lab1.repository.FacultyRepository;
import ua.kpi.lab1.repository.StudentRepository;

@Controller
public class InstituteController {

    @Autowired
    private StudentRepository studentRepo;

    @Autowired
    private FacultyRepository facultyRepo;


    @GetMapping("/faculties")
    public String institute(Model model) {
        model.addAttribute("faculties", facultyRepo.findAll());
        return "faculties";
    }

    @PostMapping("/faculties")
    public String createFaculty(@RequestParam String name) {
        facultyRepo.save(new Faculty(name));
        return "redirect:/faculties";
    }

    @GetMapping("/faculties/{facultyId}")
    public String faculty(Model model, @PathVariable int facultyId) {
        Faculty faculty = facultyRepo.findById(facultyId).get();
        model.addAttribute("faculty", faculty);
        return "faculty";
    }

    @PostMapping("/faculties/delete/{facultyId}")
    public String deleteFaculty(@PathVariable int facultyId) {
        facultyRepo.deleteById(facultyId);
        return "redirect:/faculties";
    }

    @PostMapping("/faculties/student/delete/{studentId}")
    public String deleteFaculty(@RequestParam int facultyId, @PathVariable int studentId) {
        studentRepo.deleteById(studentId);
        return "redirect:/faculties/" + facultyId;
    }

    @PostMapping("/faculties/{facultyId}")
    public String createStudent(@PathVariable int facultyId, @RequestParam String name,
                                @RequestParam String surname, @RequestParam int averageMark) {
        Faculty faculty = facultyRepo.findById(facultyId).get();
        var student = new Student(name, surname, averageMark);
        student.setFaculty(faculty);
        studentRepo.save(student);
        return "redirect:/faculties/" + facultyId;
    }

    @GetMapping("/faculties/{facultyId}/student/{studentId}")
    public String student(Model model, @PathVariable int facultyId, @PathVariable int studentId) {
        model.addAttribute("student", studentRepo.findById(studentId).get());
        model.addAttribute("faculty", facultyRepo.findById(facultyId).get());
        return "student";
    }
}
