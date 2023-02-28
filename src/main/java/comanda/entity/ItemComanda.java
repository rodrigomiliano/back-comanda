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
@Table(name = "ItemComandas") // Esto debe coincidir con el nombre de la tabla tal cual en bd.
public class ItemComanda {

	@Id // para que se sepa que es primary key
	@GeneratedValue(strategy = GenerationType.IDENTITY) // para que la pk sea autoincremental y la estrategia de cómo se
														// va a generar (en mysql).
	@Column(name = "ITEMCOM_ID")
	private Integer id;

	@OneToOne
	@JoinColumn(name = "ITEMCOM_COMANDA") // "idComanda")
	private Comanda comanda;

	@OneToOne
	@JoinColumn(name = "ITEMCOM_PRODUC") // "idProducto")
	private Producto producto;

	@Column(name = "ITEMCOM_CANTIDAD")
	private Integer cantidad;

	@Column(name = "ITEMCOM_TOTAL")
	private Double total;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	/*
	 * public Comanda getComanda() { return comanda; }
	 */

	public Integer getComanda() {
		return comanda.getId();
	}

	public void setComanda(Comanda comanda) {
		this.comanda = comanda;
	}

	public Producto getProducto() {
		return producto;
	}

	public void setProducto(Producto producto) {
		this.producto = producto;
	}

	public Integer getCantidad() {
		return cantidad;
	}

	public void setCantidad(Integer cantidad) {
		this.cantidad = cantidad;
	}

	public Double getTotal() {
		return total;
	}

	public void setTotal() {
		this.total = getProducto().getPrecio() * this.cantidad;
	}

	@Override
	public String toString() {
		return "ItemComanda [id=" + id + ", comanda=" + comanda + ", producto=" + producto + ", total=" + total + "]";
	}

}
