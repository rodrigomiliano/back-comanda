package comanda.service;

import java.util.List;
import java.util.Optional;
import comanda.entity.Venta;

public interface IVentasService {
	
	List<Venta> buscarTodas();
	void guardar(Venta venta);
	void eliminar(int idVenta);
	Optional<Venta> buscarVenta(int idVenta);

}
