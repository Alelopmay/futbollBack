package com.alejandroLopez.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Entity
@Data
public class Company {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, length = 100)
    private String name;

    @Column(nullable = false, length = 100)
    private String address;

    @Column(nullable = false)
    private Date associationDate;

    @OneToMany(mappedBy = "company", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore // Evita serialización infinita
    private List<Equipment> equipments;

    // Constructor completo
    public Company(Integer id, String name, String address, Date associationDate, List<Equipment> equipments) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.associationDate = associationDate;
        this.equipments = equipments;
    }

    // Constructor vacío
    public Company() {}

    // Getters y Setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Date getAssociationDate() {
        return associationDate;
    }

    public void setAssociationDate(Date associationDate) {
        this.associationDate = associationDate;
    }

    public List<Equipment> getEquipments() {
        return equipments;
    }

    public void setEquipments(List<Equipment> equipments) {
        this.equipments = equipments;
    }

    @Override
    public String toString() {
        return "Company{" +
                "id=" + id +
                ", name='" + name + '\'' + // Mostrar nombre de la empresa en el toString
                ", address='" + address + '\'' +
                ", associationDate=" + associationDate +
                ", equipments=" + equipments +
                '}';
    }
}
