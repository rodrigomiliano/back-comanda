package comanda.controller.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class MesaResponse {

	private Integer id;
	
	private Integer sillas;
	
	private String observacion;

	private EstadoResponse estado;

	private UsuarioResponse usuario;
	
	private LocalResponse local;
}
