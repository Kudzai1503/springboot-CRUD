package com.example.demo;

import com.example.demo.student.Student;
import com.example.demo.student.StudentRepository;
import com.example.demo.student.StudentService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDate;
import java.time.Month;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class DemoApplicationTests {


    @Autowired
    private StudentService studentService;
    @MockBean
    private StudentRepository studentRepository;

    @Test
    void contextLoads() {
    }

    @Test
    public void getStudentsTest() {
        when(studentRepository.findAll()).thenReturn(Stream.of(new Student("clifford", "cliff@gmail.com", LocalDate.of(2001, Month.JUNE, 22))).collect(Collectors.toList()));
        Assertions.assertEquals(1, studentService.getStudents().size());
    }


}
