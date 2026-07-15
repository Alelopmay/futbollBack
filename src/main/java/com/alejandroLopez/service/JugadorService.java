package com.alejandroLopez.service;

import com.alejandroLopez.model.Equipo;
import com.alejandroLopez.model.Jugador;
import com.alejandroLopez.repository.EquiposRepository;
import com.alejandroLopez.repository.JugadorRepository;
import io.swagger.v3.oas.annotations.servers.Server;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import static org.springframework.data.jpa.domain.AbstractPersistable_.id;

@Service
@RequiredArgsConstructor
public class JugadorService {

    private final JugadorRepository jugadorRepository;
    private final EquiposRepository equipoRepository;

    private static final String RUTA_FOTOS =
            "C:\\Users\\USUARIO\\Desktop\\fotos\\";

    public Jugador crearJugador(String nombre,
                                String apellido,
                                LocalDate fechaNacimiento,
                                Integer edad,
                                String posicion,
                                Double disponibilidad,
                                Integer idEquipo,
                                MultipartFile foto) {

        try {

            File carpeta = new File(RUTA_FOTOS);

            if (!carpeta.exists()) {
                carpeta.mkdirs();
            }

            String nombreFoto = UUID.randomUUID() + "_" + foto.getOriginalFilename();

            File destino = new File(RUTA_FOTOS + nombreFoto);

            foto.transferTo(destino);

            Equipo equipo = equipoRepository.findById(idEquipo)
                    .orElseThrow(() -> new RuntimeException("Equipo no encontrado"));

            Jugador jugador = new Jugador();

            jugador.setNombre(nombre);
            jugador.setApellido(apellido);
            jugador.setFechaNacimiento(fechaNacimiento);
            jugador.setEdad(edad);
            jugador.setPosicion(posicion);
            jugador.setDisponibilidad(disponibilidad);
            jugador.setFoto(nombreFoto);
            jugador.setEquipo(equipo);

            return jugadorRepository.save(jugador);

        } catch (Exception e) {
            throw new RuntimeException("Error al guardar el jugador", e);
        }

    }


    public Jugador modificarJugador(Integer id, Jugador jugador) {

        Jugador jugadorBD = jugadorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Jugador no encontrado"));

        jugadorBD.setNombre(jugador.getNombre());
        jugadorBD.setApellido(jugador.getApellido());
        jugadorBD.setFechaNacimiento(jugador.getFechaNacimiento());
        jugadorBD.setEdad(jugador.getEdad());
        jugadorBD.setPosicion(jugador.getPosicion());
        jugadorBD.setDisponibilidad(jugador.getDisponibilidad());
        jugadorBD.setFoto(jugador.getFoto());
        jugadorBD.setEquipo(jugador.getEquipo());

        return jugadorRepository.save(jugadorBD);
    }
    public  void eliminarJugador(Integer id){
       if(!jugadorRepository.existsById(id)){
           throw  new RuntimeException("el jugador no se encuentra");
       }
       jugadorRepository.deleteById(id);
    }
    public List<Jugador> listaDejugadoresPorEquipo(Integer id){
        return jugadorRepository.findByEquipoId(id);
    }
}
