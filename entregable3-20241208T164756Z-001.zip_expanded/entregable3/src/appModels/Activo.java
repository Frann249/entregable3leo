package appModels;

public class Activo {
	private String nomenclatura;
	private double cantidad;
	/**
	 * @return the moneda
	 */
	public Activo(String nomenclatura, double cantidad){
		this.setNomenclatura(nomenclatura);
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
	
}
