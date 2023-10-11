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

import comanda.controller.dto.request.RolUpdateDto;
import comanda.controller.dto.response.RolResponse;
import comanda.entity.Rol;
import comanda.service.ComandaServiceException;
import comanda.service.IRolesService;
import comanda.service.mapper.RolMapper;

@RestController
@RequestMapping("/comanda")
public class RolesController {

	private final Logger LOGGER = LoggerFactory.getLogger(RolesController.class);

	@Autowired
	private IRolesService serviceRoles;

	private final RolMapper rolMapper = RolMapper.INSTANCE;

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
	public Rol guardar(@RequestBody Rol rol) {
		serviceRoles.guardar(rol);
		return rol;
	}

	@PutMapping("/rol")
	public Rol modificar(@RequestBody Rol rol) {
		serviceRoles.guardar(rol);
		return rol;
	}

	@PutMapping("/rol/{id}")
	public Rol modificar(@PathVariable("id") int idRol, @RequestBody RolUpdateDto rolDto)
			throws ComandaServiceException {

		LOGGER.info("idRol: " + idRol);
		LOGGER.info("Rol: " + rolDto.toString());

		// Buscar rol existente
		Rol rol = null;
		try {
			rol = serviceRoles.buscarRol(idRol);
		} catch (ComandaServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// Reemplazo el valor del objeto actualmente en la DB con el valor que se pasa
		// por el Body
		rol.setNombre(rolDto.getNombre());

		LOGGER.info("rol: " + rol.toString());

		serviceRoles.guardar(rol);
		LOGGER.info("Rol guardado: " + rol.toString());

		return rol;
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
