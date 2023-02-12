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
@Table(name = "Mesas") // Esto debe coincidir con el nombre de la tabla tal cual en bd.
public class Mesa {

	@Id // para que se sepa que es primary key
	@GeneratedValue(strategy = GenerationType.IDENTITY) // para que la pk sea autoincremental y la estrategia de cómo se
														// va a generar (en mysql).
	@Column(name = "MESA_ID")
	private Integer id;
	@Column(name = "MESA_SILLAS")
	private Integer sillas;
	@Column(name = "MESA_OBSERV")
	private String observacion;
	// @Column(name = "MESA_ESTADO")
	// private String estado;
	@OneToOne
	@JoinColumn(name = "MESA_ESTADO") // "idEstado")
	private Estado estado;
	// @OneToOne
	// @JoinColumn(name = "MESA_LOCAL") // "idLocal")
	// private Local local;
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getSillas() {
		return sillas;
	}

	public void setSillas(Integer sillas) {
		this.sillas = sillas;
	}

	public String getObservacion() {
		return observacion;
	}

	public void setObservacion(String observacion) {
		this.observacion = observacion;
	}

	public Estado getEstado() {
		return estado;
	}

	public void setEstado(Estado estado) {
		this.estado = estado;
	}

	@Override
	public String toString() {
		return "Mesa [id=" + id + ", sillas=" + sillas + ", observacion=" + observacion + ", estado=" + estado + "]";
	}

}
