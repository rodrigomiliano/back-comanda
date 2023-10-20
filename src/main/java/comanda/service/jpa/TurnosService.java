package comanda.service.jpa;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import comanda.entity.Estado; 
import comanda.entity.Turno; 
import comanda.repository.TurnosRepository; 
import comanda.service.ComandaServiceException;
import comanda.service.IEstadosService; 
import comanda.service.ITurnosService; 

@Service
public class TurnosService implements ITurnosService {

    private final Logger LOGGER = LoggerFactory.getLogger(TurnosService.class);

    @Autowired
    private TurnosRepository repoTurnos; 

    @Autowired
    private IEstadosService serviceEstados; 

    public List<Turno> buscarTodos() { 
        System.out.println("------------------------------------------------------------");
        List<Turno> turnos = repoTurnos.findAll(); 
        System.out.println("Listado de Turnos: "); 
        turnos.forEach(t -> {
            System.out.println(t);
        });
        return repoTurnos.findAll(); 
    }

    public Turno guardar(Turno turno, Integer estadoId) throws ComandaServiceException { 
        System.out.println("------------------------------------------------------------");

        LOGGER.info(">>>>>> Turno a guardar: " + turno); 
        LOGGER.info(">>>>>> estadoId: " + estadoId); 

        if (estadoId != null) {
            // Buscar el estado por ID usando serviceEstados
            Estado estado = serviceEstados.buscarEstado(estadoId); 

            LOGGER.info(">>>>>> estado: " + estado); 

            if (estado != null) {
                turno.setEstado(estado); 
            } else {
                throw new ComandaServiceException("TS02", "No existe estado para el id: " + estadoId); 
            }

        } else {
            throw new ComandaServiceException("TS01", "El id del estado es nulo"); 
        }

        LOGGER.info(">>>>>> Turno a guardar via el repo: " + turno); 
        System.out.println("Guardando " + turno);

        return repoTurnos.save(turno); 
    }

    public Turno modificar(Turno turno, Integer estadoId) throws ComandaServiceException { 
        System.out.println("------------------------------------------------------------");

        LOGGER.info(">>>>>> Turno a guardar: " + turno); 
        LOGGER.info(">>>>>> estadoId: " + estadoId); 

        // Buscar el turno existente
        Turno turnoNew = null;
        try {
            turnoNew = buscarTurno(turno.getId());
        } catch (ComandaServiceException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        // Reemplazo el valor del objeto actualmente en la DB con el valor que se pasa
        // por el Body
        turnoNew.setHorario(turno.getHorario());        

        LOGGER.info("Estado actual: " + turno.getEstado()); 

        LOGGER.info("turno: " + turno.toString()); 
        LOGGER.info(">>>>>> Turno a guardar via el repo: " + turno); 
        System.out.println("Guardando " + turno);

        return guardar(turno, estadoId); 
    }

    public void eliminar(int idTurno) throws Exception { 
        System.out.println("Eliminando registro: " + buscarTurno(idTurno));
        repoTurnos.deleteById(idTurno); 
    }

    public Turno buscarTurno(int idTurno) throws ComandaServiceException {
        System.out.println("------------------------------------------------------------");
        Optional<Turno> optional = repoTurnos.findById(idTurno); 
        if (optional.isPresent()) {
            Turno t = optional.get(); 
            System.out.println("Elegiste " + t); 
            return t; 
        } else {
            System.out.println("------------------------------------------------------------");
            System.out.println("No existe el Turno n° " + idTurno); 
            throw new ComandaServiceException("TS001", "No existe el Turno n° " + idTurno);
        }
    }
}
