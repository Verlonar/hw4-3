package ru.hogwarts.school.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.service.StudentService;

import java.util.Collection;

@RestController
@RequestMapping("/student")
public class StudentController {

    final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping("{id}")
    public ResponseEntity<Student> getStudentById(@PathVariable Long id) {
        Student student = studentService.getStudentById(id);
        if (student == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(student);
    }

    @GetMapping
    public ResponseEntity<Collection<Student>> getAllStudentsByAge(@RequestParam(required = false) Long age,
                                                                   @RequestParam(required = false) Long min,
                                                                   @RequestParam(required = false) Long max) {
        if (age != null && age > 0) {
            return ResponseEntity.ok(studentService.getAllStudentsByAge(age));
        }
        if (min != null && max != null) {
            return ResponseEntity.ok(studentService.findByAgeBetween(min, max));
        }
        return ResponseEntity.ok(studentService.findAllStudents());
    }

    @GetMapping("/students-count")
    public Long getStudentsCount() {
        return studentService.getStudentsCount();
    }

    @GetMapping("/students-age-avg")
    public Long getStudentsAgeAvg() {
        return studentService.getStudentsAgeAvg();
    }

    @GetMapping("/get-last-five-students")
    public ResponseEntity<Collection<Student>> getLastFiveStudents() {
        return ResponseEntity.ok(studentService.getLastFiveStudents());
    }

    @PostMapping
    public Student addStudent(@RequestBody Student student) {
        return studentService.addStudent(student);
    }

    @PutMapping
    public ResponseEntity<Student> editStudent(@RequestBody Student student) {
        Student foundStudent = studentService.editStudent(student);
        if (foundStudent == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        return ResponseEntity.ok(foundStudent);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Student> deleteStudent(@PathVariable Long id) {
        studentService.deleteStudent(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("{id}/faculty")
    public Faculty getStudentsFacultyByStudentsId(@PathVariable Long id) {
        return studentService.getStudentsFacultyByStudentsId(id);
    }
}
