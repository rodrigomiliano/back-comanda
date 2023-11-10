package comanda.controller.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class TurnoResponse {
	
	private Integer id;
	
	private String horario;

	private EstadoResponse estado;
	
}
