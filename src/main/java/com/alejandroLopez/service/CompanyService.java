package com.alejandroLopez.service;

import com.alejandroLopez.model.Company;
import com.alejandroLopez.repository.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CompanyService {

    @Autowired
    private CompanyRepository companyRepository;

    // Obtener todas las empresas
    public List<Company> getAllCompanies() {
        return companyRepository.findAll();
    }

    // Obtener empresa por ID
    public Optional<Company> getCompanyById(Long id) {
        return companyRepository.findById(Math.toIntExact(id));
    }

    // Crear o actualizar una empresa
    public Company createOrUpdateCompany(Company company) {
        // Ya no necesitamos verificar ni crear la ubicación, ya que se ha eliminado
        // Guardamos la empresa (ya sea nueva o actualizada)
        return companyRepository.save(company);
    }

    // Eliminar una empresa
    public void deleteCompany(Integer id) {
        companyRepository.deleteById(id);
    }

    // Método para buscar compañías cercanas a una ubicación específica (lat, lon) [Eliminado, ya no es necesario]

    // Obtener ID de la empresa por nombre y contraseña (para login u otros casos) [No implementado aún]
}
