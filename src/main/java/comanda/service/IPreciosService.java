package comanda.service;

import java.util.List;
import java.util.Optional;
import comanda.entity.Precio;

public interface IPreciosService {

	List<Precio> buscarTodos();
	void guardar(Precio precio);
	void eliminar(int idPrecio);
	Optional<Precio> buscarPrecio(int idPrecio);
}
