package comanda.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import comanda.entity.Comanda;

import java.util.Date;
import java.util.List;

public interface ComandasRepository extends JpaRepository<Comanda, Integer> {

    List<Comanda> findByFechaGreaterThanEqual(Date date);

    List<Comanda> findAllByOrderByFechaDesc();
}
