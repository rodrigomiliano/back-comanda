package comanda.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import comanda.entity.MenuItem;

public interface MenuItemsRepository extends JpaRepository<MenuItem, Integer> {

}
