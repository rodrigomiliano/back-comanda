package comanda.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "Usuarios") // Esto debe coincidir con el nombre de la tabla tal cual en bd.
public class Usuario {

	@Id // para que se sepa que es primary key
	@GeneratedValue(strategy = GenerationType.IDENTITY) // para que la pk sea autoincremental y la estrategia de cómo se
														// va a generar (en mysql).
	@Column(name = "USER_ID")
	private Integer id;
	@Column(name = "USER_USER")
	private String usuario;
	@Column(name = "USER_NOMBRE")
	private String nombre;
	@Column(name = "USER_APELLI")
	private String apellido;
	@Column(name = "USER_NRODOC")
	private Integer dni;
	@Column(name = "USER_EMAIL")
	private String email;
	@Column(name = "USER_TELEFN")
	private String telefono;
	@Column(name = "USER_PASWRD")
	private String contrasena;
	// @Column(name = "USER_ROL")
	// private Integer rol;
	@ManyToOne
	@JoinColumn(name = "USER_ROL") // "idRol")
	private Rol rol;

	@OneToMany(mappedBy = "usuario")	
	//@JoinTable(name = "USUARIOS_LOCALES", joinColumns = @JoinColumn(name = "LOCAL_ID", referencedColumnName = "LOCAL_ID"), inverseJoinColumns = @JoinColumn(name = "USER_ID", referencedColumnName = "USER_ID"))
	private List<UsuarioLocal> usuariosLocales;
	
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public Integer getDni() {
		return dni;
	}

	public void setDni(Integer dni) {
		this.dni = dni;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getContrasena() {
		return contrasena;
	}

	public void setContrasena(String contrasena) {
		this.contrasena = contrasena;
	}

	public Rol getRol() {
		return rol;
	}
	/*public String getRol() {
		return rol.getNombre();
	}*/

	public void setRol(Rol rol) {
		this.rol = rol;
	}

	@Override
	public String toString() {
		return "Usuario [id=" + id + ", usuario=" + usuario + ", nombre=" + nombre + ", apellido=" + apellido + ", dni="
				+ dni + ", email=" + email + ", telefono=" + telefono + ", contrasena=" + contrasena + ", rol=" + rol
				+ "]";
	}

}
