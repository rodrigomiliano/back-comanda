package comanda.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import comanda.entity.Producto;

public interface ProductosRepository extends JpaRepository<Producto, Integer> {

}
