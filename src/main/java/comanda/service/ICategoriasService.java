package comanda.service;

import java.util.List;
import comanda.entity.Categoria;
import comanda.entity.Local;

public interface ICategoriasService {

	List<Categoria> buscarTodas();
	Categoria guardar(Categoria categoria) throws ComandaServiceException;
	Categoria modificar(Categoria categoria) throws ComandaServiceException;
	void eliminar(int idCategoria) throws Exception;
	Categoria buscarCategoria(int idCategoria) throws ComandaServiceException;
	List<Local> obtenerLocalesPorCategoria(int idCategoria);
}
