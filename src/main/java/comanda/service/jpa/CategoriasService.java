package comanda.service.jpa;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import comanda.entity.Categoria;
import comanda.repository.CategoriasRepository;
import comanda.service.ICategoriasService;

@Service
public class CategoriasService implements ICategoriasService {

	@Autowired
	private CategoriasRepository repoCategorias;

	public List<Categoria> buscarTodas() {
		return repoCategorias.findAll();
	}

	public void guardar(Categoria categoria) {
		repoCategorias.save(categoria);
	}

	public void eliminar(int idCategoria) {
		repoCategorias.deleteById(idCategoria);
	}
	
	public Optional<Categoria> buscarCategoria(int idCategoria) {
		return repoCategorias.findById(idCategoria);
	}
}
