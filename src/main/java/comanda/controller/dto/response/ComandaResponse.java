package comanda.controller.dto.response;

import comanda.entity.Comanda;
import comanda.entity.ItemComanda;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class ComandaResponse {

	private Comanda comanda;
	private List<ItemComanda> items;


}
