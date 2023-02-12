package comanda.service.jpa;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import comanda.entity.Estado;
import comanda.repository.EstadosRepository;
import comanda.service.IEstadosService;

@Service
public class EstadosService implements IEstadosService {

	@Autowired
	private EstadosRepository repoEstados;

	public List<Estado> buscarTodos() {
		return repoEstados.findAll();
	}

	public void guardar(Estado estado) {
		repoEstados.save(estado);
	}

	public void eliminar(int idEstado) {
		repoEstados.deleteById(idEstado);
	}
	
	public Optional<Estado> buscarEstado(int idEstado) {
		return repoEstados.findById(idEstado);
	}
}
