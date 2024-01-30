package comanda.controller;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import comanda.controller.dto.request.ProductoInsertDto;
import comanda.controller.dto.request.ProductoUpdateDto;
import comanda.controller.dto.response.ProductoResponse;
import comanda.entity.Local;
import comanda.entity.Producto;
import comanda.form.FormUsuario;
import comanda.service.ComandaServiceException;
import comanda.service.IProductosService;
import comanda.service.IUsuarioLocalesService;
import comanda.service.mapper.ProductoMapper;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/comanda")
public class ProductosController {

	private final Logger LOGGER = LoggerFactory.getLogger(ProductosController.class);

	@Autowired
	private IProductosService serviceProductos;
	@Autowired
	private IUsuarioLocalesService serviceUsuarioLocales;

	private final ProductoMapper productoMapper = ProductoMapper.INSTANCE;

	@GetMapping("/producto")
	public List<ProductoResponse> buscarTodos() {
		List<Producto> productos = serviceProductos.buscarTodos();
		List<ProductoResponse> response = productoMapper.mapToProductoResponseList(productos);
		return response;
	}

	@PostMapping("/buscarproducto")
	public List<Producto> buscarTodosPorUsuario(@RequestBody FormUsuario formUsuario) {
		if (formUsuario != null && formUsuario.getId() != null) {
			Local local = serviceUsuarioLocales.buscarLocalDeUsuario(formUsuario.getId());
			if (local != null) {
				return serviceProductos.buscarTodosPorLocal(local.getId());
			}
		}
		return serviceProductos.buscarTodos();
	}

	@GetMapping("/producto/{id}")
	public ProductoResponse buscarProducto(@PathVariable("id") int idProducto) {
		Producto producto = null;
		try {
			producto = serviceProductos.buscarProducto(idProducto);
		} catch (Exception e) {			
			e.printStackTrace();
		}
		LOGGER.info(">>>>>> Producto: " + producto);
		LOGGER.info(">>>>>> Producto Categoria: " + producto.getCategoria());
		LOGGER.info(">>>>>> Producto Lcoal: " + producto.getLocal());		

		ProductoResponse productoResponse = productoMapper.mapToProductoDto(producto);
		LOGGER.info(">>>>>> productoResponse: " + productoResponse);	
		return productoResponse;
	}

	@PostMapping("/producto")
	public ProductoResponse guardar(@RequestBody ProductoInsertDto productoDto) throws ComandaServiceException {

		// Creamos el producto a insertar
		Producto producto = null;
		producto = productoMapper.mapToProducto(productoDto);
		LOGGER.info(">>>>>> Producto luego del mapper : " + producto);

		producto = serviceProductos.guardar(producto, productoDto.getCategoriaId(), productoDto.getLocalId());

		ProductoResponse productoResponse = productoMapper.mapToProductoDto(producto);
		LOGGER.info(">>>>>> productoResponse: " + productoResponse);

		return productoResponse;
	}
	
	@PutMapping("/producto/{id}")
	public ProductoResponse modificar(@PathVariable("id") int idProducto, @RequestBody ProductoUpdateDto productoDto)
			throws ComandaServiceException {

		Producto producto = null;
		producto = productoMapper.mapToProducto(productoDto);
		producto.setId(idProducto);
		LOGGER.info(">>>>>> Producto luego del mapper : " + producto);

		LOGGER.info("idProducto: " + idProducto);
		LOGGER.info("Producto: " + productoDto.toString());

		producto = serviceProductos.modificar(producto, productoDto.getCategoriaId(), productoDto.getLocalId());
		LOGGER.info("Producto guardado: " + producto.toString());

		ProductoResponse productoResponse = productoMapper.mapToProductoDto(producto);
		LOGGER.info(">>>>>> productoResponse: " + productoResponse);

		return productoResponse;

	}

	@DeleteMapping("/producto/{id}")
	public String eliminar(@PathVariable("id") int idProducto) {
		try {
			serviceProductos.eliminar(idProducto);
		} catch (Exception e) {			
			e.printStackTrace();
		}
		return "Registro Eliminado";
	}

	@GetMapping("/producto/local/{idLocal}")
	public List<ProductoResponse> buscarProductosPorLocal(@PathVariable("idLocal") int idLocal) {
		List<Producto> productos = serviceProductos.buscarProductosPorLocal(idLocal);
		List<ProductoResponse> response = productoMapper.mapToProductoResponseList(productos);
		return response;
	}
	
	@GetMapping("/producto/categoria/{idCategoria}")
	public List<ProductoResponse> buscarProductosPorCategoria(@PathVariable("idCategoria") int idCategoria) {
	    List<Producto> productos = serviceProductos.buscarProductosPorCategoria(idCategoria);
	    List<ProductoResponse> response = productoMapper.mapToProductoResponseList(productos);
	    return response;
	}	
}
