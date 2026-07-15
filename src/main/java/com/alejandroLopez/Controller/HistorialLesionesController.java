package com.alejandroLopez.Controller;

import com.alejandroLopez.model.HistorialLesiones;
import com.alejandroLopez.service.HistorialLesionesService;
import io.swagger.v3.oas.annotations.servers.Server;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/historial")
@RequiredArgsConstructor
public class HistorialLesionesController {
    private final HistorialLesionesService historialLesionesService;

    @PostMapping
    public ResponseEntity<HistorialLesiones> crear(@RequestBody HistorialLesiones historial){

        return ResponseEntity.ok(
                historialLesionesService.crearLesion(historial)
        );

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Integer id){

        historialLesionesService.eliminarLesion(id);

        return ResponseEntity.noContent().build();

    }

    @GetMapping("/jugador/{idJugador}")
    public ResponseEntity<List<HistorialLesiones>> listarJugador(@PathVariable Integer idJugador){

        return ResponseEntity.ok(
                historialLesionesService.listarLesionesJugador(idJugador)
        );

    }

    @PostMapping("/excel")
    public ResponseEntity<List<HistorialLesiones>> importarExcel(
            @RequestBody List<HistorialLesiones> lista){

        return ResponseEntity.ok(
                historialLesionesService.importarListaExcel(lista)
        );

    }
    @PutMapping
    public ResponseEntity<HistorialLesiones> editar(
            @RequestBody HistorialLesiones historial) {

        HistorialLesiones actualizado = historialLesionesService.editar(historial);

        return ResponseEntity.ok(actualizado);
    }
}
