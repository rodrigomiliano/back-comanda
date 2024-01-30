package comanda.controller.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class CategoriaUpdateDto {

	private String nombre;	
	private String imagen;
	private Boolean destacado;
}
