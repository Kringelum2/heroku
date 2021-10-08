package com.example.heroku.models;

import lombok.Data;

import javax.persistence.*;

@Data
@Table(name = "persons")
@Entity
public class Person {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column
    private Long id;

    @Column
    private String name;

    @Column
    private int age;

    @Column
    private String gender;

}
