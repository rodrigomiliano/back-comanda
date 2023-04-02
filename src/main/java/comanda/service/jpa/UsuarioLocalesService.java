package comanda.service.jpa;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import comanda.entity.UsuarioLocal;
import comanda.repository.UsuarioLocalesRepository;
import comanda.service.IUsuarioLocalesService;

@Service
public class UsuarioLocalesService implements IUsuarioLocalesService{

	@Autowired
	private UsuarioLocalesRepository repoUsuarioLocales;

	public List<UsuarioLocal> buscarTodos() {
		return repoUsuarioLocales.findAll();
	}

	public void guardar(UsuarioLocal usuarioLocal) {
		repoUsuarioLocales.save(usuarioLocal);
	}

	public void eliminar(int idUsuarioLocal) {
		repoUsuarioLocales.deleteById(idUsuarioLocal);
	}
	
	public Optional<UsuarioLocal> buscarUsuarioLocal(int idUsuarioLocal) {
		return repoUsuarioLocales.findById(idUsuarioLocal);
	}
	
}
