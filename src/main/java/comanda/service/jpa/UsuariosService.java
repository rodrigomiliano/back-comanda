package comanda.service.jpa;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import comanda.entity.Rol;
import comanda.entity.Usuario;
import comanda.repository.UsuariosRepository;
import comanda.service.ComandaServiceException;
import comanda.service.IRolesService;
import comanda.service.IUsuariosService;

@Service
public class UsuariosService implements IUsuariosService {

    private final Logger LOGGER = LoggerFactory.getLogger(UsuariosService.class);

    @Autowired
    private UsuariosRepository repoUsuarios;

    @Autowired
    private IRolesService serviceRoles;

    public List<Usuario> buscarTodos() {
        System.out.println("------------------------------------------------------------");
        List<Usuario> usuarios = repoUsuarios.findAll();
        System.out.println("Listado de Usuarios: ");
        usuarios.forEach(u -> {
            System.out.println(u);
        });
        return repoUsuarios.findAll();
    }

    public Usuario guardar(Usuario usuario, Integer rolId) throws ComandaServiceException {
        System.out.println("------------------------------------------------------------");

        LOGGER.info(">>>>>> Usuario a guardar: " + usuario);
        LOGGER.info(">>>>>> rolId: " + rolId);

        if (rolId != null) {
            // Buscar el rol por ID usando serviceRoles
            Rol rol = serviceRoles.buscarRol(rolId);

            LOGGER.info(">>>>>> rol: " + rol);

            if (rol != null) {
                usuario.setRol(rol);
            } else {
                throw new ComandaServiceException("US02", "No existe rol para el id: " + rolId);
            }

        } else {
            throw new ComandaServiceException("US01", "El id del rol es nulo");
        }

        LOGGER.info(">>>>>> Usuario a guardar via el repo: " + usuario);
        System.out.println("Guardando " + usuario);

        return repoUsuarios.save(usuario);
    }

    public Usuario modificar(Usuario usuario, Integer rolId) throws ComandaServiceException {
        System.out.println("------------------------------------------------------------");

        LOGGER.info(">>>>>> Usuario a guardar: " + usuario);
        LOGGER.info(">>>>>> rolId: " + rolId);

        // Buscar el usuario existente
        Usuario usuarioNew = null;
        try {
            usuarioNew = buscarUsuario(usuario.getId());
        } catch (ComandaServiceException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        // Reemplazo el valor del objeto actualmente en la DB con el valor que se pasa
        // por el Body
        usuarioNew.setUsuario(usuario.getUsuario());
        usuarioNew.setNombre(usuario.getNombre());
        usuarioNew.setApellido(usuario.getApellido());
        usuarioNew.setDni(usuario.getDni());
        usuarioNew.setEmail(usuario.getEmail());
        usuarioNew.setTelefono(usuario.getTelefono());
        usuarioNew.setContrasena(usuario.getContrasena());        

        LOGGER.info("Rol actual: " + usuario.getRol());

        LOGGER.info("usr: " + usuario.toString());
        LOGGER.info(">>>>>> Usuario a guardar via el repo: " + usuario);
        System.out.println("Guardando " + usuario);

        return guardar(usuario, rolId);
    }

    public void eliminar(int idUsuario) throws Exception {
        System.out.println("Eliminando registro: " + buscarUsuario(idUsuario));
        repoUsuarios.deleteById(idUsuario);
    }

    public Usuario buscarUsuario(int idUsuario) throws ComandaServiceException {
        System.out.println("------------------------------------------------------------");
        Optional<Usuario> optional = repoUsuarios.findById(idUsuario);
        if (optional.isPresent()) {
            Usuario u = optional.get();
            System.out.println("Elegiste " + u);
            return u;
        } else {
            System.out.println("------------------------------------------------------------");
            System.out.println("No existe el Usuario n° " + idUsuario);
            throw new ComandaServiceException("US001", "No existe el Usuario n° " + idUsuario);
        }
    }
}