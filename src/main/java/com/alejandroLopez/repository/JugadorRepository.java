package com.alejandroLopez.repository;

import com.alejandroLopez.model.Jugador;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface JugadorRepository extends JpaRepository<Jugador,Integer> {
    List<Jugador>findByEquipoId(Integer idEquipo);

    @Query("""
            SELECT j
            FROM Jugador j
            WHERE CONCAT(j.nombre, ' ', j.apellido) = :nombreCompleto
            """)
    Optional<Jugador> buscarPorNombreCompleto(
            @Param("nombreCompleto") String nombreCompleto
    );


}
