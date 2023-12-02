package comanda.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "Locales") // Esto debe coincidir con el nombre de la tabla tal cual en bd.
public class Local {

	@Id // para que se sepa que es primary key
	@GeneratedValue(strategy = GenerationType.IDENTITY) // para que la pk sea autoincremental y la estrategia de cómo se
														// va a generar (en mysql).
	@Column(name = "LOCAL_ID")
	private Integer id;
	@Column(name = "LOCAL_NOMBRE")
	private String nombre;
	@Column(name = "LOCAL_CALLE")
	private String calle;
	@Column(name = "LOCAL_ALTURA")
	private Integer altura;
	@Column(name = "LOCAL_CODPOS")
	private Integer codigo_postal;
	@Column(name = "LOCAL_TELEFN")
	private Integer telefono;
	@Column(name = "LOCAL_IMG")
	private String imagen;

	@OneToMany(mappedBy = "local", cascade = CascadeType.PERSIST, fetch = FetchType.LAZY, orphanRemoval = true)		
	@JsonManagedReference
	private List<UsuarioLocal> usuariosLocales;
		
	public Local() {
		super();		
	}	

	public Local(String nombre, String calle, Integer altura, Integer codigo_postal, Integer telefono,
			String imagen, List<UsuarioLocal> usuariosLocales) {
		super();		
		this.nombre = nombre;
		this.calle = calle;
		this.altura = altura;
		this.codigo_postal = codigo_postal;
		this.telefono = telefono;
		this.imagen = imagen;
		this.usuariosLocales = usuariosLocales;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getCalle() {
		return calle;
	}

	public void setCalle(String calle) {
		this.calle = calle;
	}

	public Integer getAltura() {
		return altura;
	}

	public void setAltura(Integer altura) {
		this.altura = altura;
	}

	public Integer getCodigo_postal() {
		return codigo_postal;
	}

	public void setCodigo_postal(Integer codigo_postal) {
		this.codigo_postal = codigo_postal;
	}

	public Integer getTelefono() {
		return telefono;
	}

	public void setTelefono(Integer telefono) {
		this.telefono = telefono;
	}

	public String getImagen() {
		return imagen;
	}

	public void setImagen(String imagen) {
		this.imagen = imagen;
	}
	
	

	public List<UsuarioLocal> getUsuariosLocales() {
		return usuariosLocales;
	}

	public void setUsuariosLocales(List<UsuarioLocal> usuariosLocales) {
		this.usuariosLocales = usuariosLocales;
	}

	/*@Override
	public String toString() {
		return "Local [id=" + id + ", nombre=" + nombre + ", calle=" + calle + ", altura=" + altura + ", codigo_postal="
				+ codigo_postal + ", telefono=" + telefono + ", imagen=" + imagen + ", usuariosLocales="
				+ usuariosLocales.size() + "]";
	}*/

	@Override
	public String toString() {
	    String usuariosLocalesInfo = (usuariosLocales != null) ? String.valueOf(usuariosLocales.size()) : "null";
	    return "Local [id=" + id + ", nombre=" + nombre + ", calle=" + calle + ", altura=" + altura +
	            ", codigo_postal=" + codigo_postal + ", telefono=" + telefono + ", imagen=" + imagen +
	            ", usuariosLocales=" + usuariosLocalesInfo + "]";
	}

	
}