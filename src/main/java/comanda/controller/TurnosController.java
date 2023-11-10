package comanda.controller;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import comanda.controller.dto.request.TurnoInsertDto; 
import comanda.controller.dto.request.TurnoUpdateDto; 
import comanda.controller.dto.response.TurnoResponse; 
import comanda.entity.Turno; 
import comanda.service.ComandaServiceException;
import comanda.service.ITurnosService; 
import comanda.service.mapper.TurnoMapper; 

@RestController
@RequestMapping("/comanda")
public class TurnosController { 

    private final Logger LOGGER = LoggerFactory.getLogger(TurnosController.class); 

    @Autowired
    private ITurnosService serviceTurnos; 

    private final TurnoMapper turnoMapper = TurnoMapper.INSTANCE; 

    @GetMapping("/turno") 
    public List<TurnoResponse> buscarTodos() {
        List<Turno> turnos = serviceTurnos.buscarTodos(); 
        List<TurnoResponse> response = turnoMapper.mapToTurnoResponseList(turnos);
        return response;
    }

    @GetMapping("/turno/{id}") 
    public TurnoResponse buscarTurno(@PathVariable("id") int idTurno) { 
        Turno turno = null; 
        try {
            turno = serviceTurnos.buscarTurno(idTurno); 
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        LOGGER.info(">>>>>> Turno: " + turno); 
        LOGGER.info(">>>>>> Turno Estado: " + turno.getEstado());
        // EstadoResponse estadoResponse = o
        // estadoMapper.mapToEstadoResponse(turno.getEstado()); 
        // LOGGER.info(">>>>>> estadoResponse: " + estadoResponse); 

        TurnoResponse turnoResponse = turnoMapper.mapToTurnoDto(turno); 
        LOGGER.info(">>>>>> turnoResponse: " + turnoResponse); 
        // turnoResponse.setEstado(estadoResponse); 
        // LOGGER.info(">>>>>> turnoResponse: " + turnoResponse); 
        return turnoResponse;
    }

    @PostMapping("/turno") 
    public TurnoResponse guardar(@RequestBody TurnoInsertDto turnoDto) throws ComandaServiceException { 

        // Creamos el turno a insertar
        Turno turno = null; 
        turno = turnoMapper.mapToTurno(turnoDto); 
        LOGGER.info(">>>>>> Turno luego del mapper : " + turno); 

        turno = serviceTurnos.guardar(turno, turnoDto.getEstadoId()); 
        
        TurnoResponse turnoResponse = turnoMapper.mapToTurnoDto(turno); 
        LOGGER.info(">>>>>> turnoResponse: " + turnoResponse); 
        return turnoResponse;
    }

    /*@PutMapping("/turno")
    public Turno modificar(@RequestBody Turno turno) throws ComandaServiceException {
        serviceTurnos.guardar(turno, null);
        return turno;
    }*/

    @PutMapping("/turno/{id}") 
    public TurnoResponse modificar(@PathVariable("id") int idTurno, @RequestBody TurnoUpdateDto turnoDto) 
            throws ComandaServiceException {

        Turno turno = null; 
        turno = turnoMapper.mapToTurno(turnoDto); 
        turno.setId(idTurno);
        LOGGER.info(">>>>>> Turno luego del mapper : " + turno); 

        LOGGER.info("idTurno: " + idTurno); 
        LOGGER.info("Turno: " + turnoDto.toString()); 

        turno = serviceTurnos.modificar(turno, turnoDto.getEstadoId()); 
        
        LOGGER.info("Turno guardado: " + turno.toString());

        TurnoResponse turnoResponse = turnoMapper.mapToTurnoDto(turno); 
        LOGGER.info(">>>>>> turnoResponse: " + turnoResponse); 

        return turnoResponse;

    }

    @DeleteMapping("/turno/{id}") 
    public String eliminar(@PathVariable("id") int idTurno) {
        try {
            serviceTurnos.eliminar(idTurno); 
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return "Registro Eliminado";
    }
}
