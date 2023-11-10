package comanda.service;

import java.util.List;
import comanda.entity.Producto;

public interface IProductosService {

	List<Producto> buscarTodos();
	Producto guardar(Producto producto, Integer categoriaId) throws ComandaServiceException;
	Producto modificar(Producto producto, Integer categoriaId) throws ComandaServiceException;
	void eliminar(int idProducto) throws Exception;
	Producto buscarProducto(int idProducto) throws ComandaServiceException;
}
