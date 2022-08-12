package ru.hogwarts.school.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.repository.FacultyRepository;

import java.util.Collection;

@Service
public class FacultyService {

    private final Logger logger = LoggerFactory.getLogger(FacultyService.class);

    private final FacultyRepository facultyRepository;

    public FacultyService(FacultyRepository facultyRepository) {
        this.facultyRepository = facultyRepository;
    }

    public Faculty getFacultyById(Long id) {
        logger.info("Вызван метод getFacultyById");
        return facultyRepository.findById(id).get();
    }

    public Faculty addFaculty(Faculty faculty) {
        logger.info("Вызван метод addFaculty");
        faculty.setId(0L);
        return facultyRepository.save(faculty);
    }

    public Faculty editFaculty(Faculty faculty) {
        logger.info("Вызван метод editFaculty");
        return facultyRepository.save(faculty);
    }

    public void deleteFaculty(Long id) {
        logger.info("Вызван метод deleteFaculty");
        facultyRepository.deleteById(id);
    }

    public Collection<Faculty> getAllFacultiesByNameOrColor(String nameOrColor) {
        logger.info("Вызван метод getAllFacultiesByNameOrColor");
        return facultyRepository.getFacultiesByNameIgnoreCaseOrColorIgnoreCase(nameOrColor, nameOrColor);
    }

    public Collection<Faculty> findAllFaculties() {
        logger.info("Вызван метод findAllFaculties");
        return facultyRepository.findAll();
    }

    public Collection<Student> getAllStudentsOnFacultyById(Long id) {
        logger.info("Вызван метод getAllStudentsOnFacultyById");
        return facultyRepository.findById(id).get().getStudents();
    }
}
