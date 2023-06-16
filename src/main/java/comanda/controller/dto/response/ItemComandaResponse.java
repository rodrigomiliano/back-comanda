package comanda.controller.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class ItemComandaResponse {


	private Integer id;

	private ComandaResponse comanda;

	private ProductoResponse producto;

	private Double precio;

	private Integer cantidad;

	private Double total;

}
