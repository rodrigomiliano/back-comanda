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
@Table(name = "Items_Comprobante") // Esto debe coincidir con el nombre de la tabla tal cual en bd.
public class ItemComprobante {

	@Id // para que se sepa que es primary key
	@GeneratedValue(strategy = GenerationType.IDENTITY) // para que la pk sea autoincremental y la estrategia de cómo se
														// va a generar (en mysql).
	@Column(name = "ITMCOM_ID")
	private Integer id;

	@OneToOne
	@JoinColumn(name = "COMPRO_ID") // "idComprobante")
	private Comprobante comprobante;

	// @OneToMany
	// @JoinColumn(name = "ITMCOM_PRODUC") // "idProducto")
	// private Producto producto;

	// private double precio;

	@Column(name = "ITMCOM_CANTID")
	private Integer cantidad;

	public ItemComprobante() {
		super();
	}
	
	public ItemComprobante(Comprobante comprobante, Integer cantidad) {
		super();		
		this.comprobante = comprobante;
		this.cantidad = cantidad;
	}



	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	/*
	 * public Comprobante getComprobante() { return comprobante; }
	 * 
	 * public void setComprobante(Comprobante comprobante) { this.comprobante =
	 * comprobante; }
	 */

	/*
	 * public Producto getProducto() { return producto; }
	 * 
	 * public void setProducto(Producto producto) { this.producto = producto; }
	 */

	public Integer getCantidad() {
		return cantidad;
	}

	public void setCantidad(Integer cantidad) {
		this.cantidad = cantidad;
	}

	@Override
	public String toString() {
		return "ItemComprobante [id=" + id
				+ /* ", comprobante=" + comprobante + */ /* ", producto=" + producto + */", cantidad=" + cantidad + "]";
	}

}
