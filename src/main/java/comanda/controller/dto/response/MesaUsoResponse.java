package comanda.controller.dto.response;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;



@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder


public class MesaUsoResponse {

	private Integer id;
	private MesaResponse mesa;
	@JsonManagedReference
	private List<ComandaResponse> comandas;



}
