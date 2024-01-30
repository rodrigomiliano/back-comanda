package comanda.controller;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import comanda.controller.dto.request.RolInsertDto;
import comanda.controller.dto.request.RolUpdateDto;
import comanda.controller.dto.response.RolResponse;
import comanda.entity.Rol;
import comanda.service.ComandaServiceException;
import comanda.service.IRolesService;
import comanda.service.mapper.RolMapper;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/comanda")
public class RolesController {

	private final Logger LOGGER = LoggerFactory.getLogger(RolesController.class);

	@Autowired
	private IRolesService serviceRoles;

	@Autowired
	private RolMapper rolMapper;

	@GetMapping("/rol")
	public List<RolResponse> buscarTodos() {
		List<Rol> roles = serviceRoles.buscarTodos();
		List<RolResponse> response = rolMapper.mapToRolResponseList(roles);
		return response;
	}

	@GetMapping("/rol/{id}")
	public RolResponse buscarRol(@PathVariable("id") int idRol) {
		Rol rol = null;
		try {
			rol = serviceRoles.buscarRol(idRol);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		LOGGER.info(">>>>>> Rol: " + rol);
		RolResponse rolResponse = rolMapper.mapToRolDto(rol);
		LOGGER.info(">>>>>> rolResponse: " + rolResponse);
		return rolResponse;
	}

	@PostMapping("/rol")
	public RolResponse guardar(@RequestBody RolInsertDto rolDto) throws ComandaServiceException {

		// Creamos rol a insertar
		Rol rol = null;
		rol = rolMapper.mapToRol(rolDto);
		LOGGER.info(">>>>>> Rol luego del mapper : " + rol);

		rol = serviceRoles.guardar(rol);

		RolResponse rolResponse = rolMapper.mapToRolDto(rol);
		LOGGER.info(">>>>>> rolResponse: " + rolResponse);

		return rolResponse;
	}
	
	@PutMapping("/rol/{id}")
	public RolResponse modificar(@PathVariable("id") int idRol, @RequestBody RolUpdateDto rolDto)
			throws ComandaServiceException {

		Rol rol = null;
		rol = rolMapper.mapToRol(rolDto);
		rol.setId(idRol);
		LOGGER.info(">>>>>> Rol luego del mapper : " + rol);

		LOGGER.info("idRol: " + idRol);
		LOGGER.info("Rol: " + rolDto.toString());

		rol = serviceRoles.modificar(rol);
		LOGGER.info("Rol guardado: " + rol.toString());

		RolResponse rolResponse = rolMapper.mapToRolDto(rol);
		LOGGER.info(">>>>>> rolResponse: " + rolResponse);

		return rolResponse;
	}

	@DeleteMapping("/rol/{id}")
	public String eliminar(@PathVariable("id") int idRol) {
		try {
			serviceRoles.eliminar(idRol);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "Registro Eliminado";
	}

}
