package comanda.service.jpa;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

import comanda.entity.Producto;
import comanda.entity.Usuario;
import comanda.entity.UsuarioLocal;
import comanda.repository.UsuarioLocalesRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import comanda.entity.Local;
import comanda.repository.LocalesRepository;
import comanda.repository.ProductosRepository;
import comanda.service.ComandaServiceException;
import comanda.service.ILocalesService;

@Service
public class LocalesService implements ILocalesService {

    private final Logger LOGGER = LoggerFactory.getLogger(LocalesService.class);

    @Autowired
    private LocalesRepository repoLocales;
    
    @Autowired
    private ProductosRepository repoProductos;

    @Autowired
    private UsuarioLocalesRepository repoUsuarioLocales;

    public List<Local> buscarTodos() {
        System.out.println("------------------------------------------------------------");
        List<Local> locales = repoLocales.findAll();
        System.out.println("Listado de Locales: ");
        locales.forEach(l -> {
            System.out.println(l);
        });
        return repoLocales.findAll();
    }

    public Local guardar(Local local) throws ComandaServiceException {
        System.out.println("------------------------------------------------------------");
        LOGGER.info(">>>>>> Local a guardar: " + local);
        LOGGER.info(">>>>>> Local a guardar via el repo: " + local);
        System.out.println("Guardando " + local);
        return repoLocales.save(local);
    }

    public Local modificar(Local local) throws ComandaServiceException {
        System.out.println("------------------------------------------------------------");
        LOGGER.info(">>>>>> Local a modificar: " + local);

        // Buscar el local existente
        Local localNew = buscarLocal(local.getId());

        // Reemplazo el valor del objeto actualmente en la DB con el valor que se pasa por el Body
        localNew.setNombre(local.getNombre());
        localNew.setCalle(local.getCalle());
        localNew.setAltura(local.getAltura());
        localNew.setCodigo_postal(local.getCodigo_postal());
        localNew.setTelefono(local.getTelefono());
        localNew.setImagen(local.getImagen());
        localNew.setDescripcion(local.getDescripcion());

        LOGGER.info("Local modificado: " + localNew.toString());
        System.out.println("Guardando cambios en " + localNew);

        return repoLocales.save(localNew);
    }


    public void eliminar(int idLocal) throws Exception {
        System.out.println("Eliminando registro: " + buscarLocal(idLocal));
        repoLocales.deleteById(idLocal);
    }

    public Local buscarLocal(int idLocal) throws ComandaServiceException {
        System.out.println("------------------------------------------------------------");
        Optional<Local> optional = repoLocales.findById(idLocal);
        if (optional.isPresent()) {
            Local l = optional.get();
            System.out.println("Elegiste " + l);
            return l;
        } else {
            System.out.println("------------------------------------------------------------");
            System.out.println("No existe el Local n° " + idLocal);
            throw new ComandaServiceException("US001", "No existe el Local n° " + idLocal);
        }
    }       

    @Override
    public List<Local> buscarTodosPorUsuario(Usuario usuario) {
        if (usuario != null) {
            UsuarioLocal exampleUsuarioLocal = new UsuarioLocal();
            exampleUsuarioLocal.setUsuario(usuario);
            Example<UsuarioLocal> example = Example.of(exampleUsuarioLocal);
            return repoUsuarioLocales.findAll(example).stream().map(ul -> ul.getLocal()).collect(Collectors.toList());
        }
        return null;
    }
}
