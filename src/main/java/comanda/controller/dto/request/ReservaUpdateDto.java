package comanda.controller.dto.request;

import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class ReservaUpdateDto {		
    	
	private Integer clienteId;		
	private Date fecha_alta;		
	private Date fecha_reserva;	
	private Integer mesaId;	
	private Integer estadoId;	
	private Integer turnoId;

}
