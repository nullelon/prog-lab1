package ua.kpi.lab1.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Student {
    @Id
    @GeneratedValue
    private int id;
    private String name;
    private String surname;
    private double averageMark;
    @ManyToOne(fetch = FetchType.LAZY)
    private Faculty faculty;

    public Student(String name, String surname, double averageMark) {
        this.name = name;
        this.surname = surname;
        this.averageMark = averageMark;
    }

    public Student() {

    }
}
