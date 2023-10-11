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
import comanda.controller.dto.request.EstadoUpdateDto;
import comanda.controller.dto.response.EstadoResponse;
import comanda.entity.Estado;
import comanda.service.ComandaServiceException;
import comanda.service.IEstadosService;
import comanda.service.mapper.EstadoMapper;

@RestController
@RequestMapping("/comanda")
public class EstadosController {

	private final Logger LOGGER = LoggerFactory.getLogger(EstadosController.class);

	@Autowired
	private IEstadosService serviceEstados;

	private final EstadoMapper estadoMapper = EstadoMapper.INSTANCE;

	@GetMapping("/estado")
	public List<EstadoResponse> buscarTodos() {
		List<Estado> estados = serviceEstados.buscarTodos();
		List<EstadoResponse> response = estadoMapper.mapToEstadoResponseList(estados);
		return response;
	}

	@GetMapping("/estado/{id}")
	public EstadoResponse buscarEstado(@PathVariable("id") int idEstado) {
		Estado estado = null;
		try {
			estado = serviceEstados.buscarEstado(idEstado);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		LOGGER.info(">>>>>> Estado: " + estado);
		EstadoResponse estadoResponse = estadoMapper.mapToEstadoDto(estado);
		LOGGER.info(">>>>>> estadoResponse: " + estadoResponse);
		return estadoResponse;
	}

	@PostMapping("/estado")
	public Estado guardar(@RequestBody Estado estado) {
		serviceEstados.guardar(estado);
		return estado;
	}

	@PutMapping("/estado")
	public Estado modificar(@RequestBody Estado estado) {
		serviceEstados.guardar(estado);
		return estado;
	}

	@PutMapping("/estado/{id}")
	public Estado modificar(@PathVariable("id") int idEstado, @RequestBody EstadoUpdateDto estadoDto)
			throws ComandaServiceException {

		LOGGER.info("idEstado: " + idEstado);
		LOGGER.info("Estado: " + estadoDto.toString());

		// Buscar la categoria existente
		Estado est = null;
		try {
			est = serviceEstados.buscarEstado(idEstado);
		} catch (ComandaServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// Reemplazo el valor del objeto actualmente en la DB con el valor que se pasa
		// por el Body
		est.setNombre(estadoDto.getNombre());

		LOGGER.info("est: " + est.toString());

		serviceEstados.guardar(est);
		LOGGER.info("Estado guardado: " + est.toString());

		return est;
	}

	@DeleteMapping("/estado/{id}")
	public String eliminar(@PathVariable("id") int idEstado) {
		try {
			serviceEstados.eliminar(idEstado);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "Registro Eliminado";
	}

}
