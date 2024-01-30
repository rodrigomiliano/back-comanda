package comanda.controller;

import java.util.List;
import comanda.entity.Estado;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import comanda.controller.dto.request.MesaInsertDto;
import comanda.controller.dto.request.MesaUpdateDto;
import comanda.controller.dto.response.MesaResponse;
import comanda.entity.Mesa;
import comanda.service.ComandaServiceException;
import comanda.service.IMesasService;
import comanda.service.mapper.MesaMapper;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/comanda")
public class MesasController {

	private final Logger LOGGER = LoggerFactory.getLogger(ProductosController.class);

	@Autowired
	private IMesasService serviceMesas;

	private final MesaMapper mesaMapper = MesaMapper.INSTANCE;

	@GetMapping("/mesa")
	public List<MesaResponse> buscarTodos() {
		List<Mesa> mesas = serviceMesas.buscarTodos();
		List<MesaResponse> response = mesaMapper.mapToMesaResponseList(mesas);
		return response;
	}

	@GetMapping("/mesa/{id}")
	public MesaResponse buscarMesa(@PathVariable("id") int idMesa) throws ComandaServiceException {
		Mesa mesa = serviceMesas.buscarMesa(idMesa);
		return mesaMapper.mapToMesaDto(mesa);
	}

	@GetMapping("/mesausuario/{idUsuario}")
	public List<Mesa> buscarTodos(@PathVariable int idUsuario) {
		List<Mesa> mesas = serviceMesas.buscarPorUsuario(idUsuario);		
		return mesas;
	}

	@PostMapping("/mesa")
	public MesaResponse guardar(@RequestBody MesaInsertDto mesaDto) throws ComandaServiceException {
		Mesa mesa = mesaMapper.mapToMesa(mesaDto);
		mesa = serviceMesas.guardar(mesa);
		return mesaMapper.mapToMesaDto(mesa);
	}

	@PostMapping("/mesabis")
	public MesaResponse guardarbis(@RequestBody MesaInsertDto mesaDto) throws ComandaServiceException {

		// Creamos el producto a insertar
		Mesa mesa = null;
		mesa = mesaMapper.mapToMesa(mesaDto);
		LOGGER.info(">>>>>> Mesa luego del mapper : " + mesa);

		mesa = serviceMesas.guardarbis(mesa, mesaDto.getEstadoId(), mesaDto.getUsuarioId(), mesaDto.getLocalId());

		MesaResponse mesaResponse = mesaMapper.mapToMesaDto(mesa);
		LOGGER.info(">>>>>> mesaResponse: " + mesaResponse);

		return mesaResponse;
	}

	@PostMapping("/seleccion")
	public Mesa seleccion(@RequestBody MesaInsertDto mesaDto) throws ComandaServiceException {
		Mesa mesa = serviceMesas.buscarMesa(mesaDto.getId());
		mesa.setEstado(new Estado(2));
		return serviceMesas.guardar(mesa);
	}

	@PutMapping("/mesa/{id}")
	public MesaResponse modificar(@PathVariable("id") int idMesa, @RequestBody MesaUpdateDto mesaDto)
			throws ComandaServiceException {
		Mesa mesa = mesaMapper.mapToMesa(mesaDto);
		mesa.setId(idMesa);
		mesa = serviceMesas.modificar(mesa);
		return mesaMapper.mapToMesaDto(mesa);
	}
	
	@PutMapping("/mesabis/{id}")
	public MesaResponse modificarbis(@PathVariable("id") int idMesa, @RequestBody MesaUpdateDto mesaDto)
			throws ComandaServiceException {

		Mesa mesa = null;
		mesa = mesaMapper.mapToMesa(mesaDto);
		mesa.setId(idMesa);
		LOGGER.info(">>>>>> Mesa luego del mapper : " + mesa);

		LOGGER.info("idMesa: " + idMesa);
		LOGGER.info("Mesa: " + mesaDto.toString());

		mesa = serviceMesas.modificarbis(mesa, mesaDto.getEstadoId(), mesaDto.getUsuarioId(), mesaDto.getLocalId());
		LOGGER.info("Mesa guardado: " + mesa.toString());

		MesaResponse mesaResponse = mesaMapper.mapToMesaDto(mesa);
		LOGGER.info(">>>>>> mesaResponse: " + mesaResponse);

		return mesaResponse;

	}

	@DeleteMapping("/mesa/{id}")
	public String eliminar(@PathVariable("id") int idMesa) {
		try {
			serviceMesas.eliminar(idMesa);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "Registro Eliminado";
	}
}
