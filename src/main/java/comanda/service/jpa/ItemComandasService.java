package comanda.service.jpa;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import comanda.entity.ItemComanda;
import comanda.entity.Producto;
import comanda.repository.ItemComandasRepository;
import comanda.service.IItemComandasService;
import comanda.service.IProductosService;

@Service
public class ItemComandasService implements IItemComandasService{

	@Autowired
	private ItemComandasRepository repoItemComandas;
		
	@Autowired
	private IProductosService serviceProductos;
	

	public List<ItemComanda> buscarTodos() {
		return repoItemComandas.findAll();
	}

	/*public void guardar(ItemComanda itemComanda) {
	repoItemComandas.save(itemComanda);
    }*/
	
	public void guardar(ItemComanda itemComanda) {	
		Integer productoId = itemComanda.getProducto().getId();
		System.out.println("Envio el producto con el id: " + productoId);
		Optional<Producto> productoObtenido = serviceProductos.buscarProducto(productoId);
		if (productoObtenido.isPresent()) {
			itemComanda.setProducto(productoObtenido.get());
			itemComanda.setPrecio(productoObtenido.get().getPrecio());
			itemComanda.setTotal();
			repoItemComandas.save(itemComanda);
		} else {
			System.out.println("error");
		}
		return;		
	}

	public void eliminar(int idItemComanda) {
		repoItemComandas.deleteById(idItemComanda);
	}
	
	public Optional<ItemComanda> buscarItemComanda(int idItemComanda) {
		return repoItemComandas.findById(idItemComanda);
	}
	
}
