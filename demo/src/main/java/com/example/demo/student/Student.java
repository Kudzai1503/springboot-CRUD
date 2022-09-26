package com.example.demo.student;

import com.opencsv.bean.CsvBindByName;

import javax.persistence.*;
import java.time.LocalDate;


@Entity
@Table(name = "student")
public class Student {
    @Id
    @SequenceGenerator(
            name = "student_sequence",
            sequenceName = "student_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "student_sequence"
    )
    @Column(name = "id")
    @CsvBindByName
    private Long id;
    @Column(name = "name")
    @CsvBindByName
    private String name;
    @Column(name = "email")
    @CsvBindByName
    private String email;
//    @Column(name = "dob")
//    @CsvBindByName
//    @CsvDate("yyyy-MM-dd")
//    private LocalDate dob;
//
//    @Transient
//    @CsvBindByName(column = "Age")
//    private Integer age;

    public Student() {
    }

    public Student(Long id, String name, String email, LocalDate dob) {
        this.id = id;
        this.name = name;
        this.email = email;
//        this.dob = dob;
    }

    public Student(String name, String email) {
        this.name = name;
        this.email = email;
//        this.dob = dob;

    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

//    public LocalDate getDob() {
//        return dob;
//    }
//
//    public void setDob(LocalDate dob) {
//        this.dob = dob;
//    }
//
//    public Integer getAge() {
//        return Period.between(this.dob, LocalDate.now()).getYears();
//    }
//
//    public void setAge(Integer age) {
//        this.age = age;
//    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
//                ", dob=" + dob +
//                ", age=" + age +
                '}';
    }

//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (!(o instanceof Student)) return false;
//        Student student = (Student) o;
//        return Objects.equals(getId(), student.getId()) && Objects.equals(getName(), student.getName()) && Objects.equals(getEmail(), student.getEmail()) && Objects.equals(getDob(), student.getDob()) && Objects.equals(getAge(), student.getAge());
//    }

//    @Override
//    public int hashCode() {
//        return Objects.hash(getId(), getName(), getEmail(), getDob(), getAge());
//    }
}
