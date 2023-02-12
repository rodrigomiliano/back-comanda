package comanda.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import comanda.entity.Estado;

public interface EstadosRepository extends JpaRepository<Estado, Integer> {

}
