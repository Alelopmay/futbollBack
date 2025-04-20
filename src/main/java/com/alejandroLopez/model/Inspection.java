package com.alejandroLopez.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Entity
@Data
public class Inspection {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private Date date;

    @Column(nullable = false)
    private Boolean completed;

    @Column(nullable = false, length = 500)
    private String description;

    @ManyToOne
    @JoinColumn(name = "worker_id", nullable = false)
    @JsonIgnore // Evita referencia cíclica
    private Worker worker;

    @ManyToOne
    @JoinColumn(name = "equipment_id", nullable = false)
    @JsonIgnore // Evita referencia cíclica
    private Equipment equipment;

    public Inspection(Integer id, Date date, Boolean completed, String description, Worker worker, Equipment equipment) {
        this.id = id;
        this.date = date;
        this.completed = completed;
        this.description = description;
        this.worker = worker;
        this.equipment = equipment;
    }

    public Inspection() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Boolean getCompleted() {
        return completed;
    }

    public void setCompleted(Boolean completed) {
        this.completed = completed;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Worker getWorker() {
        return worker;
    }

    public void setWorker(Worker worker) {
        this.worker = worker;
    }

    public Equipment getEquipment() {
        return equipment;
    }

    public void setEquipment(Equipment equipment) {
        this.equipment = equipment;
    }

    @Override
    public String toString() {
        return "Inspection{" +
                "id=" + id +
                ", date=" + date +
                ", completed=" + completed +
                ", description='" + description + '\'' +
                ", worker=" + worker +
                ", equipment=" + equipment +
                '}';
    }
}
