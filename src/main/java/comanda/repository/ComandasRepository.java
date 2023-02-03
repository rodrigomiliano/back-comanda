package comanda.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import comanda.entity.Comanda;

public interface ComandasRepository extends JpaRepository<Comanda, Integer> {

}
