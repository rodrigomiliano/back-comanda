package comanda.service;

import java.util.List;
import comanda.entity.Turno; 

public interface ITurnosService { 

    List<Turno> buscarTodos(); 
    Turno guardar(Turno turno, Integer estadoId) throws ComandaServiceException; 
    Turno modificar(Turno turno, Integer estadoId) throws ComandaServiceException; 
    void eliminar(int idTurno) throws Exception; 
    Turno buscarTurno(int idTurno) throws ComandaServiceException; 
}
