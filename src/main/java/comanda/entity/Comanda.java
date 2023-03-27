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
@Table(name = "Comandas") // Esto debe coincidir con el nombre de la tabla tal cual en bd.
public class Comanda {

	@Id // para que se sepa que es primary key
	@GeneratedValue(strategy = GenerationType.IDENTITY) // para que la pk sea autoincremental y la estrategia de cómo se
														// va a generar (en mysql).
	@Column(name = "COMAND_ID")
	private Integer id;
	// @Column(name = "COMAND_RESERV")
	// private Integer reserva;
	//@OneToOne
	//@JoinColumn(name = "COMAND_RESERV") // "idReserva")
	//private Reserva reserva;
	@ManyToOne
	@JoinColumn(name = "COMAND_ESTADO") // "idEstado")
	private Estado estado;	
	@ManyToOne
	@JoinColumn(name = "COMAND_MESAUSO") // "idMesaUso")
	private MesaUso mesaUso;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Estado getEstado() {
		return estado;
	}
	public void setEstado(Estado estado) {
		this.estado = estado;
	}
	public MesaUso getMesaUso() {
		return mesaUso;
	}
	public void setMesaUso(MesaUso mesaUso) {
		this.mesaUso = mesaUso;
	}
	@Override
	public String toString() {
		return "Comanda [id=" + id + ", estado=" + estado + ", mesaUso=" + mesaUso + "]";
	}
	
}