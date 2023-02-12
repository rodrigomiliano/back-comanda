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
@Table(name = "Menuit") // Esto debe coincidir con el nombre de la tabla tal cual en bd.
public class MenuItem {

	@Id // para que se sepa que es primary key
	@GeneratedValue(strategy = GenerationType.IDENTITY) // para que la pk sea autoincremental y la estrategia de cómo se
														// va a generar (en mysql).
	@Column(name = "MENUIT_ID")
	private Integer id;
	// @Column(name = "MENUIT_MENU")
	// private Integer menu;
	@OneToOne
	@JoinColumn(name = "MENUIT_MENU") // "idMenu")
	private Menu menu;
	@OneToOne
	@JoinColumn(name = "MENUIT_PRODUC") // "idProducto")
	private Producto producto;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	/*public Menu getMenu() {
		return menu;
	}*/
	public String getMenu() {
		return menu.getNombre();
	}

	public void setMenu(Menu menu) {
		this.menu = menu;
	}

	/*public Producto getProducto() {
		return producto;
	}*/
	public String getProducto() {
		return producto.getNombre();
	}

	public void setProducto(Producto producto) {
		this.producto = producto;
	}

	@Override
	public String toString() {
		return "MenuItem [id=" + id + ", menu=" + menu + ", producto=" + producto + "]";
	}

}
