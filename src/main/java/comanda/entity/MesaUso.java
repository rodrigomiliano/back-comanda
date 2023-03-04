package comanda.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "Mesauso") // Esto debe coincidir con el nombre de la tabla tal cual en bd.
public class MesaUso {

	@Id // para que se sepa que es primary key
	@GeneratedValue(strategy = GenerationType.IDENTITY) // para que la pk sea autoincremental y la estrategia de cómo se
														// va a generar (en mysql).
	@Column(name = "MESAUSO_ID")
	private Integer id;

	// private Date inicio;

	// private Date fin;

	// private Mesa mesa;

	// private Comanda comanda;

	@OneToOne
	@JoinColumn(name = "MESAUSO_ITEMCOM") // "idEstado")
	private ItemComanda itemComanda;

	@Column(name = "MESAUSO_TOTAL")
	private Double Total;

	// private Comprobante comprobante;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public ItemComanda getItemComanda() {
		return itemComanda;
	}

	public void setItemComanda(ItemComanda itemComanda) {
		this.itemComanda = itemComanda;
	}

	public Double getTotal() {
		return Total;
	}

	public void setTotal(Double total) {
		Total = total;
	}

	@Override
	public String toString() {
		return "MesaUso [id=" + id + ", itemComanda=" + itemComanda + ", Total=" + Total + "]";
	}

}
