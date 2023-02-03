package comanda.service;

import java.util.List;
import java.util.Optional;
import comanda.entity.Moneda;

public interface IMonedasService {

	List<Moneda> buscarTodas();
	void guardar(Moneda moneda);
	void eliminar(int idMoneda);
	Optional<Moneda> buscarMoneda(int idMoneda);
}
