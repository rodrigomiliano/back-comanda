package comanda.service.jpa;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import comanda.entity.*;
import comanda.repository.UsuarioLocalesRepository;
import comanda.repository.UsuariosRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;
import comanda.repository.MesasRepository;
import comanda.service.ComandaServiceException;
import comanda.service.IEstadosService;
import comanda.service.ILocalesService;
import comanda.service.IMesasService;
import comanda.service.IUsuariosService;

@Service
public class MesasService implements IMesasService {
	
	private final Logger LOGGER = LoggerFactory.getLogger(ProductosService.class);

    @Autowired
    private MesasRepository repoMesas;
    @Autowired
    private UsuariosRepository usuariosRepository;
    @Autowired
    private UsuarioLocalesRepository repoUsuarioLocales;
    
    @Autowired
  	private IEstadosService serviceEstados;
    @Autowired
  	private IUsuariosService serviceUsuarios;
    @Autowired
	private ILocalesService serviceLocales;

    public List<Mesa> buscarTodos() {
        return repoMesas.findAll();
    }

    public Mesa guardar(Mesa mesa) throws ComandaServiceException {
        return repoMesas.save(mesa);
    }    
    
	public Mesa guardarbis(Mesa mesa, Integer estadoId, Integer usuarioId, Integer localId) throws ComandaServiceException {
		System.out.println("------------------------------------------------------------");

		LOGGER.info("----- Mesa a guardar: " + mesa);
		LOGGER.info("----- estadoId: " + estadoId);
		LOGGER.info("----- usuarioId: " + usuarioId);
		LOGGER.info("----- localId: " + localId);

		if (estadoId != null && usuarioId != null && localId != null) {
			
			Estado estado = serviceEstados.buscarEstado(estadoId);
			Usuario usuario = serviceUsuarios.buscarUsuario(usuarioId);
			Local local = serviceLocales.buscarLocal(localId);

			LOGGER.info("----- estado: " + estado);
			LOGGER.info("----- usuario: " + usuario);
			LOGGER.info("----- local: " + local);

			if (estadoId != null && usuarioId != null && localId != null) {
				mesa.setEstado(estado);
				mesa.setUsuario(usuario);;
				mesa.setLocal(local);
				LOGGER.info("----- Mesa a guardar via el repo: " + mesa);
				System.out.println("Guardando " + mesa);
				return repoMesas.save(mesa);
			} else {
				throw new ComandaServiceException("PS03",
						"La categoría o el local no existen para los IDs proporcionados");
			}
		} else {
			throw new ComandaServiceException("PS04", "El ID de estado/usuario/local son nulos");
		}
	}

    public Mesa modificar(Mesa mesa) throws ComandaServiceException {
        Mesa mesaNew = buscarMesa(mesa.getId());
        mesaNew.setSillas(mesa.getSillas());
        mesaNew.setObservacion(mesa.getObservacion());
        return guardar(mesa);
    }
    
    public Mesa modificarbis(Mesa mesa, Integer estadoId, Integer usuarioId, Integer localId) throws ComandaServiceException {
		System.out.println("------------------------------------------------------------");

		LOGGER.info("----- Mesa a guardar: " + mesa);
		LOGGER.info("----- estadoId: " + estadoId);
		LOGGER.info("----- usuarioId: " + usuarioId);
		LOGGER.info("----- localId: " + localId);

		// Buscar mesa existente
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

		return guardarbis(mesa, estadoId, usuarioId, localId);
	}


    public void eliminar(int idMesa) throws Exception {
        repoMesas.deleteById(idMesa);
    }

    public Mesa buscarMesa(int idMesa) throws ComandaServiceException {
        Optional<Mesa> optional = repoMesas.findById(idMesa);
        if (optional.isPresent()) {
            Mesa m = optional.get();
            return m;
        } else {
            throw new ComandaServiceException("MS001", "No existe la Mesa n° " + idMesa);
        }
    }

    @Override
    public List<Mesa> buscarPorUsuario(int idUsuario) {
        Optional<Usuario> user = usuariosRepository.findById(idUsuario);
        if (!user.isPresent())
            return new ArrayList<>();

        if (user.get().getRol().getNombre().equalsIgnoreCase("ADMIN")) {
            return repoMesas.findAll();
        }
        else {
            UsuarioLocal usuarioLocal = new UsuarioLocal(user.get());
            Example<UsuarioLocal> exampleUserLocal = Example.of(usuarioLocal);

            List<UsuarioLocal> userLocal = repoUsuarioLocales.findAll(exampleUserLocal);

            if (userLocal.isEmpty())
                return new ArrayList<>();

            Local local = userLocal.get(0).getLocal();
            Mesa mesa = new Mesa(local);
            Example<Mesa> exampleMesa = Example.of(mesa);
            return repoMesas.findAll(exampleMesa);
        }
    }
	
}
