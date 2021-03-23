package ua.kpi.lab1.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Institute {
    @Id
    @GeneratedValue
    private int id;
    private String name;
    @OneToMany
    private List<Faculty> facultyList;

    public Institute(String name) {
        this(name, new ArrayList<>());
    }

    public Institute(String name, List<Faculty> facultyList) {
        this.name = name;
        this.facultyList = facultyList;
    }

    public Institute() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setFacultyList(List<Faculty> facultyList) {
        this.facultyList = facultyList;
    }

    public void addFaculty(Faculty faculty) {
        facultyList.add(faculty);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Faculty> getFacultyList() {
        return facultyList;
    }
}
