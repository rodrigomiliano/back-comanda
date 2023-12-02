package comanda.controller.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class ProductoResponse {

	private Integer id;

	private String nombre;

	private String descripcion;

	private Double precio;

	private CategoriaResponse categoria;

	private String imagen;
	
	private LocalResponse local;
}
