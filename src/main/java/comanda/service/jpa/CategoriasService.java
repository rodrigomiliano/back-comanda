package comanda.service.jpa;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import comanda.entity.Categoria;
import comanda.entity.Local;
import comanda.repository.LocalesRepository;
import comanda.repository.CategoriasRepository;
import comanda.service.ComandaServiceException;
import comanda.service.ICategoriasService;

@Service
public class CategoriasService implements ICategoriasService {

	private final Logger LOGGER = LoggerFactory.getLogger(CategoriasService.class);

	@Autowired
	private CategoriasRepository repoCategorias;
	
	@Autowired
    private LocalesRepository localesRepository; // Inyecta tu repositorio de locales aquí


	public List<Categoria> buscarTodas() {
		return repoCategorias.findAll(Sort.by("id")); // Esto lo ordena en postman
	}

	public Categoria guardar(Categoria categoria) throws ComandaServiceException {

		return repoCategorias.save(categoria);		
	}

	public Categoria modificar(Categoria categoria) throws ComandaServiceException {

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
		categoriaNew.setDestacado(categoria.getDestacado());

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

		
	@Override
	public List<Local> obtenerLocalesPorCategoria(int idCategoria) {
	    List<Object[]> result = localesRepository.findLocalesWithMaxProductByCategoriaId(idCategoria);
	    List<Local> locales = new ArrayList<>();

	    for (Object[] row : result) {
	        Local local = new Local();
	        local.setId((Integer) row[0]);// Asigna el nombre desde la primera columna
	        // Asigna los otros atributos del Local desde las otras columnas
	        // Asegúrate de convertir cada atributo al tipo correspondiente
	        local.setNombre((String) row[1]);
	        local.setCalle((String) row[2]);  
	        local.setAltura((Integer) row[3]);
	        local.setCodigo_postal((Integer) row[4]);
	        local.setTelefono((Integer) row[5]);
	        local.setImagen((String) row[6]);        
	        // Agrega el local mapeado a la lista de locales
	        locales.add(local);
	    }

	    return locales;
	}
}
