package com.alejandroLopez.service;

import com.alejandroLopez.model.Company;
import com.alejandroLopez.model.Equipment;
import com.alejandroLopez.repository.CompanyRepository;
import com.alejandroLopez.repository.EquipmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EquipmentService {

    @Autowired
    private EquipmentRepository equipmentRepository;

    @Autowired
    private CompanyRepository companyRepository;

    public List<Equipment> getAllEquipments() {
        return equipmentRepository.findAll();
    }

    public Optional<Equipment> getEquipmentById(Integer id) {
        return equipmentRepository.findById(id);
    }

    public Equipment saveEquipment(Equipment equipment) {
        // Asegurarnos de que la compañía no sea nula antes de guardar
        if (equipment.getCompany() == null || equipment.getCompany().getId() == null) {
            throw new IllegalArgumentException("Company is required and must have a valid ID.");
        }

        // Verificar si ya existe un equipo con el mismo serialNumber
        Optional<Equipment> existingEquipment = equipmentRepository.findBySerialNumber(equipment.getSerialNumber());
        if (existingEquipment.isPresent()) {
            throw new RuntimeException("El número de serie ya está en uso: " + equipment.getSerialNumber());
        }

        // Buscar la compañía en la base de datos
        Company company = companyRepository.findById(equipment.getCompany().getId())
                .orElseThrow(() -> new RuntimeException("Company not found."));
        equipment.setCompany(company);

        return equipmentRepository.save(equipment);
    }

    public Equipment updateEquipment(Integer id, Equipment equipmentDetails) {
        return equipmentRepository.findById(id)
                .map(equipment -> {
                    equipment.setSerialNumber(equipmentDetails.getSerialNumber());
                    equipment.setType(equipmentDetails.getType());
                    equipment.setBrand(equipmentDetails.getBrand());
                    equipment.setInstallationDate(equipmentDetails.getInstallationDate());
                    equipment.setDescription(equipmentDetails.getDescription());
                    return equipmentRepository.save(equipment);
                })
                .orElseThrow(() -> new RuntimeException("Equipment not found"));
    }

    public void deleteEquipment(Integer id) {
        equipmentRepository.deleteById(id);
    }
    public List<Object[]> getAllEquipmentsByCompany() {
        return equipmentRepository.findAllEquipmentsByCompany();
    }
}
