package com.veera.mvc.controllers;

import com.veera.mvc.models.Student;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

@Controller
public class StudentController
{
    List<Student> studentList = new ArrayList<>();
    Student s1=new Student(1,"John","john@gmail.com","III","Male", LocalDate.of(2015,8,1));
    Student s2=new Student(2,"Richard","richard@gmail.com","IV","Male", LocalDate.of(2014,3,7));
    Student s3=new Student(3,"Arun","arun@gmail.com","VIII","Male", LocalDate.of(2010,1,4));
    Student s4=new Student(4,"Mark","mark@gmail.com","X","Male", LocalDate.of(2008,4,9));
    Student s5=new Student(5,"Varun","john@gmail.com","V","Male", LocalDate.of(2013,7,2));



    @GetMapping("/")
    public String redirect()
    {
        if(studentList.isEmpty())
        {
            studentList.add(s1);
            studentList.add(s2);
            studentList.add(s3);
            studentList.add(s4);
            studentList.add(s5);
        }
        return "redirect:/student-home";
    }

    @GetMapping("/student-form")
    public String homePage(Model model)
    {
        //Student s=new Student(1,124,"John","john@gmail.com","IV","Male", LocalDate.of(2016,4,2));
        Student s = new Student();
        model.addAttribute("student", s);
        return "student-form";
    }

    @GetMapping("/student-home")
    public String studentHome()
    {
        return "student-home";
    }

    @PostMapping("/save-student")
    public String processStudent(@Valid @ModelAttribute Student s, BindingResult bindingResult)
    {
        if(bindingResult.hasErrors())
        {
            System.out.println(bindingResult.getFieldError());
            return "student-form";
        }
        else
        {
            if(s.getId()!=null)
            {
                for(Student student:studentList)
                {
                    if(student.getId().equals(s.getId()))
                    {
                        student.setRollNo(s.getRollNo());
                        student.setName(s.getName());
                        student.setDob(s.getDob());
                        student.setEmail(s.getEmail());
                        student.setGrade(s.getGrade());
                        student.setGender(s.getGender());
                    }
                }
            }
            else
            {
               s.setId(UUID.randomUUID());
                studentList.add(s);
            }
            return "redirect:/student-list";
        }
    }

    @GetMapping("/student-list")
    public String studentList(Model model)
    {
        model.addAttribute("employees",studentList);
        return "student-list";
    }

    @GetMapping("/view-student")
    public String viewStudent(@RequestParam UUID id, Model model)
    {
        Student s=null;
        for(Student student:studentList)
        {
            if(student.getId().equals(id))
            {
               s=student;
            }
        }
        model.addAttribute("student",s);
        return "student-form";
    }

    @GetMapping("/delete-student")
    public String deleteStudent(@RequestParam UUID id)
    {
        Student s=null;
        for(Student student:studentList)
        {
            if(student.getId().equals(id))
            {
                s=student;
            }
        }
        studentList.remove(s);
        return "redirect:/student-list";
    }

}
