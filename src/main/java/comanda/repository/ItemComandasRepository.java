package comanda.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import comanda.entity.ItemComanda;

public interface ItemComandasRepository extends JpaRepository<ItemComanda, Integer> {

}
