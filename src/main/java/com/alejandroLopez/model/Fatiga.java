package com.alejandroLopez.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.internal.build.AllowNonPortable;

import java.sql.Date;
import java.sql.Time;

@Entity
@Table(name = "fatiga")
@Getter
@Setter
@AllowNonPortable
@Builder
public class Fatiga {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Integer velocidad;
    private Date fecha;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idJugador")
    @JsonBackReference
    private Jugador jugador;

    public Fatiga(Integer id, Integer velocidad, Date fecha, Jugador jugador) {
        this.id = id;
        this.velocidad = velocidad;
        this.fecha = fecha;
        this.jugador = jugador;
    }

    public Fatiga() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getVelocidad() {
        return velocidad;
    }

    public void setVelocidad(Integer velocidad) {
        this.velocidad = velocidad;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Jugador getJugador() {
        return jugador;
    }

    public void setJugador(Jugador jugador) {
        this.jugador = jugador;
    }

    @Override
    public String toString() {
        return "Fatiga{" +
                "id=" + id +
                ", velocidad=" + velocidad +
                ", fecha=" + fecha +
                ", jugador=" + jugador +
                '}';
    }
}