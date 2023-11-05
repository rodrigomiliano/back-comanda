package comanda.controller.dto.response;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class UsuarioResponse {	
		
	private Integer id;
	
	private String usuario;
	
	private String nombre;
	
	private String apellido;
	
	private Integer dni;
	
	private String email;
	
	private String telefono;
	
	private String contrasena;
	
	private RolResponse rol;
	
	//private List<UsuarioLocalResponse> usuariosLocales;
}
