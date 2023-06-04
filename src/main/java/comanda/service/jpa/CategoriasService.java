package comanda.service.jpa;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import comanda.entity.Categoria;
import comanda.repository.CategoriasRepository;
import comanda.service.ICategoriasService;

@Service
public class CategoriasService implements ICategoriasService {

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

	public void guardar(Categoria categoria) {
		System.out.println("------------------------------------------------------------");
		repoCategorias.save(categoria);
		System.out.println("Guardando " + categoria);
	}

	public void eliminar(int idCategoria) {
		System.out.println("Eliminando registro: " + buscarCategoria(idCategoria));
		repoCategorias.deleteById(idCategoria);
	}

	/*
	 * public Optional<Categoria> buscarCategoria(int idCategoria) { return
	 * repoCategorias.findById(idCategoria); }
	 */

	public Optional<Categoria> buscarCategoria(int idCategoria) {
		System.out.println("------------------------------------------------------------");
		Optional<Categoria> optional = repoCategorias.findById(idCategoria);
		if (optional.isPresent()) {
			Categoria u = optional.get();
			System.out.println("Elegiste " + u);
			return repoCategorias.findById(idCategoria);
		} else {
			System.out.println("------------------------------------------------------------");
			System.out.println("No existe la Categoria n° " + idCategoria);
		}
		return null;
	}

}
