package comanda.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name = "ItemComandas") // Esto debe coincidir con el nombre de la tabla tal cual en bd.
public class ItemComanda {

	@Id // para que se sepa que es primary key
	@GeneratedValue(strategy = GenerationType.IDENTITY) // para que la pk sea autoincremental y la estrategia de cómo se
														// va a generar (en mysql).
	@Column(name = "ITEMCOM_ID")
	private Integer id;

	@ManyToOne
	@JoinColumn(name = "ITEMCOM_COMANDA") // "idComanda")
	@JsonBackReference
	private Comanda comanda;

	@ManyToOne
	@JoinColumn(name = "ITEMCOM_PRODUC") // "idProducto")
	private Producto producto;

	@Column(name = "ITEMCOM_PRECIO")
	private Double precio;

	@Column(name = "ITEMCOM_CANTIDAD")
	private Integer cantidad;

	@Column(name = "ITEMCOM_TOTAL")
	private Double total;

	public ItemComanda() {
		super();		
	}
		
	public ItemComanda(/*Integer id,*/ Comanda comanda, Producto producto, Double precio, Integer cantidad, Double total) {
		super();
		//this.id = id;
		this.comanda = comanda;
		this.producto = producto;
		this.precio = precio;
		this.cantidad = cantidad;
		this.total = total;
	}

	/*public ItemComanda(Comanda comanda, Producto producto, Integer cantidad) {				
		this.comanda = comanda;
		this.producto = producto;
		this.precio = producto.getPrecio();
		this.cantidad = cantidad;
		this.total = this.precio * this.cantidad;
	}*/

	

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Comanda getComanda() {
		return comanda;
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

	public Double getPrecio() {
		return precio;
	}

	public void setPrecio(Double precio) {
		this.precio = precio;
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

	public void setTotal(Double total) {
		this.total = total;
	}

	@Override
	public String toString() {
		return "ItemComanda [id=" + id + ", comanda=" + comanda + ", producto=" + producto + ", total=" + total + "]";
	}

}