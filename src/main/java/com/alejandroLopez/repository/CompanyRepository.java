package com.alejandroLopez.repository;

import com.alejandroLopez.model.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CompanyRepository extends JpaRepository<Company, Integer> {

    // Consulta personalizada para encontrar empresas cercanas usando latitud y longitud

    // Consulta personalizada para obtener el ID de una empresa por nombre y contraseña (la contraseña debería estar encriptada)


    // Puedes agregar más consultas personalizadas si es necesario
}
