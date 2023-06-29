package comanda.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "Comandas") // Esto debe coincidir con el nombre de la tabla tal cual en bd.
public class Comanda {

	@Id // para que se sepa que es primary key
	@GeneratedValue(strategy = GenerationType.IDENTITY) // para que la pk sea autoincremental y la estrategia de cómo se
														// va a generar (en mysql).
	@Column(name = "COMAND_ID")
	private Integer id;
	// @Column(name = "COMAND_RESERV")
	// private Integer reserva;
	// @OneToOne
	// @JoinColumn(name = "COMAND_RESERV") // "idReserva")
	// private Reserva reserva;
	@ManyToOne
	@JoinColumn(name = "COMAND_ESTADO") // "idEstado")
	private Estado estado;
	@ManyToOne
	@JoinColumn(name = "COMAND_MESAUSO") // "idMesaUso")
	@JsonBackReference
	private MesaUso mesaUso;

	@OneToMany(mappedBy = "comanda", cascade = CascadeType.PERSIST, fetch = FetchType.LAZY, orphanRemoval = true)
	@JsonManagedReference
	private List<ItemComanda> itemComandas;

	public Comanda() {
		super();
	}

	public Comanda(Estado estado, MesaUso mesaUso, List<ItemComanda> itemComandas) {
		super();
		this.estado = estado;
		this.mesaUso = mesaUso;
		this.itemComandas = itemComandas;
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

	public MesaUso getMesaUso() {
		return mesaUso;
	}

	public void setMesaUso(MesaUso mesaUso) {
		this.mesaUso = mesaUso;
	}



	public List<ItemComanda> getItemComandas() {
		return itemComandas;
	}

	public void setItemComandas(List<ItemComanda> itemComandas) {
		this.itemComandas = itemComandas;
	}

	public void addItemComanda(ItemComanda itemComanda) {
		this.itemComandas.add(itemComanda);
	}


	@Override
	public String toString() {
		return "Comanda [id=" + id + ", estado=" + estado + ", mesaUso=" + mesaUso + ", itemComandas=" + itemComandas.size()
				+ "]";
	}

}
