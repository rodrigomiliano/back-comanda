package comanda.service.jpa;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import comanda.entity.Rol;
import comanda.repository.RolesRepository;
import comanda.service.ComandaServiceException;
import comanda.service.IRolesService;

@Service
public class RolesService implements IRolesService {
	
	private final Logger LOGGER = LoggerFactory.getLogger(CategoriasService.class);

	@Autowired
	private RolesRepository repoRoles;

	public List<Rol> buscarTodos() {
		System.out.println("------------------------------------------------------------");
		List<Rol> roles = repoRoles.findAll(Sort.by("id")); // consola de spring
		System.out.println("Listado de Roles: ");
		roles.forEach(t -> {
			System.out.println(t);
		});
		return repoRoles.findAll(Sort.by("id")); // postman
	}

	public Rol guardar(Rol rol) throws ComandaServiceException {
		LOGGER.info(">>>>>> Rol a guardar: " + rol);

		LOGGER.info(">>>>>> Rol a guardar via el repo: " + rol);
		System.out.println("Guardando " + rol);

		return repoRoles.save(rol);
	}

	public Rol modificar(Rol rol) throws ComandaServiceException {
	    System.out.println("------------------------------------------------------------");

	    LOGGER.info(">>>>>> Rol a guardar: " + rol);

	    // Buscar Rol existente
	    Rol rolNew = null;
	    try {
	        rolNew = buscarRol(rol.getId());
	    } catch (ComandaServiceException e) {
	        // TODO Auto-generated catch block
	        e.printStackTrace();
	    }

	    // Reemplazo el valor del objeto actualmente en la DB con el valor que se pasa
	    // por el Body
	    rolNew.setNombre(rol.getNombre());

	    LOGGER.info("Rol: " + rol.toString());
	    LOGGER.info(">>>>>> Rol a guardar via el repo: " + rol);
	    System.out.println("Guardando " + rol);

	    return guardar(rol);
	}
	
	public void eliminar(int idRol) throws Exception {
		System.out.println("Eliminando registro: " + buscarRol(idRol));
		repoRoles.deleteById(idRol);
	}

	public Rol buscarRol(int idRol) throws ComandaServiceException {
		System.out.println("------------------------------------------------------------");
		Optional<Rol> optional = repoRoles.findById(idRol);
		if (optional.isPresent()) {
			Rol u = optional.get();
			System.out.println("Elegiste " + u);
			return u;
		} else {
			System.out.println("------------------------------------------------------------");
			System.out.println("No existe el Rol n° " + idRol);
			throw new ComandaServiceException("PS006", "No existe el Rol n° " + idRol);
		}
	}
}
