package comanda.controller;

import java.util.List;
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
import comanda.controller.dto.request.CategoriaInsertDto;
import comanda.controller.dto.request.CategoriaUpdateDto;
import comanda.controller.dto.response.CategoriaResponse;
import comanda.entity.Categoria;
import comanda.service.ComandaServiceException;
import comanda.service.ICategoriasService;
import comanda.service.mapper.CategoriaMapper;


@RestController
@RequestMapping("/comanda")
public class CategoriasController {

	private final Logger LOGGER = LoggerFactory.getLogger(ProductosController.class);

	@Autowired
	private ICategoriasService serviceCategorias;	

	private final CategoriaMapper categoriaMapper = CategoriaMapper.INSTANCE;

	@GetMapping("/categoria")
	public List<CategoriaResponse> buscarTodas() {
		List<Categoria> categorias = serviceCategorias.buscarTodas();
		List<CategoriaResponse> response = categoriaMapper.mapToCategoriaResponseList(categorias);
		return response;
	}

	@GetMapping("/categoria/{id}")
	public CategoriaResponse buscarCategoria(@PathVariable("id") int idCategoria) {
		Categoria categoria = null;
		try {
			categoria = serviceCategorias.buscarCategoria(idCategoria);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		LOGGER.info(">>>>>> Categoria: " + categoria);
		CategoriaResponse categoriaResponse = categoriaMapper.mapToCategoriaDto(categoria);
		LOGGER.info(">>>>>> categoriaResponse: " + categoriaResponse);
		return categoriaResponse;
	}

	@PostMapping("/categoria")
	public CategoriaResponse guardar(@RequestBody CategoriaInsertDto categoriaDto) throws ComandaServiceException {

		// Creamos categoria a insertar
		Categoria categoria = null;
		categoria = categoriaMapper.mapToCategoria(categoriaDto);
		LOGGER.info(">>>>>> Categoria luego del mapper : " + categoria);

		categoria = serviceCategorias.guardar(categoria);

		CategoriaResponse categoriaResponse = categoriaMapper.mapToCategoriaDto(categoria);
		LOGGER.info(">>>>>> categoriaResponse: " + categoriaResponse);

		return categoriaResponse;		
	}

	/*@PutMapping("/categoria")
	public Categoria modificar(@RequestBody Categoria categoria) throws ComandaServiceException {
		serviceCategorias.guardar(categoria);
		return categoria;
	}*/

	@PutMapping("/categoria/{id}")
	public CategoriaResponse modificar(@PathVariable("id") int idCategoria, @RequestBody CategoriaUpdateDto categoriaDto)
			throws ComandaServiceException {

		Categoria categoria = null;
		categoria = categoriaMapper.mapToCategoria(categoriaDto);
		categoria.setId(idCategoria);
		LOGGER.info(">>>>>> Categoria luego del mapper : " + categoria);

		LOGGER.info("idCategoria: " + idCategoria);
		LOGGER.info("Categoria: " + categoriaDto.toString());

		categoria = serviceCategorias.modificar(categoria);
		LOGGER.info("Categoria guardada: " + categoria.toString());

		CategoriaResponse categoriaResponse = categoriaMapper.mapToCategoriaDto(categoria);
		LOGGER.info(">>>>>> categoriaResponse: " + categoriaResponse);

		return categoriaResponse;
	}

	@DeleteMapping("/categoria/{id}")
	public String eliminar(@PathVariable("id") int idCategoria) {
		try {
			serviceCategorias.eliminar(idCategoria);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "Registro Eliminado";
	}

}
