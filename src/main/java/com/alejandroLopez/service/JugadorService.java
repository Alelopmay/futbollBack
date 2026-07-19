package com.alejandroLopez.service;

import com.alejandroLopez.model.Equipo;
import com.alejandroLopez.model.Jugador;
import com.alejandroLopez.repository.EquiposRepository;
import com.alejandroLopez.repository.JugadorRepository;
import com.cloudinary.Cloudinary;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class JugadorService {

    private final JugadorRepository jugadorRepository;

    private final EquiposRepository equipoRepository;

    private final Cloudinary cloudinary;

    public Jugador crearJugador(

            String nombre,
            String apellido,
            LocalDate fechaNacimiento,
            Integer edad,
            String posicion,
            Double disponibilidad,
            Integer idEquipo,
            MultipartFile foto

    ) {

        try {

            Equipo equipo = equipoRepository.findById(idEquipo)
                    .orElseThrow(() ->
                            new RuntimeException("Equipo no encontrado"));

            Map subida = cloudinary.uploader().upload(

                    foto.getBytes(),

                    Map.of(

                            "folder", "jugadores"

                    )

            );

            String urlFoto = subida.get("secure_url").toString();

            Jugador jugador = new Jugador();

            jugador.setNombre(nombre);

            jugador.setApellido(apellido);

            jugador.setFechaNacimiento(fechaNacimiento);

            jugador.setEdad(edad);

            jugador.setPosicion(posicion);

            jugador.setDisponibilidad(disponibilidad);

            jugador.setFoto(urlFoto);

            jugador.setEquipo(equipo);

            return jugadorRepository.save(jugador);

        }

        catch (Exception e) {

            throw new RuntimeException("Error al guardar jugador", e);

        }

    }

    public Jugador modificarJugador(Integer id, Jugador jugador) {

        Jugador jugadorBD = jugadorRepository.findById(id)

                .orElseThrow(() ->
                        new RuntimeException("Jugador no encontrado"));

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

    public void eliminarJugador(Integer id) {

        if (!jugadorRepository.existsById(id)) {

            throw new RuntimeException("Jugador no encontrado");

        }

        jugadorRepository.deleteById(id);

    }

    public List<Jugador> listaDejugadoresPorEquipo(Integer id) {

        return jugadorRepository.findByEquipoId(id);

    }

}