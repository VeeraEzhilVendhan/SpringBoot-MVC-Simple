package com.veera.mvc.models;

import jakarta.validation.constraints.*;
import org.hibernate.validator.cfg.defs.UUIDDef;

import java.time.LocalDate;
import java.util.Date;
import java.util.UUID;

public class Student
{

    private UUID id;

    @NotNull
    @Min(value=1, message = "roll no starts with 1")
    @Max(value=10, message= "last roll no is 10")
    private Integer rollNo;

    @Size(min=1, message="name is required")
    private String name;

    private String email;

    private String grade;

    @NotEmpty
    private String gender;

    private LocalDate dob;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
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

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public LocalDate getDob() {
        return dob;
    }

    public void setDob(LocalDate dob) {
        this.dob = dob;
    }

    public Student() {

    }

    public Integer getRollNo() {
        return rollNo;
    }

    public void setRollNo(Integer rollNo) {
        this.rollNo = rollNo;
    }

    public Student(Integer rollNo, String name, String email, String grade, String gender, LocalDate dob) {
        this.id = UUID.randomUUID();
        this.rollNo = rollNo;
        this.name = name;
        this.email = email;
        this.grade = grade;
        this.gender = gender;
        this.dob = dob;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", rollNo=" + rollNo +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", grade='" + grade + '\'' +
                ", gender='" + gender + '\'' +
                ", dob=" + dob +
                '}';
    }
}
