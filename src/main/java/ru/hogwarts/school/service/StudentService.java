package ru.hogwarts.school.service;

import org.springframework.stereotype.Service;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.repository.StudentRepository;

import java.util.Collection;

@Service
public class StudentService {

    private final StudentRepository studentRepository;

    private final AvatarService avatarService;

    public StudentService(StudentRepository studentRepository, AvatarService avatarService) {
        this.studentRepository = studentRepository;
        this.avatarService = avatarService;
    }


    public Student getStudentById(Long id) {
        return studentRepository.findById(id).get();
    }

    public Student addStudent(Student student) {
        student.setId(0L);
        return studentRepository.save(student);
    }

    public Student editStudent(Student student) {
        return studentRepository.save(student);
    }

    public void deleteStudent(Long id) {
        avatarService.deleteAvatarByStudentId(id);
        studentRepository.deleteById(id);
    }

    public Collection<Student> getAllStudentsByAge(Long age) {
        return studentRepository.findByAge(age);
    }

    public Collection<Student> findByAgeBetween(Long min, Long max) {
        return studentRepository.findByAgeBetween(min, max);
    }

    public Collection<Student> findAllStudents() {
        return studentRepository.findAll();
    }

    public Faculty getStudentsFacultyByStudentsId(Long id) {
        return studentRepository.findById(id).get().getFaculty();
    }

    public Long getStudentsCount() {
        return studentRepository.getStudentsCount();
    }

    public Long getStudentsAgeAvg() {
        return studentRepository.getStudentsAgeAvg();
    }

    public Collection<Student> getLastFiveStudents() {
        return studentRepository.getLastFiveStudents();
    }
}
