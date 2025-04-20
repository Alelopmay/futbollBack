package com.alejandroLopez.DTO;

import java.time.LocalTime;
import java.util.Date;

public class ReportDTO {

    private Date date;
    private String title;
    private String description;
    private String maintenanceType;
    private String duration;
    private LocalTime startTime;
    private LocalTime endTime;
    private Integer workerId;  // El ID del trabajador
    private Integer equipmentId;  // El ID del equipo

    // Constructor
    public ReportDTO(Date date, String title, String description, String maintenanceType,
                     String duration, LocalTime startTime, LocalTime endTime,
                     Integer workerId, Integer equipmentId) {
        this.date = date;
        this.title = title;
        this.description = description;
        this.maintenanceType = maintenanceType;
        this.duration = duration;
        this.startTime = startTime;
        this.endTime = endTime;
        this.workerId = workerId;
        this.equipmentId = equipmentId;
    }

    // Getters y Setters
    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getMaintenanceType() {
        return maintenanceType;
    }

    public void setMaintenanceType(String maintenanceType) {
        this.maintenanceType = maintenanceType;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalTime startTime) {
        this.startTime = startTime;
    }

    public LocalTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalTime endTime) {
        this.endTime = endTime;
    }

    public Integer getWorkerId() {
        return workerId;
    }

    public void setWorkerId(Integer workerId) {
        this.workerId = workerId;
    }

    public Integer getEquipmentId() {
        return equipmentId;
    }

    public void setEquipmentId(Integer equipmentId) {
        this.equipmentId = equipmentId;
    }
}
