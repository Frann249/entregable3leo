package Vistas;


import java.awt.*;


import javax.swing.*;

import appDAO.FactoryDAO;

public class Vista extends JFrame {
    private JPanel mainPanel; // Panel principal que contendrá los subpaneles
    private CardLayout cardLayout; // Para cambiar entre paneles
    private PanelCotizaciones cotizaciones;
    private PanelCompra compra;
    private PanelMisActivos activos;
    private PanelRegistro registro;
    private PanelLogin login;
    // Constructor
    public Vista() {
        // Configuración del JFrame
        setTitle("Gestión de Paneles");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Inicialización del CardLayout y el panel principal
        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);

        // Crear los subpaneles
        cotizaciones = new PanelCotizaciones();
        compra = new PanelCompra(); 
        activos = new PanelMisActivos();
        registro = new PanelRegistro();
        login = new PanelLogin();
        // Añadir subpaneles al CardLayout
        mainPanel.add(cotizaciones, "COTIZACION");
        mainPanel.add(activos, "ACTIVOS");
        mainPanel.add(compra, "COMPRAR");
        mainPanel.add(registro, "REGISTRO");        
        mainPanel.add(login, "LOGIN");
        add(mainPanel, BorderLayout.CENTER);
        setLocationRelativeTo(null);
    }

    // Método para mostrar un panel específico
    public void mostrarPanel(String nombrePanel) {
    	if(nombrePanel.equals("LOGIN")) {
    		this.setSize(500,500);
    	}
    	if(nombrePanel.equals("COTIZACION")) {
    		this.setSize(780, 500);
    	}
        cardLayout.show(mainPanel, nombrePanel);
    }

    public PanelLogin getLogin() {
    	return this.login;
    }
    public PanelCompra getCompra() {
    	return this.compra;
    }
    
	/**
	 * @return the cotizaciones
	 */
	public PanelCotizaciones getCotizaciones() {
		return cotizaciones;
	}

	/**
	 * @param cotizaciones the cotizaciones to set
	 */
	public void setCotizaciones(PanelCotizaciones cotizaciones) {
		this.cotizaciones = cotizaciones;
	}

	public PanelRegistro getRegistro() {
		return registro;
	}

	public void setRegistro(PanelRegistro registro) {
		this.registro = registro;
	}

}
