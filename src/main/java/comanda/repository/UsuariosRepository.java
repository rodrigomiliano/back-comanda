package comanda.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import comanda.entity.Usuario;

public interface UsuariosRepository extends JpaRepository<Usuario, Integer> {

}
