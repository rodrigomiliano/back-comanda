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
		System.out.println("------------------------------------------------------------");
		List<ItemComanda> itemComandas = repoItemComandas.findAll(); // spring
		System.out.println("Listado de ItemComandas: ");
		itemComandas.forEach(t -> {
			System.out.println(t);
		});
		return repoItemComandas.findAll(); // postman
	}
	
	
	/*public void guardar(ItemComanda itemComanda) {
	repoItemComandas.save(itemComanda);
    }*/
	
	public void guardar(ItemComanda itemComanda) {	
		System.out.println("------------------------------------------------------------");
		Integer productoId = itemComanda.getProducto().getId();
		System.out.println("Envio el producto con el id: " + productoId);
		Optional<Producto> productoObtenido = serviceProductos.buscarProducto(productoId);
		if (productoObtenido.isPresent()) {
			itemComanda.setProducto(productoObtenido.get());
			itemComanda.setPrecio(productoObtenido.get().getPrecio());
			itemComanda.setTotal();
			repoItemComandas.save(itemComanda);
			System.out.println("Guardando " + itemComanda);
		} else {
			System.out.println("error");
		}
		return;		
	}	
	
	public void eliminar(int idItemComanda) {
		System.out.println("Eliminando registro: " + buscarItemComanda(idItemComanda));
		repoItemComandas.deleteById(idItemComanda);
	}
			
	public Optional<ItemComanda> buscarItemComanda(int idItemComanda) {
		System.out.println("------------------------------------------------------------");
		Optional<ItemComanda> optional = repoItemComandas.findById(idItemComanda);
		if (optional.isPresent()) {
			ItemComanda u = optional.get();
			System.out.println("Elegiste " + u);
			return repoItemComandas.findById(idItemComanda);
		} else {
			System.out.println("------------------------------------------------------------");
			System.out.println("No existe el ItemComanda n° " + idItemComanda);
		}
		return null;
	}		
	
}
