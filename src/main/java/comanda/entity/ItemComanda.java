package comanda.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.springframework.web.bind.annotation.GetMapping;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "ItemComandas") // Esto debe coincidir con el nombre de la tabla tal cual en bd.
public class ItemComanda {

	@Id // para que se sepa que es primary key
	@GeneratedValue(strategy = GenerationType.IDENTITY) // para que la pk sea autoincremental y la estrategia de cómo se
														// va a generar (en mysql).
	@Column(name = "ITEMCOM_ID")
	private Integer id;

	// @Column(name = "ITEMCOM_PRODUC")
	// private Integer producto;

	/*
	@ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "employee_project", 
             joinColumns = { @JoinColumn(name = "employee_id") }, 
             inverseJoinColumns = { @JoinColumn(name = "project_id") })
	*/
	
	
	 @ManyToMany(cascade = CascadeType.ALL)
	    @JoinTable(name = "ItemComandas", 
	             joinColumns = { @JoinColumn(name = "ITEMCOM_PRODUC") }, 
	             inverseJoinColumns = { @JoinColumn(name = "PRODUC_ID") })
	//@OneToOne	
	//@JoinColumn(name = "ITEMCOM_PRODUC") // "idProducto")
	private List<Producto> producto = new ArrayList<Producto>();
	
	/*@OneToMany(mappedBy="producto", cascade = CascadeType.PERSIST, fetch = FetchType.EAGER, orphanRemoval = true)
	@JsonManagedReference
	private List<Producto> productos;*/

	@Column(name = "ITEMCOM_TOTAL")
	private Double total;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
	public List<Producto> getProducto() {
		return producto;
	}

	public void setProducto(List<Producto> productos) {
		this.producto = productos;
	}

	public Double getTotal() {
		return total;
	}

	public void setTotal(Double total) {
		this.total = total;
	}

	public void addProductto(Producto producto) {
		if (this.producto == null) {
			this.producto = new ArrayList<Producto>();
		}
		this.producto.add(producto);
	}
	
	@Override
	public String toString() {
		return "ItemComanda [id=" + id + ", producto=" + producto + ", total=" + total + "]";
	}

}
