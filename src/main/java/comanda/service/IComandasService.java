package comanda.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import comanda.entity.Comanda;
import comanda.entity.Estado;
import comanda.entity.Mesa;

public interface IComandasService {

	List<Comanda> buscarTodas();
	Comanda guardar(Comanda comanda);
	void eliminar(int idComanda);
	Optional<Comanda> buscarComanda(int idComanda);
	void crearItemComanda(Comanda comanda);

    Comanda buscarComandaPorMesaPorEstado(Mesa mesa, Estado estado);

    List<Comanda> buscarComandaPorFecha(Date date);
}
