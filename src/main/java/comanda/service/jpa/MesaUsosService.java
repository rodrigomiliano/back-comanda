package comanda.service.jpa;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import comanda.entity.Comprobante;
import comanda.entity.MesaUso;
import comanda.repository.ComprobantesRepository;
import comanda.repository.MesaUsosRepository;
import comanda.service.IMesaUsosService;


@Service
public class MesaUsosService implements IMesaUsosService {

	@Autowired
	private MesaUsosRepository repoMesaUsos;
	
	@Autowired
	private ComprobantesRepository repoComprobantes;
	
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
		/*Comprobante comprobante = new Comprobante(mesaUso);
		System.out.println(comprobante.toString());
		comprobante.setId(mesaUso.getId());
		comprobante.getId();
		System.out.println(comprobante.toString());		
		System.out.println(mesaUso.toString());*/

		
			Comprobante comp = new Comprobante(mesaUso);			
			System.out.println("Cerrando la mesa");
			//System.out.println("Cerrando mesa: " + mesaUso.getMesa());
			//System.out.println("Cerrando mesa: " + comp.getMesaUso().getMesa());		
			//comp.setMesaUso(mesaUso);			
			repoComprobantes.save(comp);			
			System.out.println("Creando " + comp);	
			//System.out.println("ver1 " + buscarMesaUso(comp.getId()));
			//System.out.println("ver2 " + buscarTodas() );
			//System.out.println(buscarMesaUso(comp.getId()));
							
		
			/*private void guardar() {
				Categoria cat = new Categoria();
				cat.setNombre("Finanzas");
				cat.setDescripcion("Trabajos relacionados con finanzas y contabilidad");
				repoCategorias.save(cat);
				System.out.println(cat);
			}*/	
			
			
			
		// Paso 3: Recorrer mesaUso: la lista de comandas

		// Paso 4: Por cada comanda recorrer la lista de itemComandas

		
		/*public void guardar(ItemComanda itemComanda) {	
			Integer productoId = itemComanda.getProducto().getId();
			System.out.println("Envio el producto con el id: " + productoId);
			Optional<Producto> productoObtenido = serviceProductos.buscarProducto(productoId);
			if (productoObtenido.isPresent()) {
				itemComanda.setProducto(productoObtenido.get());
				itemComanda.setPrecio(productoObtenido.get().getPrecio());
				itemComanda.setTotal();
				repoItemComandas.save(itemComanda);
			} else {
				System.out.println("error");
			}
			return;		
		}*/
	}
}
