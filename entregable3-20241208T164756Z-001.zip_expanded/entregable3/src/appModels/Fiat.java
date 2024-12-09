package appModels;

/**
 * Esta clase representa un tipo de moneda fiduciaria.
 */
public class Fiat extends Moneda {

    public Fiat(int ID,String nombre_Icono, String nombre, String nomenclatura, double valor_Dolar){
		super(ID,nombre_Icono, nombre, nomenclatura, valor_Dolar);
	}
    public Fiat(String nombre_Icono, String nombre, String nomenclatura, double valor_Dolar){
		super(nombre_Icono, nombre, nomenclatura, valor_Dolar);
	}
	
}
