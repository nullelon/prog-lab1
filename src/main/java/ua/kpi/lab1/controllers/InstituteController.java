package ua.kpi.lab1.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ua.kpi.lab1.model.Faculty;
import ua.kpi.lab1.model.Institute;
import ua.kpi.lab1.model.Student;
import ua.kpi.lab1.repository.FacultyRepository;
import ua.kpi.lab1.repository.StudentRepository;

import java.util.ArrayList;

@Controller
public class InstituteController {
//    private static final Institute institute = new Institute("KPI");

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

    @PostMapping("/faculties/{facultyId}")
    public String createStudent(@PathVariable int facultyId, @RequestParam String name,
                                @RequestParam String surname, @RequestParam int averageMark) {
        Faculty faculty = facultyRepo.findById(facultyId).get();
        var student = new Student(name, surname, averageMark);
        student.setFaculty(faculty);
        student = studentRepo.save(student);
        System.out.println(student.getFaculty());
        student = studentRepo.findById(student.getId()).get();
        System.out.println(student.getFaculty());
        return "redirect:/faculties/"+facultyId;
    }

    @GetMapping("/faculties/{facultyId}/student/{studentId}")
    public String student(Model model, @PathVariable int facultyId, @PathVariable int studentId) {
        model.addAttribute("student", studentRepo.findById(studentId).get());
        model.addAttribute("faculty", facultyRepo.findById(facultyId).get());
        return "student";
    }
}
