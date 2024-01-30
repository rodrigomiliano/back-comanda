package comanda.controller;

import java.util.List;
import comanda.form.FormEmail;
import comanda.form.FormLogin;
import comanda.service.exception.InvalidPasswordException;
import comanda.service.exception.RolNoEncontradoException;
import comanda.service.exception.UsuarioNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import comanda.controller.dto.request.UsuarioInsertDto;
import comanda.controller.dto.request.UsuarioUpdateDto;
import comanda.controller.dto.response.UsuarioResponse;
import comanda.entity.Usuario;
import comanda.service.ComandaServiceException;
import comanda.service.IUsuariosService;
import comanda.service.mapper.UsuarioMapper;
import javax.mail.MessagingException;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/comanda")
public class UsuariosController {

    private final Logger LOGGER = LoggerFactory.getLogger(UsuariosController.class);

    @Autowired
    private IUsuariosService serviceUsuarios;

    private final UsuarioMapper usuarioMapper = UsuarioMapper.INSTANCE;

    @GetMapping("/usuario")
    public List<UsuarioResponse> buscarTodos() {
        List<Usuario> usuarios = serviceUsuarios.buscarTodos();
        List<UsuarioResponse> response = usuarioMapper.mapToUsuarioResponseList(usuarios);
        return response;
    }

    @GetMapping("/usuario/{id}")
    public UsuarioResponse buscarUsuario(@PathVariable("id") int idUsuario) {
        Usuario usuario = null;
        try {
            usuario = serviceUsuarios.buscarUsuario(idUsuario);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        LOGGER.info(">>>>>> Usuario: " + usuario);
        LOGGER.info(">>>>>> Usuario Rol: " + usuario.getRol());
        //LOGGER.info(">>>>>> Usuario usrloc: " + usuario.getUsuariosLocales());

        UsuarioResponse usuarioResponse = usuarioMapper.mapToUsuarioDto(usuario);
        LOGGER.info(">>>>>> usuarioResponse: " + usuarioResponse);
        return usuarioResponse;
    }

    @PostMapping("/usuario")
    public UsuarioResponse guardar(@RequestBody UsuarioInsertDto usuarioDto) throws ComandaServiceException {

        // Creamos el Usuario a insertar
        Usuario usuario = null;
        usuario = usuarioMapper.mapToUsuario(usuarioDto);
        LOGGER.info(">>>>>> Usuario luego del mapper : " + usuario);

        usuario = serviceUsuarios.guardar(usuario, usuarioDto.getRolId());

        UsuarioResponse usuarioResponse = usuarioMapper.mapToUsuarioDto(usuario);
        LOGGER.info(">>>>>> usuarioResponse: " + usuarioResponse);

        return usuarioResponse;
    }

    @PostMapping("/login")
    public Usuario guardar(@RequestBody FormLogin formLogin) throws RolNoEncontradoException {
        try {
            return serviceUsuarios.login(formLogin);
        } catch (UsuarioNotFoundException | InvalidPasswordException e) {
            return null;
        }
    }

    @PostMapping("/email")
    public void email(@RequestBody FormEmail formEmail) throws RolNoEncontradoException, MessagingException, ComandaServiceException {
        serviceUsuarios.sendEmail(formEmail.getEmail());
    }

    @PutMapping("/usuario/{id}")
    public UsuarioResponse modificar(@PathVariable("id") int idUsuario, @RequestBody UsuarioUpdateDto usuarioDto)
            throws ComandaServiceException {

        Usuario usuario = null;
        usuario = usuarioMapper.mapToUsuario(usuarioDto);
        usuario.setId(idUsuario);
        LOGGER.info(">>>>>> Usuario luego del mapper : " + usuario);

        LOGGER.info("idUsuario: " + idUsuario);
        LOGGER.info("Usuario: " + usuarioDto.toString());

        usuario = serviceUsuarios.modificar(usuario, usuarioDto.getRolId());
        LOGGER.info("Usuario guardado: " + usuario.toString());

        UsuarioResponse usuarioResponse = usuarioMapper.mapToUsuarioDto(usuario);
        LOGGER.info(">>>>>> usuarioResponse: " + usuarioResponse);

        return usuarioResponse;
    }

    @DeleteMapping("/usuario/{id}")
    public String eliminar(@PathVariable("id") int idUsuario) {
        try {
            serviceUsuarios.eliminar(idUsuario);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return "Registro Eliminado";
    }
}