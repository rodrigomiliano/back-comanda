package comanda.service;

import java.util.List;
import comanda.entity.Categoria;

public interface ICategoriasService {
	
	List<Categoria> buscarTodas();
	void guardar(Categoria categoria);
	void eliminar(int idAlbum);
}
