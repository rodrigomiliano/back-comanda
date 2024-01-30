package comanda.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import comanda.entity.Local;

public interface LocalesRepository extends JpaRepository<Local, Integer> {

	@Query(value = "SELECT l.*, " + "MAX(p.PRODUC_ID) AS PRODUC_ID, MAX(p.PRODUC_NOMBRE) AS PRODUC_NOMBRE, "
			+ "MAX(p.PRODUC_DESCRP) AS PRODUC_DESCRP, MAX(p.PRODUC_PRECIO) AS PRODUC_PRECIO, "
			+ "MAX(p.PRODUC_CATEGO) AS PRODUC_CATEGO, MAX(p.PRODUC_IMG) AS PRODUC_IMG, MAX(p.PRODUC_LOCAL) AS PRODUC_LOCAL "
			+ "FROM LOCALES l INNER JOIN PRODUCTOS p ON l.LOCAL_ID = p.PRODUC_LOCAL "
			+ "WHERE p.PRODUC_CATEGO = :categoriaId " + "GROUP BY l.LOCAL_ID", nativeQuery = true)
	List<Object[]> findLocalesWithMaxProductByCategoriaId(int categoriaId);

}
