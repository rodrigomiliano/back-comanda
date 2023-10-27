package comanda.controller.dto.response;

import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class ReservaResponse {	
	
	private Integer id;
	
	private ClienteResponse cliente;
		
	private Date fecha_alta;
		
	private Date fecha_reserva;
	
	private MesaResponse mesa;
	
	private EstadoResponse estado;
	
	private TurnoResponse turno;

}
