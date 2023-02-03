package comanda.service.jpa;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import comanda.entity.Reserva;
import comanda.repository.ReservasRepository;
import comanda.service.IReservasService;

@Service
public class ReservasService implements IReservasService{

	@Autowired
	private ReservasRepository repoReservas;

	public List<Reserva> buscarTodas() {
		return repoReservas.findAll();
	}

	public void guardar(Reserva reserva) {
		repoReservas.save(reserva);
	}

	public void eliminar(int idReserva) {
		repoReservas.deleteById(idReserva);
	}
	
	public Optional<Reserva> buscarReserva(int idReserva) {
		return repoReservas.findById(idReserva);
	}
}
