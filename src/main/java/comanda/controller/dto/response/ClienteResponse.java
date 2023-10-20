package comanda.controller.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class ClienteResponse {	
		
	private Integer id;
	
	private String usuario;
	
	private String nombre;
	
	private String apellido;
	
	private Integer dni;
	
	private String email;
	
	private String telefono;
	
	private String contrasena;		
}
