package comanda.service.jpa;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import comanda.entity.Cliente;
import comanda.entity.Estado;
import comanda.entity.Mesa;
import comanda.entity.Reserva;
import comanda.entity.Turno;
import comanda.repository.ReservasRepository;
import comanda.service.ComandaServiceException;
import comanda.service.IClientesService;
import comanda.service.IEstadosService;
import comanda.service.IMesasService;
import comanda.service.IReservasService;
import comanda.service.ITurnosService;

@Service
public class ReservasService implements IReservasService {

    private final Logger LOGGER = LoggerFactory.getLogger(ReservasService.class);

    @Autowired
    private ReservasRepository repoReservas;

    @Autowired
    private IMesasService serviceMesas;
    
    @Autowired
    private IEstadosService serviceEstados;
    
    @Autowired
    private ITurnosService serviceTurnos;
    
    @Autowired
    private IClientesService serviceClientes;

    public List<Reserva> buscarTodas() {
        System.out.println("------------------------------------------------------------");
        List<Reserva> reservas = repoReservas.findAll();
        System.out.println("Listado de Reservas: ");
        reservas.forEach(r -> {
            System.out.println(r);
        });
        return repoReservas.findAll();
    }

    public Reserva guardar(Reserva reserva, Integer mesaId, Integer estadoId, Integer turnoId, Integer clienteId) throws ComandaServiceException {
        System.out.println("------------------------------------------------------------");

        LOGGER.info(">>>>>> Reserva a guardar: " + reserva);
        LOGGER.info(">>>>>> mesaId: " + mesaId);
        LOGGER.info(">>>>>> estadoId: " + estadoId);
        LOGGER.info(">>>>>> turnoId: " + turnoId);
        LOGGER.info(">>>>>> clienteId: " + clienteId);

        if (mesaId != null && estadoId != null && turnoId != null && clienteId != null) {
            // Buscar la mesa por ID usando serviceMesas
            Mesa mesa = serviceMesas.buscarMesa(mesaId);
            Estado estado = serviceEstados.buscarEstado(estadoId);
            Turno turno = serviceTurnos.buscarTurno(turnoId);
            Cliente cliente = serviceClientes.buscarCliente(clienteId);

            LOGGER.info(">>>>>> mesa: " + mesa);
            LOGGER.info(">>>>>> estado: " + estado);
            LOGGER.info(">>>>>> turno: " + turno);
            LOGGER.info(">>>>>> cliente: " + cliente);

            if (mesa != null && estado != null && turno != null && cliente != null) {
                reserva.setMesa(mesa);
                reserva.setEstado(estado);
                reserva.setTurno(turno);
                reserva.setCliente(cliente);
            } else {
                throw new ComandaServiceException("RS02", "No existe mesa/estado/turno/cliente " /*+ mesaId*/);
            }

        } else {
            throw new ComandaServiceException("RS01", "El id de la mesa/estado/turno/cliente es nulo");
        }

        LOGGER.info(">>>>>> Reserva a guardar via el repo: " + reserva);
        System.out.println("Guardando " + reserva);

        return repoReservas.save(reserva);
    }

    public Reserva modificar(Reserva reserva, Integer mesaId, Integer estadoId, Integer turnoId, Integer clienteId) throws ComandaServiceException {
        System.out.println("------------------------------------------------------------");

        LOGGER.info(">>>>>> Reserva a guardar: " + reserva);
        LOGGER.info(">>>>>> mesaId: " + mesaId);
        LOGGER.info(">>>>>> estadoId: " + estadoId);
        LOGGER.info(">>>>>> turnoId: " + turnoId);
        LOGGER.info(">>>>>> clienteId: " + clienteId);

        // Buscar la reserva existente
        Reserva reservaNew = null;
        try {
            reservaNew = buscarReserva(reserva.getId());
        } catch (ComandaServiceException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        // Reemplazo el valor del objeto actualmente en la DB con el valor que se pasa
        // por el Body
        // reservaNew.setFecha_alta(reserva.getFecha_alta());//no deberia poder setear esto
        reservaNew.setFecha_reserva(reserva.getFecha_reserva());
        
        LOGGER.info("Mesa actual: " + reserva.getMesa());
        LOGGER.info("Estado actual: " + reserva.getEstado());
        LOGGER.info("Turno actual: " + reserva.getTurno());
        LOGGER.info("Cliente actual: " + reserva.getCliente());

        LOGGER.info("res: " + reserva.toString());
        LOGGER.info(">>>>>> Reserva a guardar via el repo: " + reserva);
        System.out.println("Guardando " + reserva);

        return guardar(reserva, mesaId, estadoId, turnoId, clienteId);
    }

    public void eliminar(int idReserva) throws Exception {
        System.out.println("Eliminando registro: " + buscarReserva(idReserva));
        repoReservas.deleteById(idReserva);
    }

    public Reserva buscarReserva(int idReserva) throws ComandaServiceException {
        System.out.println("------------------------------------------------------------");
        Optional<Reserva> optional = repoReservas.findById(idReserva);
        if (optional.isPresent()) {
            Reserva r = optional.get();
            System.out.println("Elegiste " + r);
            return r;
        } else {
            System.out.println("------------------------------------------------------------");
            System.out.println("No existe la Reserva n° " + idReserva);
            throw new ComandaServiceException("RS001", "No existe la Reserva n° " + idReserva);
        }
    }
}
