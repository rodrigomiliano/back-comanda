package comanda.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import comanda.entity.Venta;

public interface VentasRepository extends JpaRepository<Venta, Integer> {

}
