package comanda.controller.dto.response;

import java.util.List;

import comanda.entity.UsuarioLocal;
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
	
	private List<UsuarioLocal> usuariosLocales;
}
