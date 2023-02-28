package comanda.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Comprobantes") // Esto debe coincidir con el nombre de la tabla tal cual en bd.
public class Comprobante {

	@Id // para que se sepa que es primary key
	@GeneratedValue(strategy = GenerationType.IDENTITY) // para que la pk sea autoincremental y la estrategia de cómo se
														// va a generar (en mysql).
	//@Column(name = "PRODUC_ID")
	private Integer id;
	//@Column(name = "PRODUC_ID")
	private Integer nro_comprobante;
	
	private Date fecha;
	
	//private MesaUso mesUso;
	
	private double total;
	
	//private ItemComprobante itemComprobante;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getNro_comprobante() {
		return nro_comprobante;
	}

	public void setNro_comprobante(Integer nro_comprobante) {
		this.nro_comprobante = nro_comprobante;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	/*public MesaUso getMesUso() {
		return mesUso;
	}

	public void setMesUso(MesaUso mesUso) {
		this.mesUso = mesUso;
	}*/

	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}

	/*@Override
	public String toString() {
		return "Comprobante [id=" + id + ", nro_comprobante=" + nro_comprobante + ", fecha=" + fecha + ", mesUso="
				+ mesUso + ", total=" + total + "]";
	}*/
	

	
	
	
	
}
