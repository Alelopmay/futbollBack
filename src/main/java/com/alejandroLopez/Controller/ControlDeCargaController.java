package com.alejandroLopez.Controller;

import com.alejandroLopez.model.ControlDeCarga;
import com.alejandroLopez.service.ControlDeCargaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/control-carga")
@RequiredArgsConstructor
public class ControlDeCargaController {

    private final ControlDeCargaService controlDeCargaService;

    @PostMapping
    public ResponseEntity<ControlDeCarga> crear(@RequestBody ControlDeCarga control){

        return ResponseEntity.ok(
                controlDeCargaService.crear(control)
        );

    }

    @PutMapping("/{id}")
    public ResponseEntity<ControlDeCarga> editar(@PathVariable Integer id,
                                                 @RequestBody ControlDeCarga control){

        return ResponseEntity.ok(
                controlDeCargaService.editar(id, control)
        );

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Integer id){

        controlDeCargaService.eliminar(id);

        return ResponseEntity.noContent().build();

    }

    @GetMapping("/jugador/{idJugador}")
    public ResponseEntity<List<ControlDeCarga>> listar(@PathVariable Integer idJugador){

        return ResponseEntity.ok(
                controlDeCargaService.listarJugador(idJugador)
        );

    }
    @PostMapping("/importar")
    public ResponseEntity<String> importarExcel(
            @RequestParam("archivo") MultipartFile archivo)
            throws IOException {

        controlDeCargaService.importarExcel(archivo);

        return ResponseEntity.ok(
                "Control de carga importado correctamente."
        );
    }
}