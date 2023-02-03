package comanda.controller;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import comanda.entity.Comprobante;
import comanda.service.IComprobantesService;

@RestController
@RequestMapping("/comanda")
public class ComprobantesController {

	@Autowired
	private IComprobantesService serviceComprobantes;
	
	@GetMapping("/comprobante")
	public List<Comprobante> buscarTodos(){
		return serviceComprobantes.buscarTodos();
	}
	
	@GetMapping("/comprobante/{id}")
	public Optional<Comprobante> buscarComprobante(@PathVariable("id") int idComprobante) {
		return serviceComprobantes.buscarComprobante(idComprobante);
	}
	
	@PostMapping("/comprobante") 
	public Comprobante guardar(@RequestBody Comprobante comprobante) {
		serviceComprobantes.guardar(comprobante);
		return comprobante;
	}
	
	@PutMapping("/comprobante")
	public Comprobante modificar(@RequestBody Comprobante comprobante) {
		serviceComprobantes.guardar(comprobante);
		return comprobante;
	} 		
		
	@DeleteMapping("/comprobante/{id}")
	public String eliminar(@PathVariable("id") int idComprobante) {
		serviceComprobantes.eliminar(idComprobante);
		return "Registro Eliminado";
	}
}
