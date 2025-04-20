package com.alejandroLopez.Controller;

import com.alejandroLopez.model.Company;
import com.alejandroLopez.model.Equipment;
import com.alejandroLopez.service.CompanyService;
import com.alejandroLopez.service.EquipmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/equipments")
public class EquipmentController {

    @Autowired
    private EquipmentService equipmentService;

    @Autowired
    private CompanyService companyService; // Se usa para buscar la compañía

    @GetMapping
    public List<Equipment> getAllEquipments() {
        return equipmentService.getAllEquipments();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Equipment> getEquipmentById(@PathVariable Integer id) {
        Optional<Equipment> equipment = equipmentService.getEquipmentById(id);
        return equipment.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<?> createEquipment(@RequestBody Equipment equipment, @RequestParam Integer companyId) {
        if (companyId == null) {
            return ResponseEntity.badRequest().body("Company ID is required.");
        }

        Optional<Company> companyOpt = companyService.getCompanyById(Long.valueOf(companyId));
        if (companyOpt.isEmpty()) {
            return ResponseEntity.badRequest().body("Company not found.");
        }

        equipment.setCompany(companyOpt.get());

        try {
            Equipment savedEquipment = equipmentService.saveEquipment(equipment);
            return ResponseEntity.ok(savedEquipment);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Equipment> updateEquipment(@PathVariable Integer id, @RequestBody Equipment equipmentDetails) {
        try {
            Equipment updatedEquipment = equipmentService.updateEquipment(id, equipmentDetails);
            return ResponseEntity.ok(updatedEquipment);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEquipment(@PathVariable Integer id) {
        equipmentService.deleteEquipment(id);
        return ResponseEntity.noContent().build();
    }
    @GetMapping("/by-company")
    public List<Object[]> getEquipmentsByCompany() {
        return equipmentService.getAllEquipmentsByCompany();
    }
}
