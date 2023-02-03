package comanda.service;

import java.util.List;
import java.util.Optional;
import comanda.entity.Mesa;

public interface IMesasService {

	List<Mesa> buscarTodas();
	void guardar(Mesa mesa);
	void eliminar(int idMesa);
	Optional<Mesa> buscarMesa(int idMesa);
}
