package com.alejandroLopez.repository;

import com.alejandroLopez.model.Fatiga;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface FatigaRespository extends JpaRepository<Fatiga,Integer> {
    List<Fatiga> findByJugadorId(Integer idJugador);
}
