package comanda.service.jpa;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import comanda.entity.EstadisFactu;
import comanda.repository.EstadisFactusRepository;
import comanda.service.IEstadisFactusService;

@Service
public class EstadisFactusService implements IEstadisFactusService{

	@Autowired
	private EstadisFactusRepository repoEstadisFactus;

	public List<EstadisFactu> buscarTodas() {
		return repoEstadisFactus.findAll();
	}

	public void guardar(EstadisFactu EstadisFactu) {
		repoEstadisFactus.save(EstadisFactu);
	}

	public void eliminar(int idEstadisFactu) {
		repoEstadisFactus.deleteById(idEstadisFactu);
	}
	
	public Optional<EstadisFactu> buscarEstadisFactu(int idEstadisFactu) {
		return repoEstadisFactus.findById(idEstadisFactu);
	}
}
