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
@Table(name = "Productos") // Esto debe coincidir con el nombre de la tabla tal cual en bd.
public class Producto {

	@Id // para que se sepa que es primary key
	@GeneratedValue(strategy = GenerationType.IDENTITY) // para que la pk sea autoincremental y la estrategia de cómo se
														// va a generar (en mysql).
	@Column(name = "PRODUC_ID")
	private Integer id;
	@Column(name = "PRODUC_NOMBRE")
	private String nombre;
	@Column(name = "PRODUC_DESCRP")
	private String descripcion;
	@Column(name = "PRODUC_PRECIO")
	private Double precio;
	@OneToOne
	@JoinColumn(name = "PRODUC_CATEGO") // "idCategoria")
	private Categoria categoria;

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

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Double getPrecio() {
		return precio;
	}

	public void setPrecio(Double precio) {
		this.precio = precio;
	}

	/*
	 * public Categoria getCategoria() { return categoria; }
	 */
	public String getCategoria() {
		return categoria.getNombre();
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	@Override
	public String toString() {
		return "Producto [id=" + id + ", nombre=" + nombre + ", descripcion=" + descripcion + ", precio=" + precio
				+ ", categoria=" + categoria + "]";
	}

	
	public ItemComanda get(int i) {
		// TODO Auto-generated method stub
		return null;
	}

	public int size() {
		// TODO Auto-generated method stub
		return 0;
	}
    
}
