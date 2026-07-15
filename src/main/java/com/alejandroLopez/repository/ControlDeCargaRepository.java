package com.alejandroLopez.repository;

import com.alejandroLopez.model.ControlDeCarga;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ControlDeCargaRepository extends JpaRepository<ControlDeCarga, Integer> {

    List<ControlDeCarga> findByJugadorIdOrderBySemanaAsc(Integer idJugador);

    List<ControlDeCarga> findByJugadorIdOrderByIdAsc(Integer idJugador);

    Optional<ControlDeCarga> findByJugadorIdAndSemana(
            Integer idJugador,
            String semana
    );

}