package comanda.service;

import java.util.List;
import java.util.Optional;
import comanda.entity.Comanda;

public interface IComandasService {

	List<Comanda> buscarTodas();
	void guardar(Comanda comanda);
	void eliminar(int idComanda);
	Optional<Comanda> buscarComanda(int idComanda);
	void crearItemComanda(Comanda comanda);	
}
