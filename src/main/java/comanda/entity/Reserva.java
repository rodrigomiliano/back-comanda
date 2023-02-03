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
@Table(name = "Reservas") // Esto debe coincidir con el nombre de la tabla tal cual en bd.
public class Reserva {

	@Id // para que se sepa que es primary key
	@GeneratedValue(strategy = GenerationType.IDENTITY) // para que la pk sea autoincremental y la estrategia de cómo se
														// va a generar (en mysql).
	@Column(name = "RESERV_ID")
	private Integer id;
	// @Column(name = "RESERV_USER")
	// private Integer usuario;
	@OneToOne
	@JoinColumn(name = "RESERV_USER") // "idUsuario")
	private Usuario usuario;
	@Column(name = "RESERV_FECALT")
	private Date fecha_alta;
	@Column(name = "RESERV_FECRES")
	private Date fecha_reserva;
	@Column(name = "RESERV_COMENS")
	private Integer comensal;
	@Column(name = "RESERV_ESTADO")
	private String estado;
	// @Column(name = "RESERV_MESA")
	// private Integer mesa;
	@OneToOne
	@JoinColumn(name = "RESERV_MESA") // "idMesa")
	private Mesa mesa;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
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

	public Integer getComensal() {
		return comensal;
	}

	public void setComensal(Integer comensal) {
		this.comensal = comensal;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public Mesa getMesa() {
		return mesa;
	}

	public void setMesa(Mesa mesa) {
		this.mesa = mesa;
	}

	@Override
	public String toString() {
		return "Reserva [id=" + id + ", usuario=" + usuario + ", fecha_alta=" + fecha_alta + ", fecha_reserva="
				+ fecha_reserva + ", comensal=" + comensal + ", estado=" + estado + ", mesa=" + mesa + "]";
	}

}
