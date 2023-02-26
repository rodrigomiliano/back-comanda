package comanda.service.jpa;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import comanda.entity.Venta;
import comanda.repository.VentasRepository;
import comanda.service.IVentasService;

@Service
public class VentasService implements IVentasService{

	@Autowired
	private VentasRepository repoVentas;

	public List<Venta> buscarTodas() {
		return repoVentas.findAll();
	}

	public void guardar(Venta venta) {
		repoVentas.save(venta);
	}

	public void eliminar(int idVenta) {
		repoVentas.deleteById(idVenta);
	}
	
	public Optional<Venta> buscarVenta(int idVenta) {
		return repoVentas.findById(idVenta);
	}
}
