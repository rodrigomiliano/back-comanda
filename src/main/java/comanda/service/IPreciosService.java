package comanda.service;

import java.util.List;
import comanda.entity.Precio;

public interface IPreciosService {

	List<Precio> buscarTodos();
	void guardar(Precio precio);
	void eliminar(int idPrecio);
}
