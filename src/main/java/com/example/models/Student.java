package com.example.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Entity
@Table(name = "students")
@Getter
@Setter
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private int id;
    @Column
    private String first_name;
    @Column
    private String last_name;
    @Column
    private String email;
    @Column
    private String phone;
    @Column
    private String address;
    @Column
    private String major;
    @Column
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date enrollment_date;
}
