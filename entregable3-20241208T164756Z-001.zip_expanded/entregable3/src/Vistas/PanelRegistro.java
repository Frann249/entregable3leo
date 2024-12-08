package Vistas;

import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class PanelRegistro extends JPanel{
	private final Font fuenteTxt = new Font("Arial", Font.BOLD, 24);
	private final Font fuenteField= new Font("Arial", Font.BOLD, 15);
	private List<JTextField> camposTexto;
	public PanelRegistro() {
		setLayout(new GridBagLayout());
		camposTexto = new ArrayList<JTextField>(4);
		GridBagConstraints gbc = new GridBagConstraints();
		
		gbc.gridx = 0;
		gbc.gridy = 0;
		
		JLabel nombre = new JLabel("Nombres: ");
		nombre.setFont(fuenteTxt);
		this.add(nombre);
		
		JTextField textNombre = new JTextField(15);
		this.add(textNombre);
		
		camposTexto.add(textNombre);
	}
}
