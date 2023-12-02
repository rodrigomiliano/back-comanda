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
	@ManyToOne
	@JoinColumn(name = "PRODUC_CATEGO") // "idCategoria")
	private Categoria categoria;
	@Column(name = "PRODUC_IMG")
	private String imagen;
	@ManyToOne
	@JoinColumn(name = "PRODUC_LOCAL") // "idCategoria")
	private Local local;
	
	

	public Producto() {
		super();		
	}

	public Producto(String nombre, String descripcion, Double precio, Categoria categoria, String imagen) {
		super();		
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.precio = precio;
		this.categoria = categoria;
		this.imagen = imagen;
	}

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

	public Categoria getCategoria() {
		return categoria;
	}

	/*
	 * public String getCategoria() { return categoria.getNombre(); }
	 */

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	public String getImagen() {
		return imagen;
	}

	public void setImagen(String imagen) {
		this.imagen = imagen;
	}
	
	public Local getLocal() {
		return local;
	}

	public void setLocal(Local local) {
		this.local = local;
	}

	@Override
	public String toString() {
		return "Producto [id=" + id + ", nombre=" + nombre + ", descripcion=" + descripcion + ", precio=" + precio
				+ ", categoria=" + categoria + ", imagen=" + imagen + ", local=" + local + "]";
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