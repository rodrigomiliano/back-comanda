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
<<<<<<< HEAD
	private Integer sillas;
=======
	
	private Integer sillas;
	
	private String observacion;

<<<<<<< HEAD
>>>>>>> rodri
=======
	private EstadoResponse estado;
>>>>>>> rodri

	private UsuarioResponse usuario;
	
	private LocalResponse local;
}
