package comanda.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Precios") // Esto debe coincidir con el nombre de la tabla tal cual en bd.
public class Precio {

	@Id // para que se sepa que es primary key
	@GeneratedValue(strategy = GenerationType.IDENTITY) // para que la pk sea autoincremental y la estrategia de cómo se
														// va a generar (en mysql).
	@Column(name = "PRELIS_ID")
	private Integer id;
	@Column(name = "PRELIS_NOMBRE")
	private String nombre;
	@Column(name = "PRELIS_PRODUC")
	private Integer producto_id;
	@Column(name = "PRELIS_PRECIO")
	private Double precio;
	@Column(name = "PRELIS_VIGDDE")
	private Date vigencia_desde;
	@Column(name = "PRELIS_VIGHTA")
	private Date vigencia_hasta;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Integer getProducto_id() {
		return producto_id;
	}

	public void setProducto_id(Integer producto_id) {
		this.producto_id = producto_id;
	}

	public Double getPrecio() {
		return precio;
	}

	public void setPrecio(Double precio) {
		this.precio = precio;
	}

	public Date getVigencia_desde() {
		return vigencia_desde;
	}

	public void setVigencia_desde(Date vigencia_desde) {
		this.vigencia_desde = vigencia_desde;
	}

	public Date getVigencia_hasta() {
		return vigencia_hasta;
	}

	public void setVigencia_hasta(Date vigencia_hasta) {
		this.vigencia_hasta = vigencia_hasta;
	}

	@Override
	public String toString() {
		return "Precio [id=" + id + ", nombre=" + nombre + ", producto_id=" + producto_id + ", precio=" + precio
				+ ", vigencia_desde=" + vigencia_desde + ", vigencia_hasta=" + vigencia_hasta + "]";
	}

}
