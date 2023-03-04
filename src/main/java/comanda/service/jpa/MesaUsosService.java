package comanda.service.jpa;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import comanda.entity.MesaUso;
import comanda.repository.MesaUsosRepository;
import comanda.service.IMesaUsosService;

@Service
public class MesaUsosService implements IMesaUsosService {

	@Autowired
	private MesaUsosRepository repoMesaUsos;

	public List<MesaUso> buscarTodas() {
		return repoMesaUsos.findAll();
	}

	public void guardar(MesaUso mesaUso) {
		repoMesaUsos.save(mesaUso);
	}

	public void eliminar(int idMesaUso) {
		repoMesaUsos.deleteById(idMesaUso);
	}
	
	public Optional<MesaUso> buscarMesaUso(int idMesaUso) {
		return repoMesaUsos.findById(idMesaUso);
	}
}
