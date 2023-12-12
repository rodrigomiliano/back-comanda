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

import comanda.controller.dto.request.MesaInsertDto;
import comanda.controller.dto.request.MesaUpdateDto;
import comanda.controller.dto.response.MesaResponse;
import comanda.entity.Mesa;
import comanda.service.ComandaServiceException;
import comanda.service.IMesasService;
import comanda.service.mapper.MesaMapper;

@RestController
@RequestMapping("/comanda")
public class MesasController {

    private final Logger LOGGER = LoggerFactory.getLogger(ProductosController.class);

    @Autowired
    private IMesasService serviceMesas;

    private final MesaMapper mesaMapper = MesaMapper.INSTANCE;

    @GetMapping("/mesa")
    public List<MesaResponse> buscarTodos() {
        List<Mesa> mesas = serviceMesas.buscarTodos();
        List<MesaResponse> response = mesaMapper.mapToMesaResponseList(mesas);
        return response;
    }

    @GetMapping("/mesa/{id}")
    public MesaResponse buscarMesa(@PathVariable("id") int idMesa) {
        Mesa mesa = null;
        try {
            mesa = serviceMesas.buscarMesa(idMesa);
        } catch (Exception e) {
            e.printStackTrace();
        }
        LOGGER.info(">>>>>> Mesa: " + mesa);
        LOGGER.info(">>>>>> Mesa, estado, usuario, local: " + mesa.getEstado() + mesa.getUsuario() + mesa.getLocal());

        MesaResponse mesaResponse = mesaMapper.mapToMesaDto(mesa);
        LOGGER.info(">>>>>> mesaResponse: " + mesaResponse);
        return mesaResponse;
    }

    @PostMapping("/mesa")
    public MesaResponse guardar(@RequestBody MesaInsertDto mesaDto) throws ComandaServiceException {

        // Creamos la mesa a insertar
        Mesa mesa = null;
        mesa = mesaMapper.mapToMesa(mesaDto);
        LOGGER.info(">>>>>> Mesa luego del mapper : " + mesa);

        mesa = serviceMesas.guardar(mesa, mesaDto.getEstadoId(), mesaDto.getUsuarioId(), mesaDto.getLocalId());

        MesaResponse mesaResponse = mesaMapper.mapToMesaDto(mesa);
        LOGGER.info(">>>>>> mesaResponse: " + mesaResponse);

        return mesaResponse;
    }

    @PutMapping("/mesa/{id}")
    public MesaResponse modificar(@PathVariable("id") int idMesa, @RequestBody MesaUpdateDto mesaDto)
            throws ComandaServiceException {

        Mesa mesa = null;
        mesa = mesaMapper.mapToMesa(mesaDto);
        mesa.setId(idMesa);
        LOGGER.info(">>>>>> Mesa luego del mapper : " + mesa);

        LOGGER.info("idMesa: " + idMesa);
        LOGGER.info("Mesa: " + mesaDto.toString());

        mesa = serviceMesas.modificar(mesa, mesaDto.getEstadoId(), mesaDto.getUsuarioId(), mesaDto.getLocalId());
        LOGGER.info("Mesa guardada: " + mesa.toString());

        MesaResponse mesaResponse = mesaMapper.mapToMesaDto(mesa);
        LOGGER.info(">>>>>> mesaResponse: " + mesaResponse);

        return mesaResponse;

    }

    @DeleteMapping("/mesa/{id}")
    public String eliminar(@PathVariable("id") int idMesa) {
        try {
            serviceMesas.eliminar(idMesa);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "Registro Eliminado";
    }
}
