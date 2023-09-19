package comanda.controller;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import comanda.controller.dto.response.MesaResponse;
import comanda.entity.Mesa;
import comanda.service.IMesasService;

@RestController
@RequestMapping("/comanda")
public class MesasController {

	@Autowired
	private IMesasService serviceMesas;

	@GetMapping("/mesa")
	public List<Mesa> buscarTodas() {
		return serviceMesas.buscarTodas();
	}

	@GetMapping("/mesa/{id}")
	public ResponseEntity<MesaResponse> buscarMesa(@PathVariable("id") int idMesa) {
		Optional<Mesa> mesa = serviceMesas.buscarMesa(idMesa);
		MesaResponse mesaResponse = new MesaResponse();
		if(mesa.isPresent()){			
			Mesa mesita = mesa.get();
			mesaResponse.setId(mesita.getId());
			mesaResponse.setSillas(mesita.getSillas());
			return new ResponseEntity<MesaResponse>(mesaResponse, HttpStatus.OK);			
		}
		return new ResponseEntity<MesaResponse>(mesaResponse, HttpStatus.NOT_FOUND);		
		
	}

	@PostMapping("/mesa")
	public Mesa guardar(@RequestBody Mesa mesa) {
		serviceMesas.guardar(mesa);
		return mesa;
	}

	@PutMapping("/mesa")
	public Mesa modificar(@RequestBody Mesa mesa) {
		serviceMesas.guardar(mesa);
		return mesa;
	}

	@DeleteMapping("/mesa/{id}")
	public String eliminar(@PathVariable("id") int idMesa) {
		serviceMesas.eliminar(idMesa);
		return "Registro Eliminado";
	}
}
