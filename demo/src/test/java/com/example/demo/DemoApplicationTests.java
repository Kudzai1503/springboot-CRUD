package com.example.demo;

import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class DemoApplicationTests {


//    @Autowired
//    private StudentService studentService;
//    @MockBean
//    private StudentRepository studentRepository;
//
//    @Test
//    void contextLoads() {
//    }
//
//    @Test
//    public void getStudentsTest() {
//        when(studentRepository.findAll()).thenReturn(Stream.of(new Student("clifford", "cliff@gmail.com", LocalDate.of(2001, Month.JUNE, 22))).collect(Collectors.toList()));
//        Assertions.assertEquals(1, studentService.getStudents().size());
//    }


}
