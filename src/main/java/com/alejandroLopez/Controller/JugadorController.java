package com.alejandroLopez.Controller;

import com.alejandroLopez.model.Jugador;
import com.alejandroLopez.service.JugadorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/jugador")
@RequiredArgsConstructor
public class JugadorController {
private  final JugadorService jugadorService;

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Jugador> crearJugador(

            @RequestParam String nombre,
            @RequestParam String apellido,
            @RequestParam LocalDate fechaNacimiento,
            @RequestParam Integer edad,
            @RequestParam String posicion,
            @RequestParam(required = false) Double disponibilidad,
            @RequestParam Integer idEquipo,
            @RequestParam MultipartFile foto

    ){

        return ResponseEntity.ok(

                jugadorService.crearJugador(
                        nombre,
                        apellido,
                        fechaNacimiento,
                        edad,
                        posicion,
                        disponibilidad,
                        idEquipo,
                        foto
                )

        );

    }
    @DeleteMapping("/{id}")
    public  ResponseEntity<Jugador>eliminarJugador(@PathVariable Integer id){
        jugadorService.eliminarJugador(id);
        return ResponseEntity.noContent().build();
    }
    @PutMapping("/{id}")
    public  ResponseEntity<Jugador>modificarJugadro(@PathVariable Integer id , @RequestBody Jugador jugador){
        return  ResponseEntity.ok(
                jugadorService.modificarJugador(id,jugador)
        );
    }
    @GetMapping("/equipo/{id}")
    public ResponseEntity<List<Jugador>> listadoJugadoresEquipo(@PathVariable Integer id) {

        return ResponseEntity.ok(
                jugadorService.listaDejugadoresPorEquipo(id)
        );

    }
}
