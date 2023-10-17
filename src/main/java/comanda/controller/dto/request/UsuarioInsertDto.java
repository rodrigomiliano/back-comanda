package comanda.controller.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class UsuarioInsertDto {  	
	private String usuario;	
	private String nombre;	
	private String apellido;	
	private Integer dni;	
	private String email;	
	private String telefono;	
	private String contrasena;	
	private Integer rolId;
}
