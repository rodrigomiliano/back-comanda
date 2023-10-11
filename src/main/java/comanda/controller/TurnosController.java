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

import comanda.controller.dto.request.TurnoUpdateDto;
import comanda.controller.dto.response.TurnoResponse;
import comanda.entity.Estado;
import comanda.entity.Turno;
import comanda.service.ComandaServiceException;
import comanda.service.IEstadosService;
import comanda.service.ITurnosService;
import comanda.service.mapper.TurnoMapper;

@RestController
@RequestMapping("/comanda")
public class TurnosController {

	private final Logger LOGGER = LoggerFactory.getLogger(TurnosController.class);

	@Autowired
	private ITurnosService serviceTurnos;

	@Autowired
	private IEstadosService serviceEstados;

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

		TurnoResponse turnoResponse = turnoMapper.mapToTurnoDto(turno);
		LOGGER.info(">>>>>> turnoResponse: " + turnoResponse);

		return turnoResponse;
	}

	@PostMapping("/turno")
	public Turno guardar(@RequestBody Turno turno) {
		serviceTurnos.guardar(turno);
		return turno;
	}

	@PutMapping("/turno")
	public Turno modificar(@RequestBody Turno turno) {
		serviceTurnos.guardar(turno);
		return turno;
	}

	@PutMapping("/turno/{id}")
	public Turno modificar(@PathVariable("id") int idTurno, @RequestBody TurnoUpdateDto turnoDto)
			throws ComandaServiceException {

		LOGGER.info("idTurno: " + idTurno);
		LOGGER.info("Turno: " + turnoDto.toString());

		if (turnoDto.getEstadoId() != null) {
			Turno turn = null;
			try {
				turn = serviceTurnos.buscarTurno(idTurno);
			} catch (ComandaServiceException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			// Reemplazo el valor del objeto actualmente en la DB con el valor que se pasa
			// por el Body
			turn.setHorario(turnoDto.getHorario());

			// Buscar estado por ID usando serviceEstados
			Estado estado = serviceEstados.buscarEstado(turnoDto.getEstadoId());

			if (estado != null) {
				turn.setEstado(estado);
			} else {
				// Manejar el caso en el que Estado no se encuentra
				// Puedes lanzar una excepción o manejarlo de otra manera según tus necesidades
			}

			LOGGER.info("Estado actual: " + turn.getEstado());

			LOGGER.info("turn: " + turn.toString());

			serviceTurnos.guardar(turn);
			LOGGER.info("Turno guardado: " + turn.toString());

			return turn;
		} else {
			// Manejar el caso en el que estadoId es null
			// Puedes lanzar una excepción o manejarlo de otra manera según tus necesidades
			return null;
		}
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
