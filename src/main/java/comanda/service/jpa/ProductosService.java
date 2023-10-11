package comanda.service.jpa;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import comanda.controller.ProductosController;
import comanda.entity.Categoria;
import comanda.entity.Producto;
import comanda.repository.ProductosRepository;
import comanda.service.ComandaServiceException;
import comanda.service.ICategoriasService;
import comanda.service.IProductosService;

@Service
public class ProductosService implements IProductosService {

	private final Logger LOGGER = LoggerFactory.getLogger(ProductosService.class);


	@Autowired
	private ProductosRepository repoProductos;

	@Autowired
	private ICategoriasService serviceCategorias;

	public List<Producto> buscarTodos() {
		System.out.println("------------------------------------------------------------");
		List<Producto> productos = repoProductos.findAll();
		System.out.println("Listado de Productos: ");
		productos.forEach(t -> {
			System.out.println(t);
		});
		return repoProductos.findAll();
	}

	public Producto guardar(Producto producto, Integer categoriaId) throws ComandaServiceException {
		System.out.println("------------------------------------------------------------");

		LOGGER.info(">>>>>> Producto a guardar: " + producto);
		LOGGER.info(">>>>>> categoriaId: " + categoriaId);

		if (categoriaId != null) {
			// Buscar la categoría por ID usando serviceCategorias
			Categoria categoria = serviceCategorias.buscarCategoria(categoriaId);

			LOGGER.info(">>>>>> categoria: " + categoria);

			if (categoria != null) {
				producto.setCategoria(categoria);
			} else {
				throw new ComandaServiceException("PS02", "No existe categoría para el id: " + categoriaId);
			}

		} else {
			throw new ComandaServiceException("PS01", "El id de la categoría es nulo");
		}

		LOGGER.info(">>>>>> Producto a guardar via el repo: " + producto);
		System.out.println("Guardando " + producto);

		return repoProductos.save(producto);
	}

	public Producto modificar(Producto producto, Integer categoriaId) throws ComandaServiceException {
		System.out.println("------------------------------------------------------------");

		LOGGER.info(">>>>>> Producto a guardar: " + producto);
		LOGGER.info(">>>>>> categoriaId: " + categoriaId);

		// Buscar el producto existente
		Producto productoNew = null;
		try {
			productoNew = buscarProducto(producto.getId());
		} catch (ComandaServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// Reemplazo el valor del objeto actualmente en la DB con el valor que se pasa
		// por el Body
		productoNew.setNombre(producto.getNombre());
		productoNew.setDescripcion(producto.getDescripcion());
		productoNew.setPrecio(producto.getPrecio());
		productoNew.setImagen(producto.getImagen());

		LOGGER.info("Categoria actual: " + producto.getCategoria());

		LOGGER.info("prod: " + producto.toString());
		LOGGER.info(">>>>>> Producto a guardar via el repo: " + producto);
		System.out.println("Guardando " + producto);

		return guardar(producto, categoriaId);
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
