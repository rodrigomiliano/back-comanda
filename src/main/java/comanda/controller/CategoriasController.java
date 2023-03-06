package comanda.controller;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import comanda.entity.Categoria;
import comanda.entity.Producto;
import comanda.service.ICategoriasService;

@RestController
@RequestMapping("/comanda")
public class CategoriasController {

	@Autowired
	private ICategoriasService serviceCategorias;
	
	@GetMapping("/cargar-categorias")
	public void cargarProductos(){
		Categoria c1 = new Categoria("Categoria 1");
		Categoria c2 = new Categoria("Categoria 2");
		Categoria c3 = new Categoria("Categoria 3");
		Categoria c4 = new Categoria("Categoria 4");
		Categoria c5 = new Categoria("Categoria 5");
		serviceCategorias.guardar(c1);
		serviceCategorias.guardar(c2);
		serviceCategorias.guardar(c3);
		serviceCategorias.guardar(c4);
		serviceCategorias.guardar(c5);
	}

	@GetMapping("/categoria")
	public List<Categoria> buscarTodas() {
		return serviceCategorias.buscarTodas();
		// buscar como agregar el order by id
	}

	@GetMapping("/categoria/{id}")
	public Optional<Categoria> buscarCategoria(@PathVariable("id") int idCategoria) {
		return serviceCategorias.buscarCategoria(idCategoria);
	}

	@PostMapping("/categoria")
	public Categoria guardar(@RequestBody Categoria categoria) {
		serviceCategorias.guardar(categoria);
		return categoria;
	}

	@PutMapping("/categoria")
	public Categoria modificar(@RequestBody Categoria categoria) {
		serviceCategorias.guardar(categoria);
		return categoria;
	}

	@DeleteMapping("/categoria/{id}")
	public String eliminar(@PathVariable("id") int idCategoria) {
		serviceCategorias.eliminar(idCategoria);
		return "Registro Eliminado";
	}

}
