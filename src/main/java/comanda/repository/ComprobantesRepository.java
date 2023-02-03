package comanda.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import comanda.entity.Comprobante;

public interface ComprobantesRepository extends JpaRepository<Comprobante, Integer> {

}
