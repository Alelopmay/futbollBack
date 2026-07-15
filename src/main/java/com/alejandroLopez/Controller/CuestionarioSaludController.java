package com.alejandroLopez.Controller;

import com.alejandroLopez.model.CuestionarioSalud;
import com.alejandroLopez.service.CuestionarioSaludService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
@RestController
@RequestMapping("/cuestionarioDeSalud")
@RequiredArgsConstructor

public class CuestionarioSaludController {

    private final CuestionarioSaludService service;

    @PostMapping
    public ResponseEntity<CuestionarioSalud> crear(
            @RequestBody CuestionarioSalud cuestionario){

        return ResponseEntity.ok(
                service.crear(cuestionario)
        );

    }

    @PutMapping
    public ResponseEntity<CuestionarioSalud> editar(
            @RequestBody CuestionarioSalud cuestionario){

        return ResponseEntity.ok(
                service.editar(cuestionario)
        );

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(
            @PathVariable Integer id){

        service.eliminar(id);

        return ResponseEntity.noContent().build();

    }

    @GetMapping("/jugador/{idJugador}")
    public ResponseEntity<List<CuestionarioSalud>> listarJugador(
            @PathVariable Integer idJugador){

        return ResponseEntity.ok(
                service.listarJugador(idJugador)
        );

    }
    @PostMapping("/importar")
    public ResponseEntity<String> importarExcel(
            @RequestParam("archivo") MultipartFile archivo)
            throws IOException {

        service.importarExcel(archivo);

        return ResponseEntity.ok(
                "Cuestionarios importados correctamente."
        );

    }
}