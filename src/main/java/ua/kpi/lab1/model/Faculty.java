package ua.kpi.lab1.model;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class Faculty {
    @Id
    @GeneratedValue
    private int id;
    private String name;
    @ManyToOne
    private Institute institute;
    @OneToMany(mappedBy = "faculty", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Student> studentList;

    public Faculty(String name) {
        this.name = name;
        this.studentList = new ArrayList<>();
    }

    public Faculty(String name, List<Student> studentList) {
        this.name = name;
        this.studentList = studentList;
    }

    public Faculty() {

    }

}
