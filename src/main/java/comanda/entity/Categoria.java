package comanda.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Categorias") // Esto debe coincidir con el nombre de la tabla tal cual en bd.
public class Categoria {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) // para que la pk sea autoincremental y la estrategia de cómo se
														// va a generar (en mysql).
	@Column(name = "CATEGO_ID")
	private Integer id;
	@Column(name = "CATEGO_NOMBRE")
	private String nombre;
	@Column(name = "CATEGO_IMG")
	private String imagen;
	@Column(name = "CATEGO_DESTACADO")
	private Boolean destacado;

	public Categoria() {
		super();
	}

	public Categoria(Integer id, String nombre, String imagen, Boolean destacado) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.imagen = imagen;
		this.destacado = destacado;
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
	

	public String getImagen() {
		return imagen;
	}

	public void setImagen(String imagen) {
		this.imagen = imagen;
	}
		
	public Boolean getDestacado() {
		return destacado;
	}

	public void setDestacado(Boolean destacado) {
		this.destacado = destacado;
	}

	@Override
	public String toString() {
		return "Categoria [id=" + id + ", nombre=" + nombre + ", imagen=" + imagen + ", destacado=" + destacado + "]";
	}

}
