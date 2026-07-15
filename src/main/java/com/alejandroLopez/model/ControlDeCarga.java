package com.alejandroLopez.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.internal.build.AllowNonPortable;

import java.time.LocalDate;

@Entity
@Table(name="controlDeCarga")
@AllowNonPortable
@Getter
@Setter
@Builder
public class ControlDeCarga {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String semana;

    private Double lunes;

    private Double martes;

    private Double miercoles;

    private Double jueves;

    private Double viernes;

    private Double sabado;

    private Double domingo;

    private Double cargaAguda;

    private Double cargaCronica;

    private Double riesgoLesion;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idJugador")
    @JsonBackReference
    private Jugador jugador;

    public ControlDeCarga(Integer id, String semana, Double lunes, Double martes, Double miercoles, Double jueves, Double viernes, Double sabado, Double domingo, Double cargaAguda, Double cargaCronica, Double riesgoLesion, Jugador jugador) {
        this.id = id;
        this.semana = semana;
        this.lunes = lunes;
        this.martes = martes;
        this.miercoles = miercoles;
        this.jueves = jueves;
        this.viernes = viernes;
        this.sabado = sabado;
        this.domingo = domingo;
        this.cargaAguda = cargaAguda;
        this.cargaCronica = cargaCronica;
        this.riesgoLesion = riesgoLesion;
        this.jugador = jugador;
    }

    public ControlDeCarga() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSemana() {
        return semana;
    }

    public void setSemana(String semana) {
        this.semana = semana;
    }

    public Double getLunes() {
        return lunes;
    }

    public void setLunes(Double lunes) {
        this.lunes = lunes;
    }

    public Double getMartes() {
        return martes;
    }

    public void setMartes(Double martes) {
        this.martes = martes;
    }

    public Double getMiercoles() {
        return miercoles;
    }

    public void setMiercoles(Double miercoles) {
        this.miercoles = miercoles;
    }

    public Double getJueves() {
        return jueves;
    }

    public void setJueves(Double jueves) {
        this.jueves = jueves;
    }

    public Double getViernes() {
        return viernes;
    }

    public void setViernes(Double viernes) {
        this.viernes = viernes;
    }

    public Double getSabado() {
        return sabado;
    }

    public void setSabado(Double sabado) {
        this.sabado = sabado;
    }

    public Double getDomingo() {
        return domingo;
    }

    public void setDomingo(Double domingo) {
        this.domingo = domingo;
    }

    public Double getCargaAguda() {
        return cargaAguda;
    }

    public void setCargaAguda(Double cargaAguda) {
        this.cargaAguda = cargaAguda;
    }

    public Double getCargaCronica() {
        return cargaCronica;
    }

    public void setCargaCronica(Double cargaCronica) {
        this.cargaCronica = cargaCronica;
    }

    public Double getRiesgoLesion() {
        return riesgoLesion;
    }

    public void setRiesgoLesion(Double riesgoLesion) {
        this.riesgoLesion = riesgoLesion;
    }

    public Jugador getJugador() {
        return jugador;
    }

    public void setJugador(Jugador jugador) {
        this.jugador = jugador;
    }

    @Override
    public String toString() {
        return "ControlDeCarga{" +
                "id=" + id +
                ", semana='" + semana + '\'' +
                ", lunes=" + lunes +
                ", martes=" + martes +
                ", miercoles=" + miercoles +
                ", jueves=" + jueves +
                ", viernes=" + viernes +
                ", sabado=" + sabado +
                ", domingo=" + domingo +
                ", cargaAguda=" + cargaAguda +
                ", cargaCronica=" + cargaCronica +
                ", riesgoLesion=" + riesgoLesion +
                ", jugador=" + jugador +
                '}';
    }
}