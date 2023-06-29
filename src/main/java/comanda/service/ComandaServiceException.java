package comanda.service;

public class ComandaServiceException extends Exception {

	/**
	 *
	 */
	private static final long serialVersionUID = -309971915432626890L;
	private String codigo;

	public ComandaServiceException(String codigo, String message) {
		super(message);
		this.codigo = codigo;
	}

	public String getCodigo() {
		return codigo;
	}

}
