package com.alejandroLopez.repository;

import com.alejandroLopez.model.Inspection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
public interface InspectionRepository extends JpaRepository<Inspection, Integer> {
    // Aquí puedes agregar consultas personalizadas si es necesario
    List<Inspection> findByWorkerId(Long workerId);


    // Consulta para obtener la inspección, el trabajador, el equipo y la empresa relacionados con la inspección por su ID
    @Query(value = """
    SELECT 
        i.id AS inspection_id,
        i.date AS inspection_date,
        i.completed AS inspection_completed,
        i.description AS inspection_description,

        -- Datos del trabajador
        w.id AS worker_id,
        w.name AS worker_name,
        w.surname AS worker_surname,
        w.username AS worker_username,
        w.category AS worker_category,

        -- Datos del equipo
        e.id AS equipment_id,
        e.serial_number AS equipment_serial,
        e.type AS equipment_type,
        e.brand AS equipment_brand,
        e.installation_date AS equipment_installation_date,
        e.description AS equipment_description,

        -- Datos de la empresa
        c.id AS company_id,
        c.name AS company_name,
        c.address AS company_address,
        c.association_date AS company_association_date

    FROM inspection i
    JOIN worker w ON i.worker_id = w.id
    JOIN equipment e ON i.equipment_id = e.id
    JOIN company c ON e.company_id = c.id
    WHERE i.id = :inspectionId
""", nativeQuery = true)
    Map<String, Object> findInspectionDetailsById(@Param("inspectionId") Integer inspectionId);

}