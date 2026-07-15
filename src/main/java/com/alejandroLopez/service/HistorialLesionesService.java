package com.alejandroLopez.service;

import com.alejandroLopez.model.HistorialLesiones;
import com.alejandroLopez.repository.HistorialLesionesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.swing.text.html.parser.Entity;
import java.util.List;

@Service
@RequiredArgsConstructor
public class HistorialLesionesService {
    private final HistorialLesionesRepository historialLesionesRepository;

    public HistorialLesiones crearLesion(HistorialLesiones historial){

        return historialLesionesRepository.save(historial);

    }

    public void eliminarLesion(Integer id){

        historialLesionesRepository.deleteById(id);

    }

    public List<HistorialLesiones> listarLesionesJugador(Integer idJugador){

        return historialLesionesRepository.findByJugadorId(idJugador);

    }

    public HistorialLesiones importarDesdeExcel(HistorialLesiones historial){

        return historialLesionesRepository.save(historial);

    }

    public List<HistorialLesiones> importarListaExcel(List<HistorialLesiones> lista){

        return historialLesionesRepository.saveAll(lista);

    }
    public HistorialLesiones editar(HistorialLesiones historial) {

        return historialLesionesRepository.save(historial);

    }

}