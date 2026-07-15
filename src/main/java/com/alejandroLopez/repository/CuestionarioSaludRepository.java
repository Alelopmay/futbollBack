package com.alejandroLopez.repository;

import com.alejandroLopez.model.CuestionarioSalud;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CuestionarioSaludRepository extends JpaRepository<CuestionarioSalud, Integer> {

    List<CuestionarioSalud> findByJugadorId(Integer idJugador);

}