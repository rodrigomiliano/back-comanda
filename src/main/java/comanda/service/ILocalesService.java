package comanda.service;

import java.util.List;
import comanda.entity.Local;

public interface ILocalesService {

    List<Local> buscarTodos();
    Local guardar(Local local) throws ComandaServiceException;
    Local modificar(Local local) throws ComandaServiceException;
    void eliminar(int idLocal) throws Exception;
    Local buscarLocal(int idLocal) throws ComandaServiceException;
}
