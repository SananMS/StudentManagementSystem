package com.example.controllers;

import com.example.models.Student;
import com.example.repositories.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class StudentController {

    @Autowired
    private StudentRepository studentRepository;

    @GetMapping("/")
    public String index(Model model) {
        List<Student> students = studentRepository.findAll();
        model.addAttribute("students", students);
        return "home";
    }

    @GetMapping("/add")
    public String addStudentForm(Model model) {
        model.addAttribute("student", new Student());
        return "add_student";
    }

    @PostMapping("/add")
    public String addStudentSubmit(@ModelAttribute Student student) {
        studentRepository.save(student);
        return "redirect:/";
    }

    @GetMapping("/edit/{id}")
    public String editStudentForm(@PathVariable Integer id, Model model) {
        Student student = studentRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid student id: " + id));
        model.addAttribute("student", student);
        return "edit_student";
    }

    @PostMapping("/edit/{id}")
    public String editStudentSubmit(@PathVariable Integer id, @ModelAttribute Student student) {
        Student existingStudent = studentRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid student id: " + id));
        existingStudent.setFirst_name(student.getFirst_name());
        existingStudent.setLast_name(student.getLast_name());
        existingStudent.setEmail(student.getEmail());
        existingStudent.setPhone(student.getPhone());
        existingStudent.setAddress(student.getAddress());
        existingStudent.setMajor(student.getMajor());
        existingStudent.setEnrollment_date(student.getEnrollment_date());
        studentRepository.save(existingStudent);
        return "redirect:/";
    }

    @GetMapping("/delete/{id}")
    public String deleteStudent(@PathVariable Integer id) {
        Student student = studentRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid student id: " + id));
        studentRepository.delete(student);
        return "redirect:/";
    }
}
