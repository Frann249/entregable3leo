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
        
        // Añadir subpaneles al CardLayout
        mainPanel.add(cotizaciones, "COTIZACION");
        mainPanel.add(activos, "ACTIVOS");
        mainPanel.add(compra, "COMPRAR");

        // Añadir el panel principal al JFrame
        add(mainPanel, BorderLayout.CENTER);

    }

    // Método para mostrar un panel específico
    public void mostrarPanel(String nombrePanel) {
        cardLayout.show(mainPanel, nombrePanel);
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
	// Método para obtener el panel de "Mis Activos"
    public PanelMisActivos getMisActivos() {
        return activos;
    }
}
