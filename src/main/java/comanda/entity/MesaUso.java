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
import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.Builder;
import lombok.Data;

@Data
//@NoArgsConstructor
//@AllArgsConstructor
@Builder


@Entity
@Table(name = "Mesausos") // Esto debe coincidir con el nombre de la tabla tal cual en bd.
public class MesaUso {

	@Id // para que se sepa que es primary key
	@GeneratedValue(strategy = GenerationType.IDENTITY) // para que la pk sea autoincremental y la estrategia de cómo se
														// va a generar (en mysql).
	@Column(name = "MESAUSO_ID")
	private Integer id;

	// private Date inicio;

	// private Date fin;

	@ManyToOne
	@JoinColumn(name = "MESAUSO_MESA") // "idMesa")
	private Mesa mesa;

	// @Column(name = "MESAUSO_TOTAL")
	// private Double Total;

	@OneToMany(mappedBy = "mesaUso", cascade = CascadeType.PERSIST, fetch = FetchType.LAZY, orphanRemoval = true)
	@JsonManagedReference
	private List<Comanda> comandas;

	public MesaUso() {
		super();
	}

	public MesaUso(Integer id, Mesa mesa, List<Comanda> comandas) {
		this.id = id;
		this.mesa = mesa;
		this.comandas = comandas;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Mesa getMesa() {
		return mesa;
	}

	public void setMesa(Mesa mesa) {
		this.mesa = mesa;
	}

	public List<Comanda> getComandas() {
		return comandas;
	}

	public void setComandas(List<Comanda> comandas) {
		this.comandas = comandas;
	}

	@Override
	public String toString() {
		return "MesaUso [id=" + id + ", mesa=" + mesa + "]";
	}

}
