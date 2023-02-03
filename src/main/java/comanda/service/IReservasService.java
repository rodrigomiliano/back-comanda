package comanda.service;

import java.util.List;
import java.util.Optional;
import comanda.entity.Reserva;

public interface IReservasService {
	
	List<Reserva> buscarTodas();
	void guardar(Reserva reserva);
	void eliminar(int idReserva);
	Optional<Reserva> buscarReserva(int idReserva);

}
