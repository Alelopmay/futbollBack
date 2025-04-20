package com.alejandroLopez.Controller;

import com.alejandroLopez.DTO.CompanyDTO;
import com.alejandroLopez.model.Company;
import com.alejandroLopez.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/companies")
@CrossOrigin(origins = "http://localhost:5173")  // Permite CORS solo para este controlador
public class CompanyController {

    @Autowired
    private CompanyService companyService;

    // Obtener todas las empresas
    @GetMapping
    public ResponseEntity<List<CompanyDTO>> getAllCompanies() {
        List<Company> companies = companyService.getAllCompanies();

        // Convertir cada Company a CompanyDTO
        List<CompanyDTO> companyDTOs = companies.stream().map(company -> {
            // Ya no necesitamos la latitud y longitud, ya que se eliminaron de la entidad Company
            return new CompanyDTO(
                    company.getId(),
                    company.getName(), // Agregado el nombre de la empresa
                    company.getAddress(),
                    company.getAssociationDate()
            );
        }).collect(Collectors.toList());

        return ResponseEntity.ok(companyDTOs);
    }

    // Crear una nueva empresa
    @PostMapping
    public ResponseEntity<CompanyDTO> createCompany(@RequestBody CompanyDTO companyDTO) {
        try {
            Company companyToCreate = new Company(
                    companyDTO.getId(),
                    companyDTO.getName(), // Añadir nombre de la empresa
                    companyDTO.getAddress(),
                    companyDTO.getAssociationDate(),
                    null // No se maneja ubicación ahora, ya que se ha eliminado
            );

            Company createdCompany = companyService.createOrUpdateCompany(companyToCreate);

            companyDTO.setId(createdCompany.getId());

            return ResponseEntity.ok(companyDTO);

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    // Eliminar una empresa por su ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCompany(@PathVariable Long id) {
        try {
            // Verificar si la empresa existe antes de intentar eliminarla
            Optional<Company> company = companyService.getCompanyById(id);
            if (!company.isPresent()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build(); // Empresa no encontrada
            }

            // Llamar al servicio para eliminar la empresa
            companyService.deleteCompany(Math.toIntExact(id));

            return ResponseEntity.noContent().build(); // Eliminación exitosa, respuesta sin contenido
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build(); // Error en el servidor
        }
    }

    // Editar una empresa por su ID
    @PutMapping("/{id}")
    public ResponseEntity<CompanyDTO> updateCompany(@PathVariable Integer id, @RequestBody CompanyDTO companyDTO) {
        try {
            // Buscar la empresa por ID
            Optional<Company> optionalCompany = companyService.getCompanyById(Long.valueOf(id));

            // Verificar si la empresa existe
            if (!optionalCompany.isPresent()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build(); // Empresa no encontrada
            }

            // Obtener la empresa del Optional
            Company existingCompany = optionalCompany.get();

            // Actualizar los datos de la empresa con los valores del DTO
            existingCompany.setName(companyDTO.getName());
            existingCompany.setAddress(companyDTO.getAddress());
            existingCompany.setAssociationDate(companyDTO.getAssociationDate());

            // Guardar la empresa actualizada
            Company updatedCompany = companyService.createOrUpdateCompany(existingCompany);

            // Convertir la empresa actualizada en un DTO para la respuesta
            CompanyDTO updatedCompanyDTO = new CompanyDTO(
                    updatedCompany.getId(),
                    updatedCompany.getName(),
                    updatedCompany.getAddress(),
                    updatedCompany.getAssociationDate()
            );

            return ResponseEntity.ok(updatedCompanyDTO); // Retornar la empresa actualizada
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build(); // Error en el servidor
        }
    }
}
