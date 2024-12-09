package app;
import javax.swing.SwingUtilities;

import Vistas.Vista;
import controladores.Controlador;

public class Main {

	 public static void main(String[] args) {
	        SwingUtilities.invokeLater(() -> {
	            Vista vista = new Vista();
	            Modelo modelo = new Modelo();
	            Controlador control = new Controlador(vista, modelo);
	            
	        });
	 }
}