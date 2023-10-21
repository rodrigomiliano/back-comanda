package comanda.controller.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class LocalResponse {				
	
	private Integer id;
	
	private String nombre;
	
	private String calle;
	
	private Integer altura;
	
	private Integer codigo_postal;
	
	private Integer telefono;
	
	private String imagen;		
}
