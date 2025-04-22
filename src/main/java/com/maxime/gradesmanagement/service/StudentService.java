package com.maxime.gradesmanagement.service;


import com.maxime.gradesmanagement.dto.StudentDto;
import com.maxime.gradesmanagement.entity.Student;
import com.maxime.gradesmanagement.exception.GlobalExceptionHandler;
import com.maxime.gradesmanagement.repository.StudentRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
@Transactional
public class StudentService {

    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public Student createStudent(StudentDto studentDto) {
        Student student = new Student();
        student.setFirstName(studentDto.getFirstName());
        student.setLastName(studentDto.getLastName());
        student.setEmail(studentDto.getEmail());
        return studentRepository.save(student);
    }

    @Transactional(readOnly = true)
    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Student getStudentById(Long id) {
        return studentRepository.findById(id)
                .orElseThrow(() -> new GlobalExceptionHandler.ResourceNotFoundException("Étudiant non trouvé avec l'ID: " + id));
    }

    public Student updateStudent(Long id, StudentDto studentDto) {
        Student student = getStudentById(id);
        student.setFirstName(studentDto.getFirstName());
        student.setLastName(studentDto.getLastName());
        student.setEmail(studentDto.getEmail());
        return studentRepository.save(student);
    }

    public void deleteStudent(Long id) {
        if (!studentRepository.existsById(id)) {
            throw new GlobalExceptionHandler.ResourceNotFoundException("Étudiant non trouvé avec l'ID: " + id);
        }
        studentRepository.deleteById(id);
    }
}