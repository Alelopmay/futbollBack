package com.alejandroLopez.Controller;


import com.alejandroLopez.DTO.InspectionDTO;
import com.alejandroLopez.model.Equipment;
import com.alejandroLopez.model.Inspection;
import com.alejandroLopez.model.Worker;
import com.alejandroLopez.repository.EquipmentRepository;
import com.alejandroLopez.repository.WorkerRepository;
import com.alejandroLopez.service.InspectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/inspections")
public class InspectionController {

    @Autowired
    private InspectionService inspectionService;

    @Autowired
    private WorkerRepository workerRepository;

    @Autowired
    private EquipmentRepository equipmentRepository;

    @GetMapping
    public List<Inspection> getAllInspections() {
        return inspectionService.getAllInspections();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Inspection> getInspectionById(@PathVariable Integer id) {
        Optional<Inspection> inspection = inspectionService.getInspectionById(id);
        return inspection.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }



    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteInspection(@PathVariable Integer id) {
        inspectionService.deleteInspection(id);
        return ResponseEntity.noContent().build();
    }
    @PostMapping
    public ResponseEntity<?> createInspection(@RequestBody InspectionDTO inspectionDTO) {
        try {
            inspectionService.saveInspection(inspectionDTO);
            return new ResponseEntity<>("Inspección creada con éxito", HttpStatus.CREATED);
        } catch (RuntimeException e) {
            // Asegurarse de devolver un JSON con el mensaje de error
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
    @GetMapping("/worker/{workerId}")
    public List<Inspection> getInspectionsByWorkerId(@PathVariable Long workerId) {
        return inspectionService.getInspectionsByWorkerId(workerId);
    }
    // Endpoint para obtener una inspección con todos los detalles relacionados (trabajador, equipo, empresa)
    @GetMapping("/inspection/{inspectionId}")
    public ResponseEntity<Map<String, Object>> getInspectionDetails(@PathVariable Integer inspectionId) {
        Map<String, Object> inspection = inspectionService.getInspectionDetails(inspectionId);

        if (inspection == null || inspection.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(inspection);
    }

    @PatchMapping("/{id}/archive")
    public ResponseEntity<?> archiveInspection(@PathVariable Integer id) {
        try {
            Inspection updatedInspection = inspectionService.archiveInspection(id);
            return ResponseEntity.ok(updatedInspection);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

}
