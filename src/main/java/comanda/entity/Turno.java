package comanda.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "Turnos") // Esto debe coincidir con el nombre de la tabla tal cual en bd.
public class Turno {

	@Id // para que se sepa que es primary key
	@GeneratedValue(strategy = GenerationType.IDENTITY) // para que la pk sea autoincremental y la estrategia de cómo se
														// va a generar (en mysql).
	@Column(name = "TURNO_ID")
	private Integer id;
	@Column(name = "TURNO_HORARIO")
	private String horario;
	@ManyToOne
	@JoinColumn(name = "TURNO_ESTADO") // "idEstado")
	private Estado estado;
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getHorario() {
		return horario;
	}

	public void setHorario(String horario) {
		this.horario = horario;
	}

	public Estado getEstado() {
		return estado;
	}

	public void setEstado(Estado estado) {
		this.estado = estado;
	}

	@Override
	public String toString() {
		return "Turno [id=" + id + ", horario=" + horario + ", estado=" + estado + "]";
	}
	
}
