package comanda.service.jpa;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import comanda.entity.ComItem;
import comanda.repository.ComItemsRepository;
import comanda.service.IComItemsService;

@Service
public class ComItemsService implements IComItemsService{

	@Autowired
	private ComItemsRepository repoComItems;

	public List<ComItem> buscarTodos() {
		return repoComItems.findAll();
	}

	public void guardar(ComItem comItem) {
		repoComItems.save(comItem);
	}

	public void eliminar(int idComItem) {
		repoComItems.deleteById(idComItem);
	}
	
	public Optional<ComItem> buscarComItem(int idComItem) {
		return repoComItems.findById(idComItem);
	}
	
}
