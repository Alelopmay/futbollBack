package com.alejandroLopez.repository;

import com.alejandroLopez.model.HistorialLesiones;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface HistorialLesionesRepository extends JpaRepository<HistorialLesiones,Integer>{
    List<HistorialLesiones> findByJugadorId(Integer idJugador);

}
