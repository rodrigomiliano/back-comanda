package comanda.service.jpa;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import comanda.entity.Estado; 
import comanda.repository.EstadosRepository;
import comanda.service.ComandaServiceException;
import comanda.service.IEstadosService; 

@Service
public class EstadosService implements IEstadosService { 

    private final Logger LOGGER = LoggerFactory.getLogger(EstadosService.class);

    @Autowired
    private EstadosRepository repoEstados; 

    public List<Estado> buscarTodos() {
        System.out.println("------------------------------------------------------------");
        List<Estado> estados = repoEstados.findAll(Sort.by("id")); // Esto lo ordena en la consola de spring
        System.out.println("Listado de Estados: ");
        estados.forEach(t -> {
            System.out.println(t);
        });
        return repoEstados.findAll(Sort.by("id")); // Esto lo ordena en postman
    }

    public Estado guardar(Estado estado) throws ComandaServiceException {
        LOGGER.info(">>>>>> Estado a guardar: " + estado);

        LOGGER.info(">>>>>> Estado a guardar via el repo: " + estado);
        System.out.println("Guardando " + estado);

        return repoEstados.save(estado);
    }

    public Estado modificar(Estado estado) throws ComandaServiceException {
        System.out.println("------------------------------------------------------------");

        LOGGER.info(">>>>>> Estado a guardar: " + estado);

        // Buscar Estado existente
        Estado estadoNew = null;
        try {
            estadoNew = buscarEstado(estado.getId());
        } catch (ComandaServiceException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        // Reemplazo el valor del objeto actualmente en la DB con el valor que se pasa
        // por el Body
        estadoNew.setNombre(estado.getNombre());

        LOGGER.info("estado: " + estado.toString());
        LOGGER.info(">>>>>> Estado a guardar via el repo: " + estado);
        System.out.println("Guardando " + estado);

        return guardar(estado);
    }

    public void eliminar(int idEstado) throws Exception {
        System.out.println("Eliminando registro: " + buscarEstado(idEstado));
        repoEstados.deleteById(idEstado);
    }

    public Estado buscarEstado(int idEstado) throws ComandaServiceException {
        System.out.println("------------------------------------------------------------");
        Optional<Estado> optional = repoEstados.findById(idEstado);
        if (optional.isPresent()) {
            Estado u = optional.get();
            System.out.println("Elegiste " + u);
            return u;
        } else {
            System.out.println("------------------------------------------------------------");
            System.out.println("No existe el Estado n° " + idEstado);
            throw new ComandaServiceException("PS002", "No existe el Estado n° " + idEstado);
        }
    }
}
