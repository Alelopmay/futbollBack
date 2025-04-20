package com.alejandroLopez.service;


import com.alejandroLopez.model.Equipment;
import com.alejandroLopez.model.Inspection;
import com.alejandroLopez.model.Worker;
import com.alejandroLopez.repository.EquipmentRepository;
import com.alejandroLopez.repository.InspectionRepository;
import com.alejandroLopez.repository.WorkerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class InspectionService {

    @Autowired
    private InspectionRepository inspectionRepository;
    @Autowired
    private WorkerRepository workerRepository;  // Repositorio para Worker

    @Autowired
    private EquipmentRepository equipmentRepository;

    public List<Inspection> getAllInspections() {
        return inspectionRepository.findAll();
    }

    public Optional<Inspection> getInspectionById(Integer id) {
        return inspectionRepository.findById(id);
    }

    public Inspection saveOrUpdateInspection(Inspection inspection) {
        // Aquí podrías agregar validación antes de guardar si lo deseas
        return inspectionRepository.save(inspection);
    }

    public void deleteInspection(Integer id) {
        inspectionRepository.deleteById(id);
    }
    // Método para guardar una inspección
    public void saveInspection(com.alejandroLopez.DTO.InspectionDTO inspectionDTO) {
        // Convertir los IDs de Integer a Long
        Long workerId = Long.valueOf(inspectionDTO.getWorkerId());
        Long equipmentId = Long.valueOf(inspectionDTO.getEquipmentId());

        // Recuperar el worker y equipment por sus IDs convertidos a Long
        Optional<Worker> workerOptional = workerRepository.findById(workerId);
        Optional<Equipment> equipmentOptional = equipmentRepository.findById(Math.toIntExact(equipmentId));

        if (!workerOptional.isPresent()) {
            throw new RuntimeException("Worker not found");
        }

        if (!equipmentOptional.isPresent()) {
            throw new RuntimeException("Equipment not found");
        }

        Worker worker = workerOptional.get();
        Equipment equipment = equipmentOptional.get();

        // Crear un objeto Inspection con los datos del DTO
        Inspection inspection = new Inspection();
        inspection.setWorker(worker);
        inspection.setEquipment(equipment);
        inspection.setDescription(inspectionDTO.getDescription());
        inspection.setCompleted(inspectionDTO.getCompleted());

        // Asignar la fecha directamente ya que ahora es de tipo Date
        inspection.setDate(inspectionDTO.getDate());

        // Guardar la inspección en la base de datos
        inspectionRepository.save(inspection);
    }

    public List<Inspection> getInspectionsByWorkerId(Long workerId) {
        return inspectionRepository.findByWorkerId(workerId); }
    // Método para obtener la inspección con todos los detalles relacionados (trabajador, equipo y empresa)
    public Map<String, Object> getInspectionDetails(Integer inspectionId) {
        return inspectionRepository.findInspectionDetailsById(inspectionId);
    }
    public Inspection archiveInspection(Integer id) {
        Optional<Inspection> optionalInspection = inspectionRepository.findById(id);
        if (optionalInspection.isPresent()) {
            Inspection inspection = optionalInspection.get();
            inspection.setCompleted(true); // Marcar como completada
            return inspectionRepository.save(inspection); // Guardar cambios
        } else {
            throw new RuntimeException("Inspección no encontrada");
        }
    }



}
