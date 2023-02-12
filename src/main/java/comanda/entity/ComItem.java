package comanda.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Comitems") // Esto debe coincidir con el nombre de la tabla tal cual en bd.
public class ComItem {

	@Id // para que se sepa que es primary key
	@GeneratedValue(strategy = GenerationType.IDENTITY) // para que la pk sea autoincremental y la estrategia de cómo se
														// va a generar (en mysql).
	@Column(name = "COMITS_ID")
	private Integer id;
	@Column(name = "COMITS_COMAND")
	private Integer comanda;
	@Column(name = "COMITS_PRODUC")
	private Integer producto;
	@Column(name = "COMITS_CANTID")
	private Integer cantidad;
	@Column(name = "COMITS_ESTADO")
	private Integer estado;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getComanda() {
		return comanda;
	}

	public void setComanda(Integer comanda) {
		this.comanda = comanda;
	}

	public Integer getProducto() {
		return producto;
	}

	public void setProducto(Integer producto) {
		this.producto = producto;
	}

	public Integer getCantidad() {
		return cantidad;
	}

	public void setCantidad(Integer cantidad) {
		this.cantidad = cantidad;
	}

	public Integer getEstado() {
		return estado;
	}

	public void setEstado(Integer estado) {
		this.estado = estado;
	}

	@Override
	public String toString() {
		return "ComItem [id=" + id + ", comanda=" + comanda + ", producto=" + producto + ", cantidad=" + cantidad
				+ ", estado=" + estado + "]";
	}

}
