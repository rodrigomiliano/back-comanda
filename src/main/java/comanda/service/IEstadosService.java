package comanda.service;

import java.util.List;
import comanda.entity.Estado;

public interface IEstadosService {

    List<Estado> buscarTodos();
    Estado guardar(Estado estado) throws ComandaServiceException;
    Estado modificar(Estado estado) throws ComandaServiceException;
    void eliminar(int idEstado) throws Exception;
    Estado buscarEstado(int idEstado) throws ComandaServiceException;
}
