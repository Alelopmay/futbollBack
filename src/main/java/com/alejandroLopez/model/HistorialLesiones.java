package com.alejandroLopez.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.internal.build.AllowNonPortable;
import org.springframework.cglib.core.Local;

import java.time.LocalDate;

@Entity
@Table(name = "historialLesiones")
@Getter
@Setter
@AllowNonPortable
@Builder
public class HistorialLesiones {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private LocalDate fecha;
    private String duracion;
    private String musculo;
    @Column(columnDefinition = "TEXT")
    private String lesion;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idJugador")
    @JsonBackReference
    private Jugador jugador;

    public HistorialLesiones(Integer id, LocalDate fecha, String duracion, String musculo, String lesion, Jugador jugador) {
        this.id = id;
        this.fecha = fecha;
        this.duracion = duracion;
        this.musculo = musculo;
        this.lesion = lesion;
        this.jugador = jugador;
    }

    public HistorialLesiones() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public String getDuracion() {
        return duracion;
    }

    public void setDuracion(String duracion) {
        this.duracion = duracion;
    }

    public String getMusculo() {
        return musculo;
    }

    public void setMusculo(String musculo) {
        this.musculo = musculo;
    }

    public String getLesion() {
        return lesion;
    }

    public void setLesion(String lesion) {
        this.lesion = lesion;
    }

    public Jugador getJugador() {
        return jugador;
    }

    public void setJugador(Jugador jugador) {
        this.jugador = jugador;
    }

    @Override
    public String toString() {
        return "HistorialLesiones{" +
                "id=" + id +
                ", fecha=" + fecha +
                ", duracion='" + duracion + '\'' +
                ", musculo='" + musculo + '\'' +
                ", lesion='" + lesion + '\'' +
                ", jugador=" + jugador +
                '}';
    }
}
