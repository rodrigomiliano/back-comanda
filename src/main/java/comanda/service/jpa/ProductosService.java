package comanda.service.jpa;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import comanda.entity.Producto;
import comanda.repository.ProductosRepository;
import comanda.service.IProductosService;

@Service
public class ProductosService implements IProductosService {

	@Autowired
	private ProductosRepository repoProductos;

	public List<Producto> buscarTodos() {
		return repoProductos.findAll();
	}

	public void guardar(Producto producto) {
		repoProductos.save(producto);
	}

	public void eliminar(int idProducto) {
		repoProductos.deleteById(idProducto);
	}
	
	public Optional<Producto> buscarProducto(int idProducto) {
		return repoProductos.findById(idProducto);
	}
}
