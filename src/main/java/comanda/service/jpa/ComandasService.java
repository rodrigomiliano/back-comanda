package comanda.service.jpa;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import comanda.entity.Estado;
import comanda.entity.Mesa;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import comanda.entity.Comanda;
import comanda.repository.ComandasRepository;
import comanda.service.IComandasService;

@Service
public class ComandasService implements IComandasService {

    @Autowired
    private ComandasRepository repoComandas;

    public List<Comanda> buscarTodas() {
        System.out.println("------------------------------------------------------------");
        List<Comanda> comandas = repoComandas.findAll(); // spring
        System.out.println("Listado de Comandas: ");
        comandas.forEach(t -> {
            System.out.println(t);
        });
        return repoComandas.findAll(); // postman
    }

    public Comanda guardar(Comanda comanda) {
        return repoComandas.save(comanda);
    }

    public void eliminar(int idComanda) {
        System.out.println("Eliminando registro: " + buscarComanda(idComanda));
        repoComandas.deleteById(idComanda);
    }

    public Optional<Comanda> buscarComanda(int idComanda) {
        System.out.println("------------------------------------------------------------");
        Optional<Comanda> optional = repoComandas.findById(idComanda);
        if (optional.isPresent()) {
            Comanda u = optional.get();
            System.out.println("Elegiste " + u);
            return repoComandas.findById(idComanda);
        } else {
            System.out.println("------------------------------------------------------------");
            System.out.println("No existe la Comanda n° " + idComanda);
        }
        return null;
    }

    @Override
    public void crearItemComanda(Comanda comanda) {
        // TODO Auto-generated method stub

    }

    @Override
    public Comanda buscarComandaPorMesaPorEstado(Mesa mesa, Estado estado) {
        Comanda comanda = new Comanda(mesa, estado);
        Example<Comanda> exampleComanda = Example.of(comanda);
        List<Comanda> comandasPrevias = repoComandas.findAll(exampleComanda);
        if (comandasPrevias.isEmpty()) return null;
        return comandasPrevias.get(0);
    }

    @Override
    public List<Comanda> buscarComandaPorFecha(Date date) {
        //return repoComandas.findByFechaGreaterThanEqual(date);
        return repoComandas.findAllByOrderByFechaDesc();
    }
	
	/*public void crearItemComanda(Comanda comanda) {
		//Producto prod = new Producto();
		//prod.setId(2);
		//Double precio = prod.getPrecio();
		//System.out.println(prod.getId() + prod.getPrecio());
		ItemComanda itemCom = new ItemComanda(comanda,  null, null, null, null);
		//System.out.println("Creandooooooo " + itemCom);
		repoItemComandas.save(itemCom);
		System.out.println("Creando " + itemCom);
	}*/

	/*public void crearComanda(MesaUso mesaUso) { 
		System.out.println("------------------------------------------------------------");		
		Estado esta = new Estado(); // Crea una comanda con estado vacío, para luego setearlo a "en preparación"
		esta.setId(5); // Es el estado "en preparacion"		
		List<ItemComanda> itemComandas = new ArrayList<>(); // creo nueva lista de itemcomandas, nose si funciona asi
		//itemComanda.setId(coma);
		Comanda coma = new Comanda(esta, mesaUso, itemComandas);
		repoComandas.save(coma);
		System.out.println("Creando " + coma);
	}*/	
	
	/*public void cerrarMesa(MesaUso mesaUso) {
		System.out.println("------------------------------------------------------------");
		Comprobante comp = new Comprobante(null, mesaUso, null);
		System.out.println("Cerrando la mesa n° " + mesaUso.getMesa().getId() + "; MesaUso n° " + mesaUso.getId());
		repoComprobantes.save(comp);
		System.out.println("Creando " + comp);
		crearListaItems(comp);
	}*/

}