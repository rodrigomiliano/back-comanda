package comanda.service;

import java.util.List;
import comanda.entity.Usuario;
import comanda.form.FormLogin;
import comanda.service.exception.InvalidPasswordException;
import comanda.service.exception.UsuarioNotFoundException;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;

public interface IUsuariosService {

    List<Usuario> buscarTodos();
    Usuario guardar(Usuario usuario, Integer rolId) throws ComandaServiceException;
    Usuario modificar(Usuario usuario, Integer rolId) throws ComandaServiceException;
    void eliminar(int idUsuario) throws Exception;
    Usuario buscarUsuario(int idUsuario) throws ComandaServiceException;

    Usuario login(FormLogin formLogin) throws UsuarioNotFoundException, InvalidPasswordException;

    void sendEmail(String email) throws MessagingException, ComandaServiceException;

}