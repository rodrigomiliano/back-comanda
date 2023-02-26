package comanda.service.jpa;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import comanda.entity.ItemComprobante;
import comanda.repository.ItemComprobantesRepository;
import comanda.service.IItemComprobantesService;

@Service
public class ItemComprobantesService implements IItemComprobantesService{

	@Autowired
	private ItemComprobantesRepository repoItemComprobantes;

	public List<ItemComprobante> buscarTodos() {
		return repoItemComprobantes.findAll();
	}

	public void guardar(ItemComprobante itemComprobante) {
		repoItemComprobantes.save(itemComprobante);
	}

	public void eliminar(int idItemComprobante) {
		repoItemComprobantes.deleteById(idItemComprobante);
	}
	
	public Optional<ItemComprobante> buscarItemComprobante(int idItemComprobante) {
		return repoItemComprobantes.findById(idItemComprobante);
	}
	
}
