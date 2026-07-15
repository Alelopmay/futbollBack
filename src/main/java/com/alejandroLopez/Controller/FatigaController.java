package com.alejandroLopez.Controller;

import com.alejandroLopez.model.Fatiga;
import com.alejandroLopez.service.FatigaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/fatiga")
@RequiredArgsConstructor
public class FatigaController {

    private final FatigaService fatigaService;

    @PostMapping
    public ResponseEntity<Fatiga> crearFatiga(@RequestBody Fatiga fatiga){

        return ResponseEntity.ok(
                fatigaService.crearFatiga(fatiga)
        );

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarFatiga(@PathVariable Integer id){

        fatigaService.eliminarFatiga(id);

        return ResponseEntity.noContent().build();

    }

    @GetMapping("/jugador/{idJugador}")
    public ResponseEntity<List<Fatiga>> listarFatigaJugador(@PathVariable Integer idJugador){

        return ResponseEntity.ok(
                fatigaService.listaDefatigasJugador(idJugador)
        );

    }

}