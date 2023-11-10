package comanda.entity;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "Reservas") // Esto debe coincidir con el nombre de la tabla tal cual en bd.
public class Reserva {

	@Id // para que se sepa que es primary key
	@GeneratedValue(strategy = GenerationType.IDENTITY) // para que la pk sea autoincremental y la estrategia de cómo se
														// va a generar (en mysql).
	@Column(name = "RESERV_ID")
	private Integer id;
	@ManyToOne
	@JoinColumn(name = "RESERV_CLIENTE") // "idCliente")
	private Cliente cliente;
	
	//@Column(name = "RESERV_FECALT")
	//private Date fecha_alta;
	@Temporal(TemporalType.DATE)
	@Column(name = "RESERV_FECALT")
	private Date fecha_alta;
	
	
	@Column(name = "RESERV_FECRES")
	private Date fecha_reserva;
	//@Column(name = "RESERV_COMENS")
	//private Integer comensal;
	@ManyToOne
	@JoinColumn(name = "RESERV_MESA") // "idMesa")
	private Mesa mesa;
	@ManyToOne
	@JoinColumn(name = "RESERV_ESTADO") // "idEstado")
	private Estado estado;
	@ManyToOne
	@JoinColumn(name = "RESERV_TURNO") // "idTurno")
	private Turno turno;

	@PrePersist // usar localdatetime o calendar
	private void onCreate() {
		fecha_alta = new Date();
	}
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	/*
	 * public Cliente getCliente() { return cliente; }
	 */
	public Cliente getCliente() {
		return cliente;/*cliente.getApellido() + " " + cliente.getNombre();*/
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Date getFecha_alta() {
		return fecha_alta;
	}

	public void setFecha_alta(Date fecha_alta) {
		this.fecha_alta = fecha_alta;
	}

	public Date getFecha_reserva() {
		return fecha_reserva;
	}

	public void setFecha_reserva(Date fecha_reserva) {
		this.fecha_reserva = fecha_reserva;
	}

	/*public Integer getComensal() {
		return comensal;
	}

	public void setComensal(Integer comensal) {
		this.comensal = comensal;
	}*/

	/*
	 * public String getEstado() { return estado; }
	 */
	public Estado getEstado() {
		return estado;/*estado.getNombre();*/
	}

	public void setEstado(Estado estado) {
		this.estado = estado;
	}

	/*
	 * public Mesa getMesa() { return mesa; }
	 */
	public Mesa getMesa() {
		return mesa;/*"Hasta " + mesa.getSillas() + " comensales ";*/
	}

	public void setMesa(Mesa mesa) {
		this.mesa = mesa;
	}

	public Turno getTurno() {
		return turno;
	}

	public void setTurno(Turno turno) {
		this.turno = turno;
	}

	@Override
	public String toString() {
		return "Reserva [id=" + id + ", cliente=" + cliente + ", fecha_alta=" + fecha_alta + ", fecha_reserva="
				+ fecha_reserva + ", mesa=" + mesa + ", estado=" + estado + ", turno=" + turno + "]";
	}

	

}
