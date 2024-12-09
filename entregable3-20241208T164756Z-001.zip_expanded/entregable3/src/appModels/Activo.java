package appModels;

public class Activo {
	private String nomenclatura;
	private double cantidad;
	
	private int ID;
	private int ID_USUARIO;
	private int ID_MONEDA;
	
	
	/**
	 * @return the moneda
	 */
	public Activo(String nomenclatura, double cantidad){
		this.setNomenclatura(nomenclatura);
		this.cantidad = cantidad;
	}
	public Activo(int ID, int ID_USUARIO, int ID_MONEDA,double cantidad){
		this.ID = ID;
		this.ID_USUARIO = ID_USUARIO;
		this.ID_MONEDA = ID_MONEDA;
		this.cantidad = cantidad;
	}
	/**
	 * @return the cantidad
	 */
	public double getCantidad() {
		return cantidad;
	}
	/**
	 * @param cantidad the cantidad to set
	 */
	public void setCantidad(double cantidad) {
		this.cantidad = cantidad;
	}

	/**
	 * @return the nomenclatura
	 */
	public String getNomenclatura() {
		return nomenclatura;
	}

	/**
	 * @param nomenclatura the nomenclatura to set
	 */
	public void setNomenclatura(String nomenclatura) {
		this.nomenclatura = nomenclatura;
	}

	public int getID() {
		return ID;
	}

	public void setID(int iD) {
		ID = iD;
	}

	public int getID_USUARIO() {
		return ID_USUARIO;
	}

	public void setID_USUARIO(int iD_USUARIO) {
		ID_USUARIO = iD_USUARIO;
	}

	public int getID_MONEDA() {
		return ID_MONEDA;
	}

	public void setID_MONEDA(int iD_MONEDA) {
		ID_MONEDA = iD_MONEDA;
	}
	
}
