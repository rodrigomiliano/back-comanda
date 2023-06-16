package comanda.controller;

import java.util.ArrayList;
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

import comanda.entity.Comanda;
import comanda.entity.ItemComanda;
import comanda.controller.dto.request.ItemComandaInsertRequest;
import comanda.controller.dto.response.MesaUsoResponse;
import comanda.entity.MesaUso;
import comanda.service.IMesaUsosService;
import comanda.service.mapper.ItemComandaMapper;
//import comanda.service.mapper.MesaUsoMapper;
import comanda.service.mapper.MesaUsoMapper;

@RestController
@RequestMapping("/comanda")
public class MesaUsosController {

	@Autowired
	private IMesaUsosService serviceMesaUsos;

	private final MesaUsoMapper mesaUsoMapper = MesaUsoMapper.INSTANCE;

	@Autowired
	private ItemComandaMapper itemComandaMapper;

	@GetMapping("/mesauso")
	public List<MesaUso> buscarTodas() {
		return serviceMesaUsos.buscarTodas();
	}

	@GetMapping("/mesauso/all")
	public List<MesaUsoResponse> buscarTodasAll() {
		List<MesaUsoResponse> mesasUsoDTO = new ArrayList<MesaUsoResponse>();
		List<MesaUso> mesasUso = serviceMesaUsos.buscarTodas();

//		List<MesaUsoDTO> resultado = new ArrayList<MesaUsoDTO>();
//		for(MesaUso mesaUso : mesasUso) {
//			MesaUsoDTO dto = MesaUsoDTO.builder().id(mesaUso.getId()).build();
//			resultado.add(dto);
//		}
//		return resultado;

		mesasUsoDTO = mesaUsoMapper.mapToListMesaUsoDTO(mesasUso);

		return mesasUsoDTO;

	}


	@GetMapping("/mesauso/{id}")
	public MesaUso buscarMesaUso(@PathVariable("id") int idMesaUso) {
		MesaUso mesaUso = null;
		try {
			mesaUso = serviceMesaUsos.buscarMesaUso(idMesaUso);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return mesaUso;
	}

	// --------------CERRAR MESA--------------
	@PostMapping("/mesauso/{id}/cerrarmesa")
	public MesaUso buscarMesaUso(@PathVariable("id") @RequestBody MesaUso mesauso) {
		serviceMesaUsos.cerrarMesa(mesauso);
		return mesauso;
	}

	@PostMapping("/mesauso")
	public MesaUso guardar(@RequestBody MesaUso mesauso) {
		serviceMesaUsos.guardar(mesauso);
		return mesauso;
	}

	// --------------CREAR COMANDA--------------
	@PostMapping("/mesauso/{id}/crearcomanda")
	public MesaUso buscarMesaUso2(@PathVariable("id") @RequestBody MesaUso mesauso) {
		serviceMesaUsos.crearComanda(mesauso);
		return mesauso;
	}

	// --------------CREAR ITEMCOMANDA--------------
		@PostMapping("/mesauso/{mesaUsoId}/comanda/{comandaId}/itemcomanda")
		public MesaUso buscarMesaUso3(@PathVariable Integer mesaUsoId, @PathVariable Integer comandaId, @RequestBody ItemComandaInsertRequest itemComandaInsertRequest) {
			MesaUso mesaUso = null;
			ItemComanda itemComanda = itemComandaMapper.mapToItemComanda(itemComandaInsertRequest);

			try {
				mesaUso = serviceMesaUsos.crearItemComanda(mesaUsoId, comandaId, itemComanda);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return mesaUso;
		}
	/*@PostMapping("/mesauso/comanda/{id}/itemcomanda")
	public MesaUso buscarMesaUso3(@PathVariable("id") @RequestBody MesaUso mesauso, Comanda comanda) {
		serviceMesaUsos.crearItemComanda(mesauso, comanda);
		return mesauso;
	}*/

	@PutMapping("/mesauso")
	public MesaUso modificar(@RequestBody MesaUso mesauso) {
		serviceMesaUsos.guardar(mesauso);
		return mesauso;
	}

	@DeleteMapping("/mesauso/{id}")
	public String eliminar(@PathVariable("id") int idMesa) {
		try {
			serviceMesaUsos.eliminar(idMesa);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "Registro Eliminado";
	}
}
