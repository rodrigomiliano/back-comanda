package comanda.service.jpa;

import java.util.Base64;
import java.util.List;
import java.util.Optional;
import java.util.Properties;

import comanda.entity.UsuarioLocal;
import comanda.form.FormLogin;
import comanda.service.exception.InvalidPasswordException;
import comanda.service.exception.UsuarioNotFoundException;
import org.apache.commons.lang3.RandomStringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import comanda.entity.Rol;
import comanda.entity.Usuario;
import comanda.repository.UsuariosRepository;
import comanda.service.ComandaServiceException;
import comanda.service.IRolesService;
import comanda.service.IUsuariosService;

import javax.mail.*;
import javax.mail.internet.*;

@Service
public  class UsuariosService implements IUsuariosService {

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
        String encodedString = Base64.getEncoder().encodeToString(usuario.getContrasena().getBytes());
        usuario.setContrasena(encodedString);
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
        usuarioNew.setActivo(usuario.getActivo());

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
    @Override
    public Usuario login(FormLogin formLogin) throws UsuarioNotFoundException, InvalidPasswordException {
        if (formLogin == null || formLogin.getEmail() == null || formLogin.getPassword() == null) {
            throw new UsuarioNotFoundException();
        }
        Usuario user = new Usuario();
        String encodedString = Base64.getEncoder().encodeToString(formLogin.getPassword().getBytes());
        formLogin.setPassword(encodedString);
        user.setEmail(formLogin.getEmail());
        Example<Usuario> example = Example.of(user);
        Optional<Usuario> userFound = repoUsuarios.findOne(example);
        if (!userFound.isPresent()) {
            throw new UsuarioNotFoundException();
        }
        if (!userFound.get().getContrasena().equals(encodedString)) {
            throw new InvalidPasswordException();
        }
        return userFound.get();
    }

    @Override
        public void sendEmail(String email) throws MessagingException, ComandaServiceException {
        Usuario exampleUsuario = new Usuario();
        exampleUsuario.setEmail(email);
        Example<Usuario> example = Example.of(exampleUsuario);
        List<Usuario> result = repoUsuarios.findAll(example);
        if (result.size() != 1) {
            throw new ComandaServiceException("Error al enviar email", "Usuario no encontrado");
        }
        Properties prop = new Properties();
        prop.put("mail.smtp.auth", true);
        prop.put("mail.smtp.ssl.enable", "true");
        prop.put("mail.smtp.host", "smtp.gmail.com"); // completar
        prop.put("mail.smtp.port", "465");
        prop.put("mail.smtp.ssl.trust", "smtp.mailtrap.io");
        Session session = Session.getInstance(prop, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                String username = "username"; // completar
                String password = "pass"; // completar
                return new PasswordAuthentication(username, password);
            }
        });

        Message message = new MimeMessage(session);
        message.setFrom(new InternetAddress("from@gmail.com")); // completar
        message.setRecipients(
                Message.RecipientType.TO, InternetAddress.parse(email));
        message.setSubject("Comanda: password reset");

        String newpass = RandomStringUtils.randomAlphabetic(10);
        String msg = "New Password: " + newpass;

        Usuario usuario = result.get(0);
        usuario.setContrasena(newpass);
        repoUsuarios.save(usuario);
        MimeBodyPart mimeBodyPart = new MimeBodyPart();
        mimeBodyPart.setContent(msg, "text/html; charset=utf-8");

        Multipart multipart = new MimeMultipart();
        multipart.addBodyPart(mimeBodyPart);

        message.setContent(multipart);

        Transport.send(message);
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