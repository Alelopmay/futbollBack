package com.alejandroLopez.service;

import com.alejandroLopez.model.CuestionarioSalud;
import com.alejandroLopez.repository.CuestionarioSaludRepository;
import lombok.RequiredArgsConstructor;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.springframework.stereotype.Service;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;

import java.util.List;
import com.alejandroLopez.model.Jugador;
import com.alejandroLopez.repository.JugadorRepository;


import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CuestionarioSaludService {

    private final CuestionarioSaludRepository repository;
    private final JugadorRepository jugadorRepository;
    public CuestionarioSalud crear(CuestionarioSalud cuestionario){

        return repository.save(cuestionario);

    }

    public CuestionarioSalud editar(CuestionarioSalud cuestionario){

        return repository.save(cuestionario);

    }

    public void eliminar(Integer id){

        repository.deleteById(id);

    }

    public List<CuestionarioSalud> listarJugador(Integer idJugador){

        return repository.findByJugadorId(idJugador);

    }
    public void importarExcel(MultipartFile archivo) throws IOException {

        Workbook workbook = WorkbookFactory.create(archivo.getInputStream());

        Sheet sheet = workbook.getSheetAt(0);

        for (int i = 1; i <= sheet.getLastRowNum(); i++) {

            Row row = sheet.getRow(i);

            if (row == null) {
                continue;
            }

            String nombreCompleto = row.getCell(1).getStringCellValue().trim();

            Optional<Jugador> jugadorOptional =
                    jugadorRepository.buscarPorNombreCompleto(nombreCompleto);

            if (jugadorOptional.isEmpty()) {

                System.out.println("Jugador no encontrado: " + nombreCompleto);

                continue;

            }

            Jugador jugador = jugadorOptional.get();

            CuestionarioSalud cuestionario = new CuestionarioSalud();

            cuestionario.setNombre(nombreCompleto);

            Date fecha = row.getCell(2).getDateCellValue();

            cuestionario.setFecha(
                    fecha.toInstant()
                            .atZone(ZoneId.systemDefault())
                            .toLocalDate()
            );

            Date hSueno = row.getCell(3).getDateCellValue();

            cuestionario.sethSueno(
                    hSueno.toInstant()
                            .atZone(ZoneId.systemDefault())
                            .toLocalTime()
            );

            Date hDespierta = row.getCell(4).getDateCellValue();

            cuestionario.sethDespierta(
                    hDespierta.toInstant()
                            .atZone(ZoneId.systemDefault())
                            .toLocalTime()
            );

            cuestionario.setCalidad(
                    (int) row.getCell(5).getNumericCellValue()
            );

            cuestionario.setNivelEstres(
                    (int) row.getCell(6).getNumericCellValue()
            );

            cuestionario.setFatiga(
                    (int) row.getCell(7).getNumericCellValue()
            );

            Cell dolor = row.getCell(8);

            if (dolor != null &&
                    dolor.getCellType() == CellType.NUMERIC) {

                cuestionario.setDolorMuscular(
                        (int) dolor.getNumericCellValue()
                );

            } else {

                cuestionario.setDolorMuscular(0);

            }

            cuestionario.setJugador(jugador);

            repository.save(cuestionario);

        }

        workbook.close();

    }
}