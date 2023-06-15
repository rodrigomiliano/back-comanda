package comanda.service;

import java.util.List;
import java.util.Optional;

import comanda.entity.Comanda;
import comanda.entity.Comprobante;
import comanda.entity.ItemComanda;
import comanda.entity.MesaUso;

public interface IMesaUsosService {

	List<MesaUso> buscarTodas();
	void guardar(MesaUso mesaUso);
	void eliminar(int idMesaUso);
	Optional<MesaUso> buscarMesaUso(int idMesaUso);
	void cerrarMesa(MesaUso mesaUso); //crea comprobante, recorre comanda y productos, para grabar comprobante e itemcomprobante
	void crearListaItems(Comprobante comprobante);
	void crearComanda(MesaUso mesauso);
	void crearItemComanda(Integer mesaUsoId, Integer comandaId, ItemComanda itemComanda);
}
