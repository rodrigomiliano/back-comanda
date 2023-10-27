package comanda.service;

import java.util.List;
import comanda.entity.Reserva;

public interface IReservasService {

    List<Reserva> buscarTodas();
    Reserva guardar(Reserva reserva, Integer mesaId, Integer estadoId, Integer turnoId, Integer clienteId) throws ComandaServiceException;
    Reserva modificar(Reserva reserva, Integer mesaId, Integer estadoId, Integer turnoId, Integer clienteId) throws ComandaServiceException;
    void eliminar(int idReserva) throws Exception;
    Reserva buscarReserva(int idReserva) throws ComandaServiceException;
}
