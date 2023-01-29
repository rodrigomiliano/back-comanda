package comanda.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Menu") // Esto debe coincidir con el nombre de la tabla tal cual en bd.
public class Menu {

	@Id // para que se sepa que es primary key
	@GeneratedValue(strategy = GenerationType.IDENTITY) // para que la pk sea autoincremental y la estrategia de cómo se
														// va a generar (en mysql).
	@Column(name = "MENU_ID")
	private Integer id;
	@Column(name = "MENU_NOMBRE")
	private String nombre;
	@Column(name = "MENU_VIGDDE")
	private Date vigencia_desde;
	@Column(name = "MENU_VIGHTA")
	private Date vigencia_hasta;
	@Column(name = "MENU_PRELIS")
	private Integer menu_precio_lista;

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

	public Integer getMenu_precio_lista() {
		return menu_precio_lista;
	}

	public void setMenu_precio_lista(Integer menu_precio_lista) {
		this.menu_precio_lista = menu_precio_lista;
	}

	@Override
	public String toString() {
		return "Menu [id=" + id + ", nombre=" + nombre + ", vigencia_desde=" + vigencia_desde + ", vigencia_hasta="
				+ vigencia_hasta + ", menu_precio_lista=" + menu_precio_lista + "]";
	}

}
