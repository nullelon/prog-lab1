package ua.kpi.lab1.repository;

import org.springframework.data.repository.CrudRepository;
import ua.kpi.lab1.model.Faculty;

public interface FacultyRepository extends CrudRepository<Faculty, Integer> {
}
