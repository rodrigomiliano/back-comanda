package comanda.service.jpa;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import comanda.entity.Categoria;
import comanda.repository.CategoriasRepository;
import comanda.service.ComandaServiceException;
import comanda.service.ICategoriasService;

@Service
public class CategoriasService implements ICategoriasService {

	private final Logger LOGGER = LoggerFactory.getLogger(CategoriasService.class);

	@Autowired
	private CategoriasRepository repoCategorias;

	public List<Categoria> buscarTodas() {
		System.out.println("------------------------------------------------------------");
		List<Categoria> categorias = repoCategorias.findAll(Sort.by("id")); // Esto lo ordena en la consola de spring
		System.out.println("Listado de Categorías: ");
		categorias.forEach(t -> {
			System.out.println(t);
		});
		return repoCategorias.findAll(Sort.by("id")); // Esto lo ordena en postman
	}

	public Categoria guardar(Categoria categoria) throws ComandaServiceException {
		LOGGER.info(">>>>>> Categoria a guardar: " + categoria);

		LOGGER.info(">>>>>> Categoria a guardar via el repo: " + categoria);
		System.out.println("Guardando " + categoria);

		return repoCategorias.save(categoria);		
	}

	public Categoria modificar(Categoria categoria) throws ComandaServiceException {
		System.out.println("------------------------------------------------------------");

		LOGGER.info(">>>>>> Categoria a guardar: " + categoria);

		// Buscar Categoria existente
		Categoria categoriaNew = null;
		try {
			categoriaNew = buscarCategoria(categoria.getId());
		} catch (ComandaServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// Reemplazo el valor del objeto actualmente en la DB con el valor que se pasa
		// por el Body
		categoriaNew.setNombre(categoria.getNombre());
		categoriaNew.setImagen(categoria.getImagen());

		LOGGER.info("categoria: " + categoria.toString());
		LOGGER.info(">>>>>> Categoria a guardar via el repo: " + categoria);
		System.out.println("Guardando " + categoria);

		return guardar(categoria);
	}

	public void eliminar(int idCategoria) throws Exception {
		System.out.println("Eliminando registro: " + buscarCategoria(idCategoria));
		repoCategorias.deleteById(idCategoria);
	}	

	public Categoria buscarCategoria(int idCategoria) throws ComandaServiceException {
		System.out.println("------------------------------------------------------------");
		Optional<Categoria> optional = repoCategorias.findById(idCategoria);
		if (optional.isPresent()) {
			Categoria u = optional.get();
			System.out.println("Elegiste " + u);
			return u;
		} else {
			System.out.println("------------------------------------------------------------");
			System.out.println("No existe la Categoria n° " + idCategoria);
			throw new ComandaServiceException("PS002", "No existe la Categoria n° " + idCategoria);
		}
	}
}
