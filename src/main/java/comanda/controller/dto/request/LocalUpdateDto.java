package comanda.controller.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class LocalUpdateDto {  	
		
	private String nombre;	
	private String calle;	
	private Integer altura;	
	private Integer codigo_postal;	
	private Integer telefono;	
	private String imagen;	
	private String descripcion;
}
