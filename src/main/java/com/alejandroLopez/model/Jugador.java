package com.alejandroLopez.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.internal.build.AllowNonPortable;

import java.time.LocalDate;

@Entity
@Table(name="jugador")

@AllowNonPortable
@Getter
@Setter
@Builder
public class Jugador {
@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Integer id;
    @Column(nullable = false,length = 50)
    private  String nombre;
    @Column(nullable = false,length = 100)
    private String apellido;
    private LocalDate fechaNacimiento;
    private  Integer edad;
    @Column (length = 30)
    private String posicion;
    private Double disponibilidad;
    @Column(length = 255)
    private  String foto;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="idEquipo")
    @JsonBackReference
    private Equipo equipo;

    public Jugador(Integer id, String nombre, String apellido, LocalDate fechaNacimiento, Integer edad, String posicion, Double disponibilidad, String foto, Equipo equipo) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.fechaNacimiento = fechaNacimiento;
        this.edad = edad;
        this.posicion = posicion;
        this.disponibilidad = disponibilidad;
        this.foto = foto;
        this.equipo = equipo;
    }

    public Jugador() {

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public LocalDate getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(LocalDate fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public Integer getEdad() {
        return edad;
    }

    public void setEdad(Integer edad) {
        this.edad = edad;
    }

    public String getPosicion() {
        return posicion;
    }

    public void setPosicion(String posicion) {
        this.posicion = posicion;
    }

    public Double getDisponibilidad() {
        return disponibilidad;
    }

    public void setDisponibilidad(Double disponibilidad) {
        this.disponibilidad = disponibilidad;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public Equipo getEquipo() {
        return equipo;
    }

    public void setEquipo(Equipo equipo) {
        this.equipo = equipo;
    }

    @Override
    public String toString() {
        return "Jugador{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", apellido='" + apellido + '\'' +
                ", fechaNacimiento=" + fechaNacimiento +
                ", edad=" + edad +
                ", posicion='" + posicion + '\'' +
                ", disponibilidad=" + disponibilidad +
                ", foto='" + foto + '\'' +
                ", equipo=" + equipo +
                '}';
    }
}
