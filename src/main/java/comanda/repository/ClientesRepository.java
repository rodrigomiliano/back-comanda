package comanda.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import comanda.entity.Cliente;

public interface ClientesRepository extends JpaRepository<Cliente, Integer> {

}
