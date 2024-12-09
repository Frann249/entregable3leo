package controladores;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;

import Vistas.Vista;
import app.Modelo;
import appModels.Cripto;


public class Controlador {
	private Vista vista;
	private Modelo modelo;
	
	public Controlador(Vista vista, Modelo modelo){
		this.vista = vista;
		this.setModelo(modelo);
	
		vista.getCotizaciones().configurar(modelo.listarMonedasTipo(modelo.CRIPTO));
		List<JButton> buttonsList = vista.getCotizaciones().getButtons();
		for(JButton botonAct : buttonsList) {
			botonAct.addActionListener(new ListenerCotizacion());
		}
		buttonsList = vista.getCompra().getButtons();
		for(JButton botonAct : buttonsList) {
			botonAct.addActionListener(new ListenerCompra());
		}
		vista.getMisActivos().getBtnGenerarDatos().addActionListener(new ListenerMisActivos());
        vista.getMisActivos().getBtnExportarCSV().addActionListener(new ListenerMisActivos());
		vista.mostrarPanel("ACTIVOS");
		vista.setVisible(true);
	}
	

	/**
	 * @return the modelo
	 */
	public Modelo getModelo() {
		return modelo;
	}

	/**
	 * @param modelo the modelo to set
	 */
	public void setModelo(Modelo modelo) {
		this.modelo = modelo;
	}


	class ListenerCotizacion implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			String command = e.getActionCommand();
			
			if (command.contains("SWAP")) {
	            System.out.println("Esta función no está disponible.");

	            // Crear el JDialog
	            JDialog dialog = new JDialog(vista, "NOTIFICACIÓN", true); // Modal
	            dialog.setLayout(new FlowLayout());
	            dialog.setSize(300, 100);
	            dialog.setLocationRelativeTo(vista);

	            // Etiqueta con el mensaje
	            JLabel label = new JLabel("La función SWAP aún no está disponible.");
	            dialog.add(label);

	            // Botón "Aceptar" con acción para cerrar el diálogo
	            JButton botonAcept = new JButton("Aceptar");
	            botonAcept.addActionListener(new ActionListener() {
	                @Override
	                public void actionPerformed(ActionEvent e) {
	                    dialog.dispose(); // Cerrar el diálogo
	                }
	            });
	       
	            dialog.add(botonAcept);
	            // Mostrar el diálogo
	            dialog.setVisible(true);
	        } 
			else {
				Cripto cripto = (Cripto) modelo.getMoneda(command);
				vista.getCompra().actualizarValores(cripto, modelo.listarActivosTipo(modelo.FIAT));;
				vista.mostrarPanel("COMPRAR");
			}
		}
	}
	class ListenerCompra implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			String command = e.getActionCommand();
			if(command.equals("CONVERTIR")) {
				String nomenclaturaSelect = vista.getCompra().getActivoSelected();
				vista.getCompra().convertir(modelo.getMoneda(nomenclaturaSelect));
			}
			if(command.equals("REALIZAR")) {
				
			}
			if(command.equals("CANCELAR")) {
				vista.getCotizaciones().actualizarPrecios(modelo.listarMonedasTipo(modelo.CRIPTO));
				vista.mostrarPanel("COTIZACION");
			}
		}
		
	}
	
	// Listener específico para el panel "Mis Activos"
    class ListenerMisActivos implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String command = e.getActionCommand();

            if (command.equals("GENERAR_DATOS")) {
                // Lógica para generar datos de prueba
                vista.getMisActivos().generarDatosDePrueba();
            }

            if (command.equals("EXPORTAR_CSV")) {
                // Lógica para exportar los datos como CSV
                vista.getMisActivos().exportarComoCSV();
            }
        }
    }
	
}