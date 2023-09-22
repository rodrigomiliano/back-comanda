package comanda.service;

import java.util.List;
import java.util.Optional;
import comanda.entity.Categoria;

public interface ICategoriasService {

	List<Categoria> buscarTodas();
	//Categoria buscarCategoria(int idCategoria);
	void guardar(Categoria categoria);
	void eliminar(int idCategoria) throws Exception;
	//Optional<Categoria> buscarCategoria(int idCategoria);
	Categoria buscarCategoria(int idCategoria) throws ComandaServiceException;
}
