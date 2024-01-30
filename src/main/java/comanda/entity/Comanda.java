package comanda.entity;

import java.util.Date;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "Comandas") // Esto debe coincidir con el nombre de la tabla tal cual en bd.
public class Comanda {


	@Id // para que se sepa que es primary key
	@GeneratedValue(strategy = GenerationType.IDENTITY) // para que la pk sea autoincremental y la estrategia de cómo se
														// va a generar (en mysql).
	@Column(name = "COMAND_ID")
	private Integer id;	
	@ManyToOne
	@JoinColumn(name = "COMAND_ESTADO") // "idEstado")
	private Estado estado;
	@ManyToOne
	@JoinColumn(name = "COMAND_MESA")
//	FetchType.EAGER
	private Mesa mesa;

	@Column
	private Date fecha;

	@Column
	private Double total;


	public Comanda() {
		super();
		this.fecha = new Date();
	}

	public Comanda(Estado estado, List<ItemComanda> itemComandas) {
		super();
		this.estado = estado;
		this.fecha = new Date();
//		/this.itemComandas = itemComandas;
	}

	public Comanda(Mesa table) {
		this.mesa = table;
		this.estado = new Estado(2);
		this.fecha = new Date();
	}

	public Comanda(Mesa mesa, Estado estado) {
		this.mesa = mesa;
		this.estado = estado;
	}

    public Comanda(Date date) {
		this.fecha = date;
    }

    public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Estado getEstado() {
		return estado;
	}

	public void setEstado(Estado estado) {
		this.estado = estado;
	}

	public Mesa getMesa() {
		return mesa;
	}

	public void setMesa(Mesa mesa) {
		this.mesa = mesa;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public Double getTotal() {
		return total;
	}

	public void setTotal(Double total) {
		this.total = total;
	}
}
