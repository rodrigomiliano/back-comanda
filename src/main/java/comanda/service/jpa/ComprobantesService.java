package comanda.service.jpa;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import comanda.entity.Comprobante;
import comanda.repository.ComprobantesRepository;
import comanda.service.IComprobantesService;

@Service
public class ComprobantesService implements IComprobantesService {

	@Autowired
	private ComprobantesRepository repoComprobantes;

	public List<Comprobante> buscarTodos() {
		return repoComprobantes.findAll();
	}

	public void guardar(Comprobante comprobante) {
		repoComprobantes.save(comprobante);
	}

	public void eliminar(int idComprobante) {
		repoComprobantes.deleteById(idComprobante);
	}
	
	public Optional<Comprobante> buscarComprobante(int idComprobante) {
		return repoComprobantes.findById(idComprobante);
	}
}
