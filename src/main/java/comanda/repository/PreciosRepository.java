package comanda.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import comanda.entity.Precio;

public interface PreciosRepository extends JpaRepository<Precio, Integer> {

}
