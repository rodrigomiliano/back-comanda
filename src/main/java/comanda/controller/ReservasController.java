package comanda.controller;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import comanda.controller.dto.request.ReservaInsertDto;
import comanda.controller.dto.request.ReservaUpdateDto;
import comanda.controller.dto.response.ReservaResponse;
import comanda.entity.Reserva;
import comanda.service.ComandaServiceException;
import comanda.service.IReservasService;
import comanda.service.mapper.ReservaMapper;

@RestController
@RequestMapping("/comanda")
public class ReservasController {

    private final Logger LOGGER = LoggerFactory.getLogger(ReservasController.class);

    @Autowired
    private IReservasService serviceReservas;

    private final ReservaMapper reservaMapper = ReservaMapper.INSTANCE;

    @GetMapping("/reserva")
    public List<ReservaResponse> buscarTodas() {
        List<Reserva> reservas = serviceReservas.buscarTodas();
        List<ReservaResponse> response = reservaMapper.mapToReservaResponseList(reservas);
        return response;
    }

    @GetMapping("/reserva/{id}")
    public ReservaResponse buscarReserva(@PathVariable("id") int idReserva) {
        Reserva reserva = null;
        try {
            reserva = serviceReservas.buscarReserva(idReserva);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        LOGGER.info(">>>>>> Reserva: " + reserva);
        LOGGER.info(">>>>>> Reserva, mesa, estado, turno, cliente: " + reserva.getMesa()+ reserva.getEstado() + reserva.getTurno() + reserva.getCliente());
        
        ReservaResponse reservaResponse = reservaMapper.mapToReservaDto(reserva);
        LOGGER.info(">>>>>> reservaResponse: " + reservaResponse);
        return reservaResponse;
    }

    @PostMapping("/reserva")
    public ReservaResponse guardar(@RequestBody ReservaInsertDto reservaDto) throws ComandaServiceException {

        // Creamos la reserva a insertar
        Reserva reserva = null;
        reserva = reservaMapper.mapToReserva(reservaDto);
        LOGGER.info(">>>>>> Reserva luego del mapper : " + reserva);

        reserva = serviceReservas.guardar(reserva, reservaDto.getMesaId(), reservaDto.getEstadoId(), reservaDto.getTurnoId(), reservaDto.getClienteId());

        ReservaResponse reservaResponse = reservaMapper.mapToReservaDto(reserva);
        LOGGER.info(">>>>>> reservaResponse: " + reservaResponse);

        return reservaResponse;
    }

    @PutMapping("/reserva/{id}")
    public ReservaResponse modificar(@PathVariable("id") int idReserva, @RequestBody ReservaUpdateDto reservaDto)
            throws ComandaServiceException {

        Reserva reserva = null;
        reserva = reservaMapper.mapToReserva(reservaDto);
        reserva.setId(idReserva);
        LOGGER.info(">>>>>> Reserva luego del mapper : " + reserva);

        LOGGER.info("idReserva: " + idReserva);
        LOGGER.info("Reserva: " + reservaDto.toString());

        reserva = serviceReservas.modificar(reserva, reservaDto.getMesaId(), reservaDto.getEstadoId(), reservaDto.getTurnoId(), reservaDto.getClienteId());
        LOGGER.info("Reserva guardada: " + reserva.toString());

        ReservaResponse reservaResponse = reservaMapper.mapToReservaDto(reserva);
        LOGGER.info(">>>>>> reservaResponse: " + reservaResponse);

        return reservaResponse;
    }

    @DeleteMapping("/reserva/{id}")
    public String eliminar(@PathVariable("id") int idReserva) {
        try {
            serviceReservas.eliminar(idReserva);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return "Registro Eliminado";
    }
}
