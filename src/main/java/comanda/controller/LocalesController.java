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

import comanda.controller.dto.request.LocalInsertDto;
import comanda.controller.dto.request.LocalUpdateDto;
import comanda.controller.dto.response.LocalResponse;
import comanda.entity.Local;
import comanda.service.ComandaServiceException;
import comanda.service.ILocalesService;
import comanda.service.mapper.LocalMapper;

@RestController
@RequestMapping("/comanda")
public class LocalesController {

    private final Logger LOGGER = LoggerFactory.getLogger(LocalesController.class);

    @Autowired
    private ILocalesService serviceLocales;

    private final LocalMapper localMapper = LocalMapper.INSTANCE;

    @GetMapping("/local")
    public List<LocalResponse> buscarTodos() {
        List<Local> locales = serviceLocales.buscarTodos();
        List<LocalResponse> response = localMapper.mapToLocalResponseList(locales);
        return response;
    }

    @GetMapping("/local/{id}")
    public LocalResponse buscarLocal(@PathVariable("id") int idLocal) {
        Local local = null;
        try {
            local = serviceLocales.buscarLocal(idLocal);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        LOGGER.info(">>>>>> Local: " + local);
        
        LocalResponse localResponse = localMapper.mapToLocalDto(local);
        LOGGER.info(">>>>>> localResponse: " + localResponse);
        return localResponse;
    }

    @PostMapping("/local")
    public LocalResponse guardar(@RequestBody LocalInsertDto localDto) throws ComandaServiceException {

        // Creamos el Local a insertar
        Local local = null;
        local = localMapper.mapToLocal(localDto);
        LOGGER.info(">>>>>> Local luego del mapper : " + local);

        local = serviceLocales.guardar(local);

        LocalResponse localResponse = localMapper.mapToLocalDto(local);
        LOGGER.info(">>>>>> localResponse: " + localResponse);

        return localResponse;
    }

    @PutMapping("/local/{id}")
    public LocalResponse modificar(@PathVariable("id") int idLocal, @RequestBody LocalUpdateDto localDto)
            throws ComandaServiceException {

        Local local = null;
        local = localMapper.mapToLocal(localDto);
        local.setId(idLocal);
        LOGGER.info(">>>>>> Local luego del mapper : " + local);

        LOGGER.info("idLocal: " + idLocal);
        LOGGER.info("Local: " + localDto.toString());

        local = serviceLocales.modificar(local);
        LOGGER.info("Local guardado: " + local.toString());

        LocalResponse localResponse = localMapper.mapToLocalDto(local);
        LOGGER.info(">>>>>> localResponse: " + localResponse);

        return localResponse;
    }

    @DeleteMapping("/local/{id}")
    public String eliminar(@PathVariable("id") int idLocal) {
        try {
            serviceLocales.eliminar(idLocal);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return "Registro Eliminado";
    }
}
