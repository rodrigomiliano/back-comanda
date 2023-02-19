package comanda.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.springframework.web.bind.annotation.GetMapping;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "ItemComandas") // Esto debe coincidir con el nombre de la tabla tal cual en bd.
public class ItemComanda {

	@Id // para que se sepa que es primary key
	@GeneratedValue(strategy = GenerationType.IDENTITY) // para que la pk sea autoincremental y la estrategia de cómo se
														// va a generar (en mysql).
	@Column(name = "ITEMCOM_ID")
	private Integer id;
	
	@OneToOne
	@JoinColumn(name = "ITEMCOM_COMANDA") // "idComanda")
	private Comanda comanda;
	
	@OneToOne
	@JoinColumn(name = "ITEMCOM_PRODUC") // "idProducto")
	private Producto producto;
	 
	//@Column(name = "ITEMCOM_TOTAL")
	//private Double total;

	

}
