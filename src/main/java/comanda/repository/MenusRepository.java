package comanda.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import comanda.entity.Menu;

public interface MenusRepository extends JpaRepository<Menu, Integer> {

}
