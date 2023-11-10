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

import comanda.controller.dto.request.EstadoInsertDto;
import comanda.controller.dto.request.EstadoUpdateDto;
import comanda.controller.dto.response.EstadoResponse;
import comanda.entity.Estado;
import comanda.service.ComandaServiceException;
import comanda.service.IEstadosService;
import comanda.service.mapper.EstadoMapper;

@RestController
@RequestMapping("/comanda")
public class EstadosController {

    private final Logger LOGGER = LoggerFactory.getLogger(EstadosController.class);

    @Autowired
    private IEstadosService serviceEstados;

    private final EstadoMapper estadoMapper = EstadoMapper.INSTANCE;

    @GetMapping("/estado")
    public List<EstadoResponse> buscarTodos() {
        List<Estado> estados = serviceEstados.buscarTodos();
        List<EstadoResponse> response = estadoMapper.mapToEstadoResponseList(estados);
        return response;
    }

    @GetMapping("/estado/{id}")
    public EstadoResponse buscarEstado(@PathVariable("id") int idEstado) {
        Estado estado = null;
        try {
            estado = serviceEstados.buscarEstado(idEstado);
        } catch (Exception e) {
            // Manejar la excepción apropiadamente
            e.printStackTrace();
        }
        LOGGER.info(">>>>>> Estado: " + estado);
        EstadoResponse estadoResponse = estadoMapper.mapToEstadoDto(estado);
        LOGGER.info(">>>>>> estadoResponse: " + estadoResponse);
        return estadoResponse;
    }

    @PostMapping("/estado")
    public EstadoResponse guardar(@RequestBody EstadoInsertDto estadoDto) throws ComandaServiceException {
        // Crear el estado a insertar
        Estado estado = null;
        estado = estadoMapper.mapToEstado(estadoDto);
        LOGGER.info(">>>>>> Estado luego del mapper : " + estado);

        estado = serviceEstados.guardar(estado);

        EstadoResponse estadoResponse = estadoMapper.mapToEstadoDto(estado);
        LOGGER.info(">>>>>> estadoResponse: " + estadoResponse);

        return estadoResponse;
    }

    @PutMapping("/estado/{id}")
    public EstadoResponse modificar(@PathVariable("id") int idEstado, @RequestBody EstadoUpdateDto estadoDto)
            throws ComandaServiceException {

        Estado estado = null;
        estado = estadoMapper.mapToEstado(estadoDto);
        estado.setId(idEstado);
        LOGGER.info(">>>>>> Estado luego del mapper : " + estado);

        LOGGER.info("idEstado: " + idEstado);
        LOGGER.info("Estado: " + estadoDto.toString());

        estado = serviceEstados.modificar(estado);
        LOGGER.info("Estado guardado: " + estado.toString());

        EstadoResponse estadoResponse = estadoMapper.mapToEstadoDto(estado);
        LOGGER.info(">>>>>> estadoResponse: " + estadoResponse);

        return estadoResponse;
    }

    @DeleteMapping("/estado/{id}")
    public String eliminar(@PathVariable("id") int idEstado) {
        try {
            serviceEstados.eliminar(idEstado);
        } catch (Exception e) {
            // Manejar la excepción apropiadamente
            e.printStackTrace();
        }
        return "Registro Eliminado";
    }
}
