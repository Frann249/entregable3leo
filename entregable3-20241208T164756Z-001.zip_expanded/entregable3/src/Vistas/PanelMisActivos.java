package Vistas;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class PanelMisActivos extends JPanel {

    private final Font fuenteS = new Font("Arial", Font.PLAIN, 12);
    private final Font fuenteM = new Font("Arial", Font.PLAIN, 16);
    private final Font fuenteL = new Font("Arial", Font.BOLD, 20);

    public PanelMisActivos() {
        this.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        // Colores personalizados
        Color colorCeleste = new Color(92, 195, 242);
        Color colorRojo = new Color(255, 69, 58);
        
	    // Sección superior: Logo, nombre y botón
	    // Redimensionar el logo del usuario
	    ImageIcon iconoPerfil = new ImageIcon(new ImageIcon("Logos/perfil.png").getImage().getScaledInstance(45, 45, Image.SCALE_SMOOTH));
	    JLabel lblLogoUsuario = new JLabel(iconoPerfil); // Logo usuario
	
	    // Etiqueta del nombre
	    JLabel lblNombreUsuario = new JLabel("Nombre Apellido");
	    lblNombreUsuario.setFont(fuenteM);
	
	    // Botón de cerrar sesión
	    JButton btnCerrarSesion = new JButton("Cerrar sesión");
	    btnCerrarSesion.setBackground(colorCeleste);
	    btnCerrarSesion.setForeground(Color.WHITE);
	
	    // Configuración del layout
	    gbc.insets = new Insets(10, 10, 10, 10);
	    gbc.anchor = GridBagConstraints.EAST;
	    gbc.gridx = 1;
	    gbc.gridy = 0;
	    this.add(lblLogoUsuario, gbc);
	
	    gbc.gridx = 2;
	    gbc.anchor = GridBagConstraints.WEST;
	    this.add(lblNombreUsuario, gbc);
	
	    gbc.gridx = 2;
	    gbc.gridy = 1;
	    gbc.anchor = GridBagConstraints.SOUTH;
	    this.add(btnCerrarSesion, gbc);

        // Botón Generar Datos de Prueba
        JButton btnGenerarDatos = new JButton("Generar Datos de Prueba");
        btnGenerarDatos.setBackground(colorRojo);
        btnGenerarDatos.setForeground(Color.WHITE);
        btnGenerarDatos.setFont(fuenteS);

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 3;
        gbc.anchor = GridBagConstraints.WEST;
        this.add(btnGenerarDatos, gbc);

        // Balance
        JLabel lblIconoBalance = new JLabel(new ImageIcon("Logos/balance.png"));
        JLabel lblBalance = new JLabel("Balance: ARS 8'000,000.39");
        lblBalance.setFont(fuenteL);

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.EAST;
        this.add(lblIconoBalance, gbc);

        gbc.gridx = 1;
        this.add(lblBalance, gbc);

        // Tabla de Criptos
        String[] columnas = {"", "Cripto", "Monto"};
        Object[][] datos = {
            {new ImageIcon("path/to/bitcoin-icon.png"), "Bitcoin", "$6960.39"},
            {new ImageIcon("path/to/dogecoin-icon.png"), "Dogecoin", "$30.39"},
            {new ImageIcon("path/to/peso-icon.png"), "Peso Argentino", "ARS 1000000"}
        };

        JTable tablaCriptos = new JTable(new DefaultTableModel(datos, columnas) {
            @Override
            public Class<?> getColumnClass(int columnIndex) {
                return columnIndex == 0 ? ImageIcon.class : String.class;
            }
        });

        tablaCriptos.setRowHeight(40);
        tablaCriptos.setFont(fuenteM);
        JScrollPane scrollTabla = new JScrollPane(tablaCriptos);

        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 3;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        this.add(scrollTabla, gbc);

        // Botón Exportar CSV
        JButton btnExportarCSV = new JButton("Exportar como CSV");
        btnExportarCSV.setBackground(colorCeleste);
        btnExportarCSV.setForeground(Color.WHITE);

        gbc.gridx = 1;
        gbc.gridy = 4;
        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.CENTER;
        this.add(btnExportarCSV, gbc);
        
     // Botones inferiores
        JButton btnMisOperaciones = new JButton("Mis Operaciones");
        JButton btnCotizaciones = new JButton("Cotizaciones");

        // Crear iconos redimensionados
        ImageIcon iconoOperaciones = new ImageIcon(new ImageIcon("Logos/misOperaciones.png").getImage().getScaledInstance(35, 35, Image.SCALE_SMOOTH));
        ImageIcon iconoCotizaciones = new ImageIcon(new ImageIcon("Logos/cotizaciones.png").getImage().getScaledInstance(35, 35, Image.SCALE_SMOOTH));

        // Asignar los iconos a los botones
        btnMisOperaciones.setIcon(iconoOperaciones);
        btnCotizaciones.setIcon(iconoCotizaciones);

        // Ajustar posición del texto e ícono
        btnMisOperaciones.setHorizontalTextPosition(SwingConstants.RIGHT); // Texto a la derecha del ícono
        btnMisOperaciones.setVerticalTextPosition(SwingConstants.CENTER);  // Texto centrado verticalmente

        btnCotizaciones.setHorizontalTextPosition(SwingConstants.RIGHT);
        btnCotizaciones.setVerticalTextPosition(SwingConstants.CENTER);

        // Configuración de colores
        btnMisOperaciones.setBackground(colorCeleste);
        btnCotizaciones.setBackground(colorCeleste);
        btnMisOperaciones.setForeground(Color.WHITE);
        btnCotizaciones.setForeground(Color.WHITE);

        // Posicionar botones
        gbc.gridx = 0;
        gbc.gridy = 5;
        this.add(btnMisOperaciones, gbc);

        gbc.gridx = 2;
        this.add(btnCotizaciones, gbc);
    }
}
