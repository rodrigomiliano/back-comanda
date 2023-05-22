package comanda.service.jpa;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import comanda.entity.Comprobante;
import comanda.entity.MesaUso;
import comanda.repository.MesaUsosRepository;
import comanda.service.IMesaUsosService;

@Service
public class MesaUsosService implements IMesaUsosService {

	@Autowired
	private MesaUsosRepository repoMesaUsos;

	public List<MesaUso> buscarTodas() {
		List<MesaUso> mesaUsos = repoMesaUsos.findAll();
		mesaUsos.forEach(t -> {
			System.out.println(t);
		});
		return repoMesaUsos.findAll();
	}

	public void guardar(MesaUso mesaUso) {
		repoMesaUsos.save(mesaUso);
	}

	public void eliminar(int idMesaUso) {
		repoMesaUsos.deleteById(idMesaUso);
	}

	public Optional<MesaUso> buscarMesaUso(int idMesaUso) {
		return repoMesaUsos.findById(idMesaUso);
	}

	@Override
	public void cerrarMesa(MesaUso mesaUso) {// crea comprobante, recorre comanda y productos, para grabar comprobante e
												// itemcomprobante
		// Sugerencia de Francisco para crear el comprobante:
		// mesauso: cerrarmesa() -> recorrer lista de comandas, x c/comanda recorrer
		// lista de productos, agrupar x codigo de producto la cantida de productos.
		// Generar comprobante new() y asociar a ese comprobante la lista agrupada de
		// los productos consumidos

		// Paso 1: Buscar mesaUso

		// Paso 2: Crear comprobante
		Comprobante comprobante = new Comprobante(mesaUso);
		System.out.println(comprobante.toString());

		// Paso 3: Recorrer mesaUso: la lista de comandas

		// Paso 4: Por cada comanda recorrer la lista de itemComandas

	}
}
