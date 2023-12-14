package comanda.service;

import java.util.List;
import comanda.entity.Producto;

public interface IProductosService {

	List<Producto> buscarTodos();
	Producto guardar(Producto producto, Integer categoriaId, Integer localId) throws ComandaServiceException;
	Producto modificar(Producto producto, Integer categoriaId, Integer localId) throws ComandaServiceException;
	void eliminar(int idProducto) throws Exception;
	Producto buscarProducto(int idProducto) throws ComandaServiceException;
	List<Producto> buscarProductosPorLocal(Integer localId);

}
