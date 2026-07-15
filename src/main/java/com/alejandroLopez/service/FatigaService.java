package com.alejandroLopez.service;

import com.alejandroLopez.model.Fatiga;
import com.alejandroLopez.repository.FatigaRespository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FatigaService {
private final FatigaRespository fatigaRespository;
public Fatiga crearFatiga (Fatiga fatiga){
    return  fatigaRespository.save(fatiga);

}
public  void eliminarFatiga(Integer id){
    if(!fatigaRespository.existsById(id)){
        throw  new RuntimeException("no existe el registro");
    }
    fatigaRespository.deleteById(id);
}
public List<Fatiga>listaDefatigasJugador(Integer idJugador){
    return  fatigaRespository.findByJugadorId(idJugador);
}
}
