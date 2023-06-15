package comanda.controller.request;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class ItemComandaInsertRequest {
	private Integer productoId;
	private Integer cantidad;

}
