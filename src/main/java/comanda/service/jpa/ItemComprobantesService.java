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
	// Empezar a actualizar como el ejemplo de categorias
	@Autowired
	private ItemComprobantesRepository repoItemComprobantes;

	public List<ItemComprobante> buscarTodos() {
		return repoItemComprobantes.findAll();
	}

	public void guardar(ItemComprobante itemComprobante) {
		repoItemComprobantes.save(itemComprobante);
	}
	
	/*public void guardar(ItemComanda itemComanda) {	
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
	}*/

	public void eliminar(int idItemComprobante) {
		repoItemComprobantes.deleteById(idItemComprobante);
	}
	
	public Optional<ItemComprobante> buscarItemComprobante(int idItemComprobante) {
		return repoItemComprobantes.findById(idItemComprobante);
	}
	
}
