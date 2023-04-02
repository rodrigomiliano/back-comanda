package comanda.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "Comprobantes") // Esto debe coincidir con el nombre de la tabla tal cual en bd.
public class Comprobante {

	@Id // para que se sepa que es primary key
	@GeneratedValue(strategy = GenerationType.IDENTITY) // para que la pk sea autoincremental y la estrategia de cómo se
														// va a generar (en mysql).
	@Column(name = "COMPROBANTE_ID")
	private Integer id;
	// @Column(name = "PRODUC_ID")
	// private Integer nro_comprobante;

	// private Date fecha;

	@OneToOne
	@JoinColumn(name = "COMPROBANTE_MESAUSO") // "idComprobanteMesaUso") private
	MesaUso mesaUso;

	// private double total;

	// private ItemComprobante itemComprobante;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public MesaUso getMesaUso() {
		return mesaUso;
	}

	public void setMesaUso(MesaUso mesaUso) {
		this.mesaUso = mesaUso;
	}

	@Override
	public String toString() {
		return "Comprobante [id=" + id + ", mesaUso=" + mesaUso + "]";
	}

}
