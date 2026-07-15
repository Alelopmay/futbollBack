package com.alejandroLopez.Controller;

import com.alejandroLopez.model.Equipo;
import com.alejandroLopez.service.EquipoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/equipos")
@RequiredArgsConstructor
public class EquipoController {
private final EquipoService equipoService;

    @PostMapping
    public ResponseEntity<Equipo> crearEquipo(@RequestBody Equipo equipo){
        return ResponseEntity.ok(
            equipoService.crearEquipo(equipo)
        );
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Equipo>eliminarEquipo( @PathVariable Integer id){
        equipoService.eliminarEquipo(id);
        return  ResponseEntity.noContent().build();
    }
    @PutMapping("/{id}")
    public ResponseEntity<Equipo> modificarEquipo(@PathVariable Integer id,
                                                  @RequestBody Equipo equipo){

        return ResponseEntity.ok(
                equipoService.modificarEquipo(id, equipo)
        );
    }
    @GetMapping
    public ResponseEntity<List<Equipo>>listaEquipos(){
        return ResponseEntity.ok(
                equipoService.listarEquipos()
        );
    }

}
