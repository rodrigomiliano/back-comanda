package comanda.service;

import java.util.List;
import comanda.entity.Cliente;

public interface IClientesService {

    List<Cliente> buscarTodos();
    Cliente guardar(Cliente cliente) throws ComandaServiceException;
    Cliente modificar(Cliente cliente) throws ComandaServiceException;
    void eliminar(int idCliente) throws Exception;
    Cliente buscarCliente(int idCliente) throws ComandaServiceException;
}
