package com.alejandroLopez.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.internal.build.AllowNonPortable;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name = "cuestionarioSalud")
@Getter
@Setter
@AllowNonPortable
@Builder
public class CuestionarioSalud {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String nombre;
    private LocalDate fecha;
    LocalTime hSueno;
    LocalTime hDespierta;
    private Integer calidad;
    private Integer nivelEstres;
    private Integer fatiga;
    private Integer dolorMuscular;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idJugador")
    @JsonBackReference
    private Jugador jugador;

    public CuestionarioSalud(Integer id, String nombre, LocalDate fecha, LocalTime hSueno, LocalTime hDespierta, Integer calidad, Integer nivelEstres, Integer fatiga, Integer dolorMuscular, Jugador jugador) {
        this.id = id;
        this.nombre = nombre;
        this.fecha = fecha;
        this.hSueno = hSueno;
        this.hDespierta = hDespierta;
        this.calidad = calidad;
        this.nivelEstres = nivelEstres;
        this.fatiga = fatiga;
        this.dolorMuscular = dolorMuscular;
        this.jugador = jugador;
    }

    public CuestionarioSalud() {
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

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public LocalTime gethSueno() {
        return hSueno;
    }

    public void sethSueno(LocalTime hSueno) {
        this.hSueno = hSueno;
    }

    public LocalTime gethDespierta() {
        return hDespierta;
    }

    public void sethDespierta(LocalTime hDespierta) {
        this.hDespierta = hDespierta;
    }

    public Integer getCalidad() {
        return calidad;
    }

    public void setCalidad(Integer calidad) {
        this.calidad = calidad;
    }

    public Integer getNivelEstres() {
        return nivelEstres;
    }

    public void setNivelEstres(Integer nivelEstres) {
        this.nivelEstres = nivelEstres;
    }

    public Integer getFatiga() {
        return fatiga;
    }

    public void setFatiga(Integer fatiga) {
        this.fatiga = fatiga;
    }

    public Integer getDolorMuscular() {
        return dolorMuscular;
    }

    public void setDolorMuscular(Integer dolorMuscular) {
        this.dolorMuscular = dolorMuscular;
    }

    public Jugador getJugador() {
        return jugador;
    }

    public void setJugador(Jugador jugador) {
        this.jugador = jugador;
    }

    @Override
    public String toString() {
        return "CuestionarioSalud{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", fecha=" + fecha +
                ", hSueno=" + hSueno +
                ", hDespierta=" + hDespierta +
                ", calidad=" + calidad +
                ", nivelEstres=" + nivelEstres +
                ", fatiga=" + fatiga +
                ", dolorMuscular=" + dolorMuscular +
                ", jugador=" + jugador +
                '}';
    }
}

