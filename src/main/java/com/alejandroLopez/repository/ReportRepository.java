package com.alejandroLopez.repository;

import com.alejandroLopez.model.Report;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Map;

public interface ReportRepository extends JpaRepository<Report, Integer> {
    // Método para obtener los informes por el ID del equipo
    List<Report> findByEquipmentId(Integer equipmentId);
    // Método personalizado para obtener el informe con detalles de trabajador, equipo y empresa
    @Query(
            value = "SELECT " +
                    "r.id AS report_id, " +
                    "r.title, " +
                    "r.date, " +
                    "r.description, " +
                    "r.maintenance_type, " +
                    "r.start_time, " +
                    "r.end_time, " +
                    "r.duration, " +

                    "w.id AS worker_id, " +
                    "w.name AS worker_name, " +
                    "w.surname AS worker_surname, " +
                    "w.username AS worker_username, " +

                    "e.id AS equipment_id, " +
                    "e.serial_number, " +
                    "e.type AS equipment_type, " +
                    "e.brand, " +
                    "e.installation_date, " +

                    "c.id AS company_id, " +
                    "c.name AS company_name, " +
                    "c.address AS company_address, " +
                    "c.association_date " +

                    "FROM report r " +
                    "JOIN worker w ON r.worker_id = w.id " +
                    "JOIN equipment e ON r.equipment_id = e.id " +
                    "JOIN company c ON e.company_id = c.id " +
                    "WHERE r.id = :reportId",
            nativeQuery = true
    )
    Map<String, Object> findReportDetailsById(@Param("reportId") Integer reportId);

    List<Report> findByWorkerId(Long workerId);
}


