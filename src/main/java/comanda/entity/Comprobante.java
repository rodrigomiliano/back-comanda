package comanda.entity;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


@Entity
@Table(name = "Comprobantes") // Esto debe coincidir con el nombre de la tabla tal cual en bd.
public class Comprobante {

	// Sugerencia de Francisco para crear el comprobante:
	//mesauso: cerrarmesa() -> recorrer lista de comandas, x c/comanda recorrer lista de productos, agrupar x codigo de producto la cantida de productos.
	//Generar comprobante new() y asociar a ese comprobante la lista agrupada de los productos consumidos
	
	@Id // para que se sepa que es primary key
	@GeneratedValue(strategy = GenerationType.IDENTITY) // para que la pk sea autoincremental y la estrategia de cómo se va a generar (en mysql).
	@Column(name = "COMPROBANTE_ID")
	private Integer id;
	// @Column(name = "PRODUC_ID")
	// private Integer nro_comprobante;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "COMPROBANTE_FECHA")
	private Date fecha;

	// private ItemComprobante itemComprobante;
	
	@OneToOne
	@JoinColumn(name = "COMPROBANTE_MESAUSO") // "idComprobanteMesaUso") private
	MesaUso mesaUso;
	//ItemComanda itemComanda;

	@Column(name = "COMPROBANTE_TOTAL")
	private Double total;

	
	
	public Comprobante() {		
		super();
	}
	/*
	public Comprobante(LocalDateTime fecha) {
		this.fecha = fecha;		
	}*/

	
	public Comprobante( Date fecha, MesaUso mesaUso, Double total) {
		super();
		this.fecha = fecha;
		this.mesaUso = mesaUso;
		this.total = total;
	}


	@PrePersist // usar localdatetime o calendar
	private void onCreate() {
		fecha = Date.from(LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant());
	}
	
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	
	
	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	
	
	
	public MesaUso getMesaUso() {
		return mesaUso;
	}

	public void setMesaUso(MesaUso mesaUso) {
		this.mesaUso = mesaUso;
	}

	public Double getTotal() {
		return total;
	}

	/*public ItemComanda getItemComanda() {
		return itemComanda;
	}

	public void setItemComanda(ItemComanda itemComanda) {
		this.itemComanda = itemComanda;
	}*/

	/*public void setTotal(Double total) {
		this.total += getItemComanda().getTotal();
	}*/

	/*public void setTotal() {
		this.total = getProducto().getPrecio() * this.cantidad;
	}*/
	
	/*public void setTotal() {
		
	}*/
	
	@Override
	public String toString() {
		return "Comprobante [id=" + id + ", fecha=" + fecha + ", mesaUso=" + mesaUso + ", total=" + total + "]";
	}

	

	

}
