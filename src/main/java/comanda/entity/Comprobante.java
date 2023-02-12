package comanda.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Comprobantes") // Esto debe coincidir con el nombre de la tabla tal cual en bd.
public class Comprobante {

	@Id // para que se sepa que es primary key
	@GeneratedValue(strategy = GenerationType.IDENTITY) // para que la pk sea autoincremental y la estrategia de cómo se
														// va a generar (en mysql).
	//@Column(name = "PRODUC_ID")
	private Integer id;
	//@Column(name = "PRODUC_ID")
	//private Integer nro_comprobante;
	//@Column(name = "PRODUC_PRECIO")
	//private Double total;
	//@OneToOne
	//@JoinColumn(name = "PRODUC_CATEGO") // "idMoneda")
	//private Moneda moneda;
}
