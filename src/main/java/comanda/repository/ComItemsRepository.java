package comanda.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import comanda.entity.ComItem;

public interface ComItemsRepository extends JpaRepository<ComItem, Integer> {

}
