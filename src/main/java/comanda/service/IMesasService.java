package comanda.service;

import java.util.List;
import comanda.entity.Mesa;

public interface IMesasService {

    List<Mesa> buscarTodos();
    Mesa guardar(Mesa mesa, Integer estadoId, Integer usuarioId, Integer localId) throws ComandaServiceException;
    Mesa modificar(Mesa mesa, Integer estadoId, Integer usuarioId, Integer localId) throws ComandaServiceException;
    void eliminar(int idMesa) throws Exception;
    Mesa buscarMesa(int idMesa) throws ComandaServiceException;
}
