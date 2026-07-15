package com.alejandroLopez.service;

import com.alejandroLopez.model.ControlDeCarga;
import com.alejandroLopez.model.Jugador;
import com.alejandroLopez.repository.ControlDeCargaRepository;
import com.alejandroLopez.repository.JugadorRepository;
import lombok.RequiredArgsConstructor;
import org.apache.poi.ss.usermodel.*;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ControlDeCargaService {

    private final ControlDeCargaRepository controlDeCargaRepository;
    private final JugadorRepository jugadorRepository;

    public ControlDeCarga crear(ControlDeCarga controlDeCarga) {

        return controlDeCargaRepository.save(controlDeCarga);

    }

    public ControlDeCarga editar(Integer id, ControlDeCarga control) {

        ControlDeCarga controlBD = controlDeCargaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Control de carga no encontrado"));

        controlBD.setSemana(control.getSemana());
        controlBD.setLunes(control.getLunes());
        controlBD.setMartes(control.getMartes());
        controlBD.setMiercoles(control.getMiercoles());
        controlBD.setJueves(control.getJueves());
        controlBD.setViernes(control.getViernes());
        controlBD.setSabado(control.getSabado());
        controlBD.setDomingo(control.getDomingo());
        controlBD.setCargaAguda(control.getCargaAguda());
        controlBD.setCargaCronica(control.getCargaCronica());
        controlBD.setRiesgoLesion(control.getRiesgoLesion());

        return controlDeCargaRepository.save(controlBD);
    }

    public void eliminar(Integer id) {

        ControlDeCarga control = controlDeCargaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Control de carga no encontrado"));

        Integer idJugador = control.getJugador().getId();

        controlDeCargaRepository.delete(control);

        renumerarSemanas(idJugador);
    }

    public List<ControlDeCarga> listarJugador(Integer idJugador) {

        List<ControlDeCarga> lista =
                controlDeCargaRepository.findByJugadorIdOrderBySemanaAsc(idJugador);

        lista.sort(Comparator.comparingInt(control ->
                Integer.parseInt(control.getSemana().replace("Semana ", ""))
        ));

        return lista;
    }

    public void renumerarSemanas(Integer idJugador) {

        List<ControlDeCarga> lista = controlDeCargaRepository.findByJugadorIdOrderBySemanaAsc(idJugador);

        int numero = 1;

        for (ControlDeCarga control : lista) {

            control.setSemana("Semana " + numero);

            numero++;

        }

        controlDeCargaRepository.saveAll(lista);
    }

    public void importarExcel(MultipartFile archivo) throws IOException {

        Workbook workbook = WorkbookFactory.create(archivo.getInputStream());

        Sheet sheet = workbook.getSheet("RPE sesiones");

        if (sheet == null) {
            throw new RuntimeException("No existe la hoja RPE sesiones");
        }

        System.out.println("Leyendo hoja -> " + sheet.getSheetName());

        // Empieza donde aparecen los jugadores
        for (int fila = 4; fila <= sheet.getLastRowNum(); fila++) {

            Row row = sheet.getRow(fila);

            if (row == null) {
                continue;
            }

            Cell nombreCell = row.getCell(0);

            if (nombreCell == null) {
                continue;
            }

            String nombreCompleto = nombreCell.toString().trim();

            if (nombreCompleto.isBlank()) {
                continue;
            }

            System.out.println("Jugador Excel -> " + nombreCompleto);

            // Si viene numerado (1. Jugador)
            if (nombreCompleto.matches("^\\d+\\..*")) {

                nombreCompleto = nombreCompleto.substring(
                        nombreCompleto.indexOf(".") + 1
                ).trim();

            }

            Optional<Jugador> jugadorOptional =
                    jugadorRepository.buscarPorNombreCompleto(nombreCompleto);

            if (jugadorOptional.isEmpty()) {

                System.out.println("Jugador no encontrado -> " + nombreCompleto);

                continue;

            }

            Jugador jugador = jugadorOptional.get();

            int columna = 1;

            int numeroSemana = 1;

            while (columna + 12 <= row.getLastCellNum()) {

                Double lunes     = obtenerValor(row.getCell(columna));
                Double martes    = obtenerValor(row.getCell(columna + 2));
                Double miercoles = obtenerValor(row.getCell(columna + 4));
                Double jueves    = obtenerValor(row.getCell(columna + 6));
                Double viernes   = obtenerValor(row.getCell(columna + 8));
                Double sabado    = obtenerValor(row.getCell(columna + 10));
                Double domingo   = obtenerValor(row.getCell(columna + 12));

                // Si la semana está completamente vacía terminamos
                if (lunes == null &&
                        martes == null &&
                        miercoles == null &&
                        jueves == null &&
                        viernes == null &&
                        sabado == null &&
                        domingo == null) {

                    break;

                }

                String semana = "Semana " + numeroSemana;

                Optional<ControlDeCarga> existente =
                        controlDeCargaRepository.findByJugadorIdAndSemana(
                                jugador.getId(),
                                semana
                        );

                ControlDeCarga control;

                if (existente.isPresent()) {

                    control = existente.get();

                } else {

                    control = new ControlDeCarga();

                    control.setJugador(jugador);

                    control.setSemana(semana);

                }

                control.setLunes(lunes);
                control.setMartes(martes);
                control.setMiercoles(miercoles);
                control.setJueves(jueves);
                control.setViernes(viernes);
                control.setSabado(sabado);
                control.setDomingo(domingo);
                double cargaAguda = 0;

                if (lunes != null) cargaAguda += lunes;
                if (martes != null) cargaAguda += martes;
                if (miercoles != null) cargaAguda += miercoles;
                if (jueves != null) cargaAguda += jueves;
                if (viernes != null) cargaAguda += viernes;
                if (sabado != null) cargaAguda += sabado;
                if (domingo != null) cargaAguda += domingo;

                control.setCargaAguda(cargaAguda);

                List<ControlDeCarga> historial =
                        controlDeCargaRepository.findByJugadorIdOrderByIdAsc(
                                jugador.getId()
                        );

                double suma = cargaAguda;
                int contador = 1;

                if (!historial.isEmpty()) {

                    int inicio = Math.max(0, historial.size() - 3);

                    for (int i = inicio; i < historial.size(); i++) {

                        ControlDeCarga anterior = historial.get(i);

                        if (anterior.getCargaAguda() != null) {

                            suma += anterior.getCargaAguda();
                            contador++;

                        }

                    }

                }

                double cargaCronica = suma / contador;

                control.setCargaCronica(cargaCronica);

                double riesgo = 0;

                if (cargaCronica > 0) {

                    riesgo = cargaAguda / cargaCronica;

                }

                control.setRiesgoLesion(riesgo);

                controlDeCargaRepository.save(control);

                System.out.println(
                        "Semana " + numeroSemana +
                                " -> L=" + lunes +
                                " M=" + martes +
                                " X=" + miercoles +
                                " J=" + jueves +
                                " V=" + viernes +
                                " S=" + sabado +
                                " D=" + domingo
                );

                numeroSemana++;

                // IMPORTANTE: cada microciclo ocupa 14 columnas
                columna += 14;
            }
        }
        workbook.close();

        System.out.println("=========== IMPORTACIÓN FINALIZADA ===========");

    }
    private Double obtenerValor(Cell cell) {

        if (cell == null) {
            return 0.0;
        }

        if (cell.getCellType() == CellType.NUMERIC) {
            return cell.getNumericCellValue();
        }

        if (cell.getCellType() == CellType.STRING) {

            String texto = cell.getStringCellValue().trim();

            if (texto.isEmpty()) {
                return 0.0;
            }

            try {
                return Double.parseDouble(texto.replace(",", "."));
            } catch (Exception e) {
                return 0.0;
            }
        }

        return 0.0;
    }
}