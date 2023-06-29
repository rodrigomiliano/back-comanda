package comanda.service.jpa;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import comanda.entity.Comanda;
import comanda.entity.Comprobante;
import comanda.entity.Estado;
import comanda.entity.ItemComanda;
import comanda.entity.ItemComprobante;
import comanda.entity.MesaUso;
import comanda.entity.Producto;
import comanda.repository.ComandasRepository;
import comanda.repository.ComprobantesRepository;
import comanda.repository.ItemComandasRepository;
import comanda.repository.ItemComprobantesRepository;
import comanda.repository.MesaUsosRepository;
import comanda.service.IMesaUsosService;
import comanda.service.ComandaServiceException;

@Service
public class MesaUsosService implements IMesaUsosService {

	@Autowired
	private MesaUsosRepository repoMesaUsos;

	@Autowired
	private ComprobantesRepository repoComprobantes;

	@Autowired
	private ItemComprobantesRepository repoItemComprobantes;

	@Autowired
	private ComandasRepository repoComandas;

	@Autowired
	private ItemComandasRepository repoItemComandas;

	@Autowired
	private ProductosService productoService;

	public List<MesaUso> buscarTodas() {
		System.out.println("------------------------------------------------------------");
		List<MesaUso> mesaUsos = repoMesaUsos.findAll(); // spring
		System.out.println("Listado de MesaUsos: ");
		mesaUsos.forEach(t -> {
			System.out.println(t);
		});
		return repoMesaUsos.findAll(); // postman
	}

	public void guardar(MesaUso mesaUso) {
		System.out.println("------------------------------------------------------------");
		repoMesaUsos.save(mesaUso);
		System.out.println("Guardando " + mesaUso);
	}

	public void eliminar(int idMesaUso) throws Exception {
		System.out.println("Eliminando registro: " + buscarMesaUso(idMesaUso));
		repoMesaUsos.deleteById(idMesaUso);
	}

	public MesaUso buscarMesaUso(int idMesaUso) throws ComandaServiceException {
		System.out.println("------------------------------------------------------------");
		Optional<MesaUso> optional = repoMesaUsos.findById(idMesaUso);
		if (optional.isPresent()) {
			MesaUso u = optional.get();
			System.out.println("Elegiste " + u);
			return u;
		} else {
			System.out.println("------------------------------------------------------------");
			System.out.println("No existe el MesaUso n° " + idMesaUso);
			throw new ComandaServiceException("MUS002", "No existe el MesaUso n° " + idMesaUso);
		}
	}

	public void cerrarMesa(MesaUso mesaUso) {// crea comprobante, recorre comanda y productos, para grabar comprobante e
												// itemcomprobante
		// Sugerencia de Francisco para crear el comprobante:
		// mesauso: cerrarmesa() -> recorrer lista de comandas, x c/comanda recorrer
		// lista de productos, agrupar x codigo de producto la cantida de productos.
		// Generar comprobante new() y asociar a ese comprobante la lista agrupada de
		// los productos consumidos

		// Paso 1: Buscar mesaUso

		// Paso 2: Crear comprobante
		System.out.println("------------------------------------------------------------");
		Comprobante comp = new Comprobante(null, mesaUso, null);
		System.out.println("Cerrando la mesa n° " + mesaUso.getMesa().getId() + "; MesaUso n° " + mesaUso.getId());
		repoComprobantes.save(comp);
		System.out.println("Creando " + comp);
		crearListaItems(comp);

		// Paso 3: Recorrer mesaUso: la lista de comandas

		// Paso 4: Por cada comanda recorrer la lista de itemComandas

		/*
		 * public void guardar(ItemComanda itemComanda) { Integer productoId =
		 * itemComanda.getProducto().getId();
		 * System.out.println("Envio el producto con el id: " + productoId);
		 * Optional<Producto> productoObtenido =
		 * serviceProductos.buscarProducto(productoId); if
		 * (productoObtenido.isPresent()) {
		 * itemComanda.setProducto(productoObtenido.get());
		 * itemComanda.setPrecio(productoObtenido.get().getPrecio());
		 * itemComanda.setTotal(); repoItemComandas.save(itemComanda); } else {
		 * System.out.println("error"); } return; }
		 */
	}

	public void crearListaItems(Comprobante comprobante) {
		ItemComprobante itemcomp = new ItemComprobante(comprobante, null);
		repoItemComprobantes.save(itemcomp);
		System.out.println("Creando " + itemcomp);
	}

	public void crearComanda(MesaUso mesaUso) {
		System.out.println("------------------------------------------------------------");
		Estado esta = new Estado(); // Crea una comanda con estado vacío, para luego setearlo a "en preparación"
		esta.setId(5); // Es el estado "en preparacion"
		List<ItemComanda> itemComandas = new ArrayList<>(); // creo nueva lista de itemcomandas, nose si funciona asi
		//itemComanda.setId(coma);
		Comanda coma = new Comanda(esta, mesaUso, itemComandas);
		repoComandas.save(coma);
		System.out.println("Creando " + coma);
	}

	@Override
	public MesaUso crearItemComanda(Integer mesaUsoId, Integer comandaId, ItemComanda itemComanda) throws ComandaServiceException {

		MesaUso mesaUso = this.buscarMesaUso(mesaUsoId);

		Comanda comanda = buscarComanda(mesaUso.getComandas(), comandaId);
		if (Objects.nonNull(comanda)) {

			// Completar ItemComanda
			Producto producto = productoService.buscarProducto(itemComanda.getProducto().getId());
			itemComanda.setProducto(producto);
			itemComanda.setPrecio(producto.getPrecio());
			Double total = itemComanda.getPrecio() * itemComanda.getCantidad();
			itemComanda.setTotal(total);
			itemComanda.setComanda(comanda);
			repoItemComandas.save(itemComanda);
			System.out.println("Creando " + itemComanda);

		} else {
			throw new ComandaServiceException("MUS001", "Error: En la mesa uso: " + mesaUsoId + " no se encuentra la comanda para el id: " + comandaId);
		}

		System.out.println("------------------------------------------------------------");

		return mesaUso;

	}

	private Comanda buscarComanda(List<Comanda> comandas, Integer comandaId) {
		Comanda resultado = null;
		for(Comanda comanda : comandas) {
			if (comanda.getId() == comandaId) {
				resultado = comanda;
				break;
			}
		}
		return resultado;
	}

}
