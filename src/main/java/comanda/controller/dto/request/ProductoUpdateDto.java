package comanda.controller.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class ProductoUpdateDto {

	private String nombre;
	private String descripcion;
	private Double precio;
	private Integer categoriaId;
	private String imagen;
	private Integer localId;
}
