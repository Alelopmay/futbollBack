package com.alejandroLopez.service;

import com.alejandroLopez.model.Equipo;
import com.alejandroLopez.repository.EquiposRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EquipoService {

    private final EquiposRepository equipoRepository;

    public Equipo crearEquipo(Equipo equipo){
        return equipoRepository.save(equipo);
    }

    public Equipo modificarEquipo(Integer id, Equipo equipo){

        Equipo equipoBD = equipoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Equipo no encontrado"));

        equipoBD.setNombre(equipo.getNombre());
        equipoBD.setJugadores(equipo.getJugadores());

        return equipoRepository.save(equipoBD);
    }

    public void eliminarEquipo(Integer id){

        if(!equipoRepository.existsById(id)){
            throw new RuntimeException("Equipo no encontrado");
        }

        equipoRepository.deleteById(id);
    }

    public List<Equipo> listarEquipos(){

        return equipoRepository.findAll();
    }

}