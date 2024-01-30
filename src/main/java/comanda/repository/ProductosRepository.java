package comanda.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import comanda.entity.Producto;

public interface ProductosRepository extends JpaRepository<Producto, Integer> {

	@Query("SELECT p FROM Producto p WHERE p.local.id = :localId")
    List<Producto> findByLocalId(@Param("localId") Integer localId);

	List<Producto> findByCategoriaId(Integer categoriaId);	

}
