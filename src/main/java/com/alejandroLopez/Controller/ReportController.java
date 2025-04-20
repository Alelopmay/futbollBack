package com.alejandroLopez.Controller;

import com.alejandroLopez.DTO.ReportDTO;
import com.alejandroLopez.model.Equipment;
import com.alejandroLopez.model.Report;
import com.alejandroLopez.model.Worker;
import com.alejandroLopez.repository.EquipmentRepository;
import com.alejandroLopez.repository.WorkerRepository;
import com.alejandroLopez.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/reports")
public class ReportController {

    @Autowired
    private ReportService reportService;

    @Autowired
    private EquipmentRepository equipmentRepository;
    @Autowired
    private WorkerRepository workerRepository;

    // Obtener todos los reportes
    @GetMapping
    public ResponseEntity<List<Report>> getAllReports() {
        return ResponseEntity.ok(reportService.getAllReports());
    }

    // Obtener un reporte por su ID
    @GetMapping("/{id}")
    public ResponseEntity<Report> getReportById(@PathVariable Integer id) {
        return reportService.getReportById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Crear un reporte con validación
    @PostMapping
    public ResponseEntity<?> createReport(@RequestBody ReportDTO reportDTO) {
        // Validación de equipo
        if (reportDTO.getEquipmentId() == null) {
            return ResponseEntity.badRequest().body("Error: El equipo es obligatorio y debe tener un ID válido.");
        }

        // Validación de trabajador
        if (reportDTO.getWorkerId() == null) {
            return ResponseEntity.badRequest().body("Error: El trabajador es obligatorio y debe tener un ID válido.");
        }

        // Convertir el equipmentId y workerId de Integer a Long
        Long equipmentId = Long.valueOf(reportDTO.getEquipmentId());
        Long workerId = Long.valueOf(reportDTO.getWorkerId());

        // Buscar el equipo por ID
        Optional<Equipment> equipmentOptional = equipmentRepository.findById(Math.toIntExact(equipmentId));
        if (!equipmentOptional.isPresent()) {
            return ResponseEntity.badRequest().body("Error: El equipo con el ID proporcionado no existe.");
        }

        // Buscar el trabajador por ID
        Optional<Worker> workerOptional = workerRepository.findById(workerId);
        if (!workerOptional.isPresent()) {
            return ResponseEntity.badRequest().body("Error: El trabajador con el ID proporcionado no existe.");
        }

        // Crear un nuevo reporte
        Report report = new Report();
        report.setDate(reportDTO.getDate());
        report.setTitle(reportDTO.getTitle());
        report.setDescription(reportDTO.getDescription());
        report.setMaintenanceType(reportDTO.getMaintenanceType());
        report.setDuration(reportDTO.getDuration());
        report.setStartTime(reportDTO.getStartTime());
        report.setEndTime(reportDTO.getEndTime());
        report.setWorker(workerOptional.get());
        report.setEquipment(equipmentOptional.get());

        // Guardar el reporte
        try {
            Report savedReport = reportService.saveReport(report);
            return ResponseEntity.ok(savedReport);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }


    // Actualizar un reporte
    @PutMapping("/{id}")
    public ResponseEntity<?> updateReport(@PathVariable Integer id, @RequestBody Report reportDetails) {
        try {
            Report updatedReport = reportService.updateReport(id, reportDetails);
            return ResponseEntity.ok(updatedReport);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    // Eliminar un reporte
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteReport(@PathVariable Integer id) {
        reportService.deleteReport(id);
        return ResponseEntity.noContent().build();
    }
    // Método para obtener todos los informes de un equipo por ID
    @GetMapping("/equipment/{equipmentId}")
    public ResponseEntity<List<Report>> getReportsByEquipmentId(@PathVariable Integer equipmentId) {
        List<Report> reports = reportService.getReportsByEquipmentId(equipmentId);
        if (reports.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(reports);
    }
    // Endpoint para obtener un informe con todos los detalles
    @GetMapping("/details/{reportId}")
    public ResponseEntity<Map<String, Object>> getReport(@PathVariable Integer reportId) {
        Map<String, Object> reportDetails = reportService.getReportWithDetails(reportId);

        if (reportDetails == null || reportDetails.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(reportDetails);
    }

    @GetMapping("/worker/{workerId}")
    public ResponseEntity<List<Report>> getReportsByWorkerId(@PathVariable Long workerId) {
        List<Report> reports = reportService.getReportsByWorkerId(workerId);
        if (reports.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(reports);
    }

}