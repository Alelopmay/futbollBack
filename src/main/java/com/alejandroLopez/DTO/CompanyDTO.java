package com.alejandroLopez.DTO;

import java.util.Date;

public class CompanyDTO {
    private Integer id;
    private String name;  // Campo añadido para el nombre de la empresa
    private String address;

    private Date associationDate;

    // Constructor vacío
    public CompanyDTO() {}

    // Constructor completo
    public CompanyDTO(Integer id, String name, String address, Date associationDate) {
        this.id = id;
        this.name = name;  // Inicialización del nombre de la empresa
        this.address = address;

        this.associationDate = associationDate;
    }

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

    @Override
    public String toString() {
        return "CompanyDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +  // Mostrar nombre de la empresa en el toString
                ", address='" + address + '\'' +
                ", associationDate=" + associationDate +
                '}';
    }
}
