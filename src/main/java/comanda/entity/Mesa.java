package comanda.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
	@ManyToOne
	@JoinColumn(name = "MESA_ESTADO") // "idEstado")
	private Estado estado;
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "MESA_USUARIO") // "idUsuario")
	private Usuario usuario;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "MESA_LOCAL") // "idLocal")
	private Local local;

	public Mesa() {
		super();		
	}

	public Mesa(Integer id, Integer sillas, String observacion, Estado estado, Usuario usuario, Local local) {		
		this.id = id;
		this.sillas = sillas;
		this.observacion = observacion;
		this.estado = estado;
		this.usuario = usuario;
		this.local = local;
	}

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

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Local getLocal() {
		return local;
	}

	public void setLocal(Local local) {
		this.local = local;
	}

	@Override
	public String toString() {
		return "Mesa [id=" + id + ", sillas=" + sillas + ", observacion=" + observacion + ", estado=" + estado + "]";
	}

}
