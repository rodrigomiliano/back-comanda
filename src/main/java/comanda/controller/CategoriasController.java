package comanda.controller;

import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import comanda.controller.dto.request.CategoriaInsertDto;
import comanda.controller.dto.request.CategoriaUpdateDto;
import comanda.controller.dto.response.CategoriaResponse;
import comanda.entity.Categoria;
import comanda.entity.Local;
import comanda.service.ComandaServiceException;
import comanda.service.ICategoriasService;
import comanda.service.mapper.CategoriaMapper;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/comanda")
public class CategoriasController {

	private final Logger LOGGER = LoggerFactory.getLogger(CategoriasController.class);

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
	
	@PutMapping("/categoria/{id}")
	public CategoriaResponse modificar(@PathVariable("id") int idCategoria,
			@RequestBody CategoriaUpdateDto categoriaDto) throws ComandaServiceException {

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
			e.printStackTrace();
		}
		return "Registro Eliminado";
	}
	
	 // Endpoint para obtener locales relacionados con una categoría específica
    @GetMapping("/categoria/{id}/locales")
    public List<Local> obtenerLocalesPorCategoria(@PathVariable("id") int idCategoria) {
        try {
            return serviceCategorias.obtenerLocalesPorCategoria(idCategoria);
        } catch (Exception e) {            
            e.printStackTrace();
            return new ArrayList<>(); 
        }
    }

}
