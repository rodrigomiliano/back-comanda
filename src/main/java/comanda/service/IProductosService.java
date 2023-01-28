package comanda.service;

import java.util.List;
import comanda.entity.Producto;

public interface IProductosService {

	List<Producto> buscarTodos();
	void guardar(Producto producto);
	void eliminar(int idProducto);
}
