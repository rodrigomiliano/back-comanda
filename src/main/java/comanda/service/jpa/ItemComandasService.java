package comanda.service.jpa;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import comanda.entity.ItemComanda;
import comanda.repository.ItemComandasRepository;
import comanda.service.IItemComandasService;

@Service
public class ItemComandasService implements IItemComandasService{

	@Autowired
	private ItemComandasRepository repoItemComandas;

	public List<ItemComanda> buscarTodos() {
		return repoItemComandas.findAll();
	}

	public void guardar(ItemComanda itemComanda) {
		repoItemComandas.save(itemComanda);
	}

	public void eliminar(int idItemComanda) {
		repoItemComandas.deleteById(idItemComanda);
	}
	
	public Optional<ItemComanda> buscarItemComanda(int idItemComanda) {
		return repoItemComandas.findById(idItemComanda);
	}
	
}
