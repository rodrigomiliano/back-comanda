package comanda.service;

import java.util.List;

import comanda.entity.Comprobante;
import comanda.entity.ItemComanda;
import comanda.entity.MesaUso;

public interface IMesaUsosService {

	List<MesaUso> buscarTodas();
	void guardar(MesaUso mesaUso);
	void eliminar(int idMesaUso) throws Exception;
	MesaUso buscarMesaUso(int idMesaUso) throws ComandaServiceException;
	void cerrarMesa(MesaUso mesaUso); //crea comprobante, recorre comanda y productos, para grabar comprobante e itemcomprobante
	void crearListaItems(Comprobante comprobante);
	void crearComanda(MesaUso mesauso);
	MesaUso crearItemComanda(Integer mesaUsoId, Integer comandaId, ItemComanda itemComanda) throws ComandaServiceException;
}
