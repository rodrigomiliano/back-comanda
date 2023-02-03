package comanda.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import comanda.entity.Mesa;

public interface MesasRepository extends JpaRepository<Mesa, Integer> {

}
