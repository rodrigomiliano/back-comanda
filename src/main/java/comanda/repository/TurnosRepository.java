package comanda.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import comanda.entity.Turno;

public interface TurnosRepository extends JpaRepository<Turno, Integer> {

}
