package comanda.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "Mesauso") // Esto debe coincidir con el nombre de la tabla tal cual en bd.
public class MesaUso {

	@Id // para que se sepa que es primary key
	@GeneratedValue(strategy = GenerationType.IDENTITY) // para que la pk sea autoincremental y la estrategia de cómo se
														// va a generar (en mysql).
	//@Column(name = "")
	private Integer id;
	//@Column(name = "")
	private Date inicio;
	//@Column(name = "")
	private Date fin;
	
	private Mesa mesa;
	
	private Comanda comanda;
	
	private Double Total;
	
	private Comprobante comprobante;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getInicio() {
		return inicio;
	}

	public void setInicio(Date inicio) {
		this.inicio = inicio;
	}

	public Date getFin() {
		return fin;
	}

	public void setFin(Date fin) {
		this.fin = fin;
	}

	public Mesa getMesa() {
		return mesa;
	}

	public void setMesa(Mesa mesa) {
		this.mesa = mesa;
	}

	public Comanda getComanda() {
		return comanda;
	}

	public void setComanda(Comanda comanda) {
		this.comanda = comanda;
	}

	public Double getTotal() {
		return Total;
	}

	public void setTotal(Double total) {
		Total = total;
	}

	public Comprobante getComprobante() {
		return comprobante;
	}

	public void setComprobante(Comprobante comprobante) {
		this.comprobante = comprobante;
	}

	@Override
	public String toString() {
		return "MesaUso [id=" + id + ", inicio=" + inicio + ", fin=" + fin + ", mesa=" + mesa + ", comanda=" + comanda
				+ ", Total=" + Total + ", comprobante=" + comprobante + "]";
	}
	
	
	
}
