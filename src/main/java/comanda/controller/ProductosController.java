package comanda.controller;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import comanda.controller.dto.request.ProductoUpdateDto;
import comanda.controller.dto.response.ProductoResponse;
import comanda.entity.Categoria;
import comanda.entity.Producto;
import comanda.service.ComandaServiceException;
import comanda.service.ICategoriasService;
import comanda.service.IProductosService;
import comanda.service.mapper.ProductoMapper;

@RestController
@RequestMapping("/comanda")
public class ProductosController {

	private final Logger LOGGER = LoggerFactory.getLogger(ProductosController.class);

	@Autowired
	private IProductosService serviceProductos;
	
	@Autowired
	private ICategoriasService serviceCategorias;

	private final ProductoMapper productoMapper = ProductoMapper.INSTANCE;


	@GetMapping("/producto")
	public List<ProductoResponse> buscarTodos() {
		List<Producto> productos = serviceProductos.buscarTodos();
		List<ProductoResponse> response = productoMapper.mapToProductoResponseList(productos);
		return response;
	}

	@GetMapping("/producto/{id}")
	public ProductoResponse buscarProducto(@PathVariable("id") int idProducto) {
		Producto producto = null;
		try {
			producto = serviceProductos.buscarProducto(idProducto);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		LOGGER.info(">>>>>> Producto: " + producto);
		LOGGER.info(">>>>>> Producto Categoria: " + producto.getCategoria());
		//CategoriaResponse categoriaResponse = categoriaMapper.mapToCategoriaResponse(producto.getCategoria());
		//LOGGER.info(">>>>>> categoriaResponse: " + categoriaResponse);

		ProductoResponse productoResponse = productoMapper.mapToProductoDTO(producto);
		LOGGER.info(">>>>>> productoResponse: " + productoResponse);
		//productoResponse.setCategoria(categoriaResponse);
		//LOGGER.info(">>>>>> productoResponse: " + productoResponse);
		return productoResponse;
	}

	@PostMapping("/producto")
	public Producto guardar(@RequestBody Producto producto) {
		serviceProductos.guardar(producto);
		// se podria hacer un find del producto enviado antes de mostrar, esto para no
		// ver el nombre null
		return producto;
		// return Optional<Producto> buscarProducto(0);
	}

	@PutMapping("/producto")
	public Producto modificar(@RequestBody Producto producto) {
		serviceProductos.guardar(producto);		
		return producto;
	}

	@PutMapping("/producto/{id}")
	public Producto modificar(@PathVariable("id") int idProducto, @RequestBody ProductoUpdateDto productoDto) throws ComandaServiceException {

		LOGGER.info("idProducto: " + idProducto);
		LOGGER.info("Producto: " + productoDto.toString());
		
		// Verificar si categoriaId no es null antes de usarlo
	    if (productoDto.getCategoriaId() != null) {
		// Buscar el producto existente
		Producto prod = null;
		try {
			prod = serviceProductos.buscarProducto(idProducto);
		} catch (ComandaServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// Reemplazo el valor del objeto actualmente en la DB con el valor que se pasa
		// por el Body
		prod.setNombre(productoDto.getNombre());
		prod.setDescripcion(productoDto.getDescripcion());
		prod.setPrecio(productoDto.getPrecio());
		prod.setImagen(productoDto.getImagen());
		
		// Buscar la categoría por ID usando serviceCategorias
        Categoria categoria = serviceCategorias.buscarCategoria(productoDto.getCategoriaId());
    
        if (categoria != null) {
            prod.setCategoria(categoria);
        } else {
            // Manejar el caso en el que la categoría no se encuentra
            // Puedes lanzar una excepción o manejarlo de otra manera según tus necesidades
        }
		
		LOGGER.info("Categoria actual: " + prod.getCategoria());
		//LOGGER.info("Categoria nueva: " + producto.getCategoriaId());
		// Categoria nuevaCategoria =
		// categoriaService.buscarPorId(producto.getCategoriaId());
		// prod.setCategoria(nuevaCategoria );

		LOGGER.info("prod: " + prod.toString());

		serviceProductos.guardar(prod);
		LOGGER.info("Producto guardado: " + prod.toString());

		return prod;
	    } else {
	        // Manejar el caso en el que categoriaId es null
	        // Puedes lanzar una excepción o manejarlo de otra manera según tus necesidades
	        return null;
	    }
	}

	@DeleteMapping("/producto/{id}")
	public String eliminar(@PathVariable("id") int idProducto) {
		try {
			serviceProductos.eliminar(idProducto);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "Registro Eliminado";
	}
}
