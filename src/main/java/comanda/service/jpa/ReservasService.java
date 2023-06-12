package comanda.service.jpa;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import comanda.entity.Reserva;
import comanda.entity.Rol;
import comanda.repository.ReservasRepository;
import comanda.service.IReservasService;

@Service
public class ReservasService implements IReservasService{

	@Autowired
	private ReservasRepository repoReservas;
	
	public List<Reserva> buscarTodas() {
		System.out.println("------------------------------------------------------------");
		List<Reserva> reservas = repoReservas.findAll(); // consola de spring
		System.out.println("Listado de Reservas: ");
		reservas.forEach(t -> {
			System.out.println(t);
		});
		return repoReservas.findAll(); // postman
	}
	
	public void guardar(Reserva reserva) {
		System.out.println("------------------------------------------------------------");
		repoReservas.save(reserva);
		System.out.println("Guardando " + reserva);
	}
	
	public void eliminar(int idReserva) {
		System.out.println("Eliminando registro: " + buscarReserva(idReserva));
		repoReservas.deleteById(idReserva);
	}
	
	public Optional<Reserva> buscarReserva(int idReserva) {
		System.out.println("------------------------------------------------------------");
		Optional<Reserva> optional = repoReservas.findById(idReserva);
		if (optional.isPresent()) {
			Reserva u = optional.get();
			System.out.println("Elegiste " + u);
			return repoReservas.findById(idReserva);
		} else {
			System.out.println("------------------------------------------------------------");
			System.out.println("No existe la Reserva n° " + idReserva);
		}
		return null;
	}
}
