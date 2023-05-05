package comanda.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "USUARIOS_LOCALES") // Esto debe coincidir con el nombre de la tabla tal cual en bd.

public class UsuarioLocal {
	
	@Id // para que se sepa que es primary key
	@GeneratedValue(strategy = GenerationType.IDENTITY) // para que la pk sea autoincremental y la estrategia de cómo se
														// va a generar (en mysql).
	@Column(name = "USER_LOCAL_ID")
	private Integer id;

	@ManyToOne
	@JoinColumn(name = "USER_ID") // "idComanda")
	private Usuario usuario;

	@ManyToOne
	@JoinColumn(name = "LOCAL_ID") // "idProducto")
	private Local local;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Local getLocal() {
		return local;
	}

	public void setLocal(Local local) {
		this.local = local;
	}

	@Override
	public String toString() {
		return "UsuarioLocal [id=" + id + ", usuario=" + usuario + ", local=" + local + "]";
	}

	

}