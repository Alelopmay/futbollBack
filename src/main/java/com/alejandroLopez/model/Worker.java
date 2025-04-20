package com.alejandroLopez.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import java.util.List;

@Entity
@Data
public class Worker {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, length = 100)
    private String name;

    @Column(nullable = false, length = 100)
    private String surname;

    @Column(nullable = false, length = 255)
    private String password;

    @Column(nullable = false, unique = true, length = 100)
    private String username;

    @Column(nullable = false, length = 50)
    private String category;

    @OneToMany(mappedBy = "worker", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore // Evita serialización infinita
    private List<Report> reports;

    @OneToMany(mappedBy = "worker", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore // Evita serialización infinita
    private List<Inspection> inspections;


    public Worker(Integer id, String name, String surname, String password, String username, String category, List<Report> reports, List<Inspection> inspections) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.password = password;
        this.username = username;
        this.category = category;
        this.reports = reports;
        this.inspections = inspections;
    }

    public Worker() {

    }



    @Override
    public String toString() {
        return "Worker{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", password='" + password + '\'' +
                ", username='" + username + '\'' +
                ", category='" + category + '\'' +
                ", reports=" + reports +
                ", inspections=" + inspections +
                '}';
    }
}