package comanda.service.jpa;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import comanda.entity.Producto;
import comanda.repository.ProductosRepository;
import comanda.service.ComandaServiceException;
import comanda.service.IProductosService;

@Service
public class ProductosService implements IProductosService {

	@Autowired
	private ProductosRepository repoProductos;

	public List<Producto> buscarTodos() {
		System.out.println("------------------------------------------------------------");
		List<Producto> productos = repoProductos.findAll();
		System.out.println("Listado de Productos: ");
		productos.forEach(t -> {
			System.out.println(t);
		});
		return repoProductos.findAll();
	}

	public void guardar(Producto producto) {
		System.out.println("------------------------------------------------------------");
		repoProductos.save(producto);
		System.out.println("Guardando " + producto);
	}

	public void eliminar(int idProducto) throws Exception {
		System.out.println("Eliminando registro: " + buscarProducto(idProducto));
		repoProductos.deleteById(idProducto);
	}

	public Producto buscarProducto(int idProducto) throws ComandaServiceException {
		System.out.println("------------------------------------------------------------");
		Optional<Producto> optional = repoProductos.findById(idProducto);
		if (optional.isPresent()) {
			Producto u = optional.get();
			System.out.println("Elegiste " + u);
			return u;
		} else {
			System.out.println("------------------------------------------------------------");
			System.out.println("No existe el Producto n° " + idProducto);
			throw new ComandaServiceException("PS001", "No existe el Producto n° " + idProducto);
		}
	}
}
