package comanda.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Estados") // Esto debe coincidir con el nombre de la tabla tal cual en bd.
public class Estado {

	public static int ESTADO_DISPONIBLE = 1;
	public static int ESTADO_EN_CURSO = 2;
	public static int ESTADO_CERRADA = 3;

	@Id // para que se sepa que es primary key
	@GeneratedValue(strategy = GenerationType.IDENTITY) // para que la pk sea autoincremental y la estrategia de cómo se
														// va a generar (en mysql).
	@Column(name = "ESTADO_ID")
	private Integer id;
	@Column(name = "ESTADO_NOMBRE")
	private String nombre;	

	public Estado() {
		super();		
	}

	public Estado(String nombre) {
		super();		
		this.nombre = nombre;		
	}

	public Estado(int id) {
		this.id = id;
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
	
	@Override
	public String toString() {
		return "Estado [id=" + id + ", nombre=" + nombre + "]";
	}

}
