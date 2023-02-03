package comanda.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import comanda.entity.Reserva;

public interface ReservasRepository extends JpaRepository<Reserva, Integer> {

}
