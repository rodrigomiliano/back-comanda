package comanda.service.jpa;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import comanda.entity.Estado;
import comanda.entity.Local;
import comanda.entity.Mesa;
import comanda.entity.Usuario;
import comanda.repository.MesasRepository;
import comanda.service.ComandaServiceException;
import comanda.service.IEstadosService;
import comanda.service.ILocalesService;
import comanda.service.IMesasService;
import comanda.service.IUsuariosService;

@Service
public class MesasService implements IMesasService {

    private final Logger LOGGER = LoggerFactory.getLogger(MesasService.class);

    @Autowired
    private MesasRepository repoMesas;

    @Autowired
    private IEstadosService serviceEstados;
    
    @Autowired
    private IUsuariosService serviceUsuarios;
    
    @Autowired
    private ILocalesService serviceLocales;

    public List<Mesa> buscarTodos() {
        System.out.println("------------------------------------------------------------");
        List<Mesa> mesas = repoMesas.findAll();
        System.out.println("Listado de Mesas: ");
        mesas.forEach(m -> {
            System.out.println(m);
        });
        return repoMesas.findAll();
    }

    public Mesa guardar(Mesa mesa, Integer estadoId, Integer usuarioId, Integer localId) throws ComandaServiceException {
        System.out.println("------------------------------------------------------------");

        LOGGER.info(">>>>>> Mesa a guardar: " + mesa);
        LOGGER.info(">>>>>> estadoId: " + estadoId);
        LOGGER.info(">>>>>> usuarioId: " + usuarioId);
        LOGGER.info(">>>>>> localId: " + localId);

        if (estadoId != null && usuarioId != null && localId != null)   {
            // Buscar el estado por ID usando serviceEstados
            Estado estado = serviceEstados.buscarEstado(estadoId);
            Usuario usuario = serviceUsuarios.buscarUsuario(usuarioId);
            Local local = serviceLocales.buscarLocal(localId);

            LOGGER.info(">>>>>> estado: " + estado);
            LOGGER.info(">>>>>> usuario: " + usuario);
            LOGGER.info(">>>>>> local: " + local);

            if (estado != null && usuario != null && local != null) {
                mesa.setEstado(estado);
                mesa.setUsuario(usuario);
                mesa.setLocal(local);
            } else {
                throw new ComandaServiceException("MS02", "No existe estado/usuario/local " /*+ estadoId*/);
            }

        } else {
            throw new ComandaServiceException("MS01", "El id del estado/usuario/local es nulo");
        }

        LOGGER.info(">>>>>> Mesa a guardar via el repo: " + mesa);
        System.out.println("Guardando " + mesa);

        return repoMesas.save(mesa);
    }

    public Mesa modificar(Mesa mesa, Integer estadoId, Integer usuarioId, Integer localId) throws ComandaServiceException {
        System.out.println("------------------------------------------------------------");

        LOGGER.info(">>>>>> Mesa a guardar: " + mesa);
        LOGGER.info(">>>>>> estadoId: " + estadoId);
        LOGGER.info(">>>>>> usuarioId: " + usuarioId);
        LOGGER.info(">>>>>> localId: " + localId);

        // Buscar la mesa existente
        Mesa mesaNew = null;
        try {
            mesaNew = buscarMesa(mesa.getId());
        } catch (ComandaServiceException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        // Reemplazo el valor del objeto actualmente en la DB con el valor que se pasa
        // por el Body               
        mesaNew.setSillas(mesa.getSillas());
        mesaNew.setObservacion(mesa.getObservacion());

        LOGGER.info("Estado actual: " + mesa.getEstado());
        LOGGER.info("Usuario actual: " + mesa.getUsuario());
        LOGGER.info("Local actual: " + mesa.getLocal());

        LOGGER.info("mesa: " + mesa.toString());
        LOGGER.info(">>>>>> Mesa a guardar via el repo: " + mesa);
        System.out.println("Guardando " + mesa);

        return guardar(mesa, estadoId, usuarioId, localId);
    }


    public void eliminar(int idMesa) throws Exception {
        System.out.println("Eliminando registro: " + buscarMesa(idMesa));
        repoMesas.deleteById(idMesa);
    }

    public Mesa buscarMesa(int idMesa) throws ComandaServiceException {
        System.out.println("------------------------------------------------------------");
        Optional<Mesa> optional = repoMesas.findById(idMesa);
        if (optional.isPresent()) {
            Mesa m = optional.get();
            System.out.println("Elegiste " + m);
            return m;
        } else {
            System.out.println("------------------------------------------------------------");
            System.out.println("No existe la Mesa n° " + idMesa);
            throw new ComandaServiceException("MS001", "No existe la Mesa n° " + idMesa);
        }
    }
}
