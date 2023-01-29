package comanda.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import comanda.entity.Categoria;

public interface CategoriasRepository extends JpaRepository<Categoria, Integer> {

}
