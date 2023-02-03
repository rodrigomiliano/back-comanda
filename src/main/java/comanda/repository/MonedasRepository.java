package comanda.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import comanda.entity.Moneda;

public interface MonedasRepository extends JpaRepository<Moneda, Integer> {

}
