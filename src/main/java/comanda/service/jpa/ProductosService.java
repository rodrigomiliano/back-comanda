package comanda.service.jpa;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import comanda.entity.Categoria;
import comanda.entity.Local;
import comanda.entity.Producto;
import comanda.repository.LocalesRepository;
import comanda.repository.ProductosRepository;
import comanda.service.ComandaServiceException;
import comanda.service.ICategoriasService;
import comanda.service.ILocalesService;
import comanda.service.IProductosService;

@Service
public class ProductosService implements IProductosService {

	private final Logger LOGGER = LoggerFactory.getLogger(ProductosService.class);

	@Autowired
	private ProductosRepository repoProductos;
	@Autowired
	private LocalesRepository repoLocal;

	@Autowired
	private ICategoriasService serviceCategorias;

	@Autowired
	private ILocalesService serviceLocales;

	public List<Producto> buscarTodos() {
		return repoProductos.findAll();
	}

	public Producto guardar(Producto producto, Integer categoriaId, Integer localId) throws ComandaServiceException {
		System.out.println("------------------------------------------------------------");

		LOGGER.info("----- Producto a guardar: " + producto);
		LOGGER.info("----- categoriaId: " + categoriaId);
		LOGGER.info("----- localId: " + localId);

		if (categoriaId != null && localId != null) {
			// Buscar la categoría por ID usando serviceCategorias
			Categoria categoria = serviceCategorias.buscarCategoria(categoriaId);
			Local local = serviceLocales.buscarLocal(localId);

			LOGGER.info("----- categoria: " + categoria);
			LOGGER.info("----- local: " + local);

			if (categoria != null && local != null) {
				producto.setCategoria(categoria);
				// Asignar el local al producto si se encuentra
				producto.setLocal(local);
				LOGGER.info("----- Producto a guardar via el repo: " + producto);
				System.out.println("Guardando " + producto);
				return repoProductos.save(producto);
			} else {
				throw new ComandaServiceException("PS03",
						"La categoría o el local no existen para los IDs proporcionados");
			}
		} else {
			throw new ComandaServiceException("PS04", "El ID de la categoría o el ID del local son nulos");
		}
	}

	public Producto modificar(Producto producto, Integer categoriaId, Integer localId) throws ComandaServiceException {
		System.out.println("------------------------------------------------------------");

		LOGGER.info("----- Producto a guardar: " + producto);
		LOGGER.info("----- categoriaId: " + categoriaId);
		LOGGER.info("----- localId: " + localId);

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
		productoNew.setActivo(producto.getActivo());

		return guardar(producto, categoriaId, localId);
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

	public List<Producto> buscarProductosPorLocal(Integer localId) {

		return repoProductos.findByLocalId(localId);
	}

	@Override
	public Producto guardar(Producto producto) {
		return repoProductos.save(producto);
	}

	public Optional<Producto> findById(int idProducto) {
		return repoProductos.findById(idProducto);
	}
	
	public List<Producto> buscarProductosPorCategoria(Integer categoriaId) {
	    return repoProductos.findByCategoriaId(categoriaId);
	}

	@Override
	public List<Producto> buscarTodosPorLocal(Integer idLocal) {
		Producto prod = new Producto();
		Optional<Local> local = repoLocal.findById(idLocal);
		if (local.isPresent()) {
			prod.setLocal(local.get());
			Example<Producto> example = Example.of(prod);
			return repoProductos.findAll(example);
		}
		return null;
	}
}
