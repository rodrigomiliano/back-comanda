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


public class MesaUsoDTO {

	private Integer id;
	private MesaDTO mesa;
	@JsonManagedReference
	private List<ComandaDTO> comandas;



}
