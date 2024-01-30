package comanda.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import comanda.entity.UsuarioLocal;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UsuarioLocalesRepository extends JpaRepository<UsuarioLocal, Integer> {

    @Query("SELECT p FROM UsuarioLocal p WHERE p.usuario.id = :usuarioId")
    Optional<UsuarioLocal> findByUsuarioId(int usuarioId);
}
