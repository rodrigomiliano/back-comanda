package comanda.controller.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class MesaInsertDto {    
	private Integer sillas;	
	private String observacion;
	private Integer estadoId;
	private Integer usuarioId;	
	private Integer localId;
}
