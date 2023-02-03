package comanda.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import comanda.entity.Rol;

public interface RolesRepository extends JpaRepository<Rol, Integer> {

}
