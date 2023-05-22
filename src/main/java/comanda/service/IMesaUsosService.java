package comanda.service;

import java.util.List;
import java.util.Optional;
import comanda.entity.MesaUso;

public interface IMesaUsosService {

	List<MesaUso> buscarTodas();
	void guardar(MesaUso mesaUso);
	void eliminar(int idMesaUso);
	Optional<MesaUso> buscarMesaUso(int idMesaUso);
	void cerrarMesa(MesaUso mesaUso); //crea comprobante, recorre comanda y productos, para grabar comprobante e itemcomprobante
}
