package com.alejandroLopez.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.internal.build.AllowNonPortable;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@AllowNonPortable
@Table(name="equipo")
@Builder
public class Equipo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(nullable = false)
    private String nombre;
    private  Integer jugadores;
    @OneToMany(mappedBy = "equipo",cascade =CascadeType.ALL,orphanRemoval = true,fetch = FetchType.EAGER)
    @JsonManagedReference
    @Builder.Default
    private List<Jugador>listJugador=new ArrayList<>();

    public Equipo(Integer id, String nombre, Integer jugadores, List<Jugador> listJugador) {
        this.id = id;
        this.nombre = nombre;
        this.jugadores = jugadores;
        this.listJugador = listJugador;
    }

    public Equipo() {
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

    public Integer getJugadores() {
        return jugadores;
    }

    public void setJugadores(Integer jugadores) {
        this.jugadores = jugadores;
    }

    public List<Jugador> getListJugador() {
        return listJugador;
    }

    public void setListJugador(List<Jugador> listJugador) {
        this.listJugador = listJugador;
    }

    @Override
    public String toString() {
        return "Equipo{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", jugadores=" + jugadores +
                ", listJugador=" + listJugador +
                '}';
    }
}
