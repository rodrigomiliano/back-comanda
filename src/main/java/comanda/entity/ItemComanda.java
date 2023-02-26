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
	
	@OneToOne
	@JoinColumn(name = "ITEMCOM_COMANDA") // "idComanda")
	private Comanda comanda;
	
	@OneToOne
	@JoinColumn(name = "ITEMCOM_PRODUC") // "idProducto")
	private Producto producto;
	
	@Column(name = "ITEMCOM_TOTAL")
	private Double total;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	/*public Comanda getComanda() {
		return comanda;
	}*/
	
	public Integer getComanda() {
		return comanda.getId();
	}

	public void setComanda(Comanda comanda) {
		this.comanda = comanda;
	}

	/*public Producto getProducto() {
		return producto;
	}*/
	
	public String getProducto() {
		return producto.getNombre();
	}
	
	public Double getPrecio() {
		return producto.getPrecio();
	}
	
	public void setProducto(Producto producto) {
		this.producto = producto;
	}
	
	public void setPrecio(Producto producto) {
		this.producto = producto;
	}
	
	/*
	public Double getTotal() {
		return total;
	}

	public void setTotal(Double total) {
		total = (double) 0;		
	    for(int i=0;i<producto.size();i++){
	      total = total + producto.get(i).getPrecio();
	    }
	}
    */
		
	 public double mostrarPrecioTotal() {
	    	double total = 0;	    	    	
	    	for(int i = 0; i < producto.size(); i++){
	    		
	    			total += producto.get(i).getPrecio();
	    		    	
	    	}
	    	return total;	
	  	}
	

	public Double getTotal() {
		return mostrarPrecioTotal();
	}
	
	public void setTotal(Double total) {
		this.total = mostrarPrecioTotal();
	}

	@Override
	public String toString() {
		return "ItemComanda [id=" + id + ", comanda=" + comanda + ", producto=" + producto + ", total=" + total + "]";
	}

	
	 	

	
}
