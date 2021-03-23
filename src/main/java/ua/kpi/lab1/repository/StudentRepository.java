package ua.kpi.lab1.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ua.kpi.lab1.model.Student;

@Repository
public interface StudentRepository extends CrudRepository<Student, Integer> {

}
