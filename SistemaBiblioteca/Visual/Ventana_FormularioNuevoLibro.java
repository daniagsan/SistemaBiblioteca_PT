package SistemaBiblioteca.Visual;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import java.util.ResourceBundle;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SpringLayout;

import lib.SpringUtilities;

public class Ventana_FormularioNuevoLibro extends JFrame{

    private DisplayText displayStrings  = new DisplayText();
    private ResourceBundle rb = displayStrings.getRb();
	private String[] labels = {rb.getString("title"),rb.getString("autor"), rb.getString("year"),
    rb.getString("isbn"),rb.getString("sinopsis"),rb.getString("editorial"),rb.getString("edition")};
    private JTextField campos[] = new JTextField[labels.length];
	private JButton botonRegistrar;
    private JButton botonLimpiar;
	private JButton botonPortada;
	
	
	public Ventana_FormularioNuevoLibro(){

        setVisible(true);
		setTitle(rb.getString("newBookFormWindowName"));
		setLayout(new BorderLayout());

		JPanel panelCampos = new JPanel(new SpringLayout());
        for (int i = 0; i < labels.length; i++) {
            JLabel lbl = new JLabel(labels[i]);
            lbl.setAlignmentX(Component.CENTER_ALIGNMENT);
            panelCampos.add(lbl);
            JTextField text = new JTextField(10);
            text.setAlignmentX(Component.CENTER_ALIGNMENT);
            panelCampos.add(text);

            campos[i] = text;
        }

		SpringUtilities.makeCompactGrid(panelCampos, labels.length, 2, 6, 6, 6, 6);

        add(panelCampos, BorderLayout.NORTH);
        
        JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.CENTER));

        botonRegistrar = new JButton(rb.getString("newBookRegisterButton"));
        botonLimpiar = new JButton(rb.getString("newBookCleanDataButton"));
        botonPortada = new JButton(rb.getString("newBookAddCoverButton"));

        panelBotones.add(botonRegistrar);
        panelBotones.add(botonLimpiar);
        panelBotones.add(botonPortada);
        add(panelBotones, BorderLayout.SOUTH);

		setResizable(false);
		pack();
        
    }

	public void asignarListeners(ActionListener listener) {
    	botonRegistrar.addActionListener(listener);
    	botonLimpiar.addActionListener(listener);
		botonPortada.addActionListener(listener);
    }

    public void portadaAsignada(){
        botonPortada.setBackground(Color.green);
    }
    
    public void limpiarCampos() {
    	for(JTextField txt : campos) {
    		txt.setText("");
    	}
    }
    
    public JTextField getTitulo() {
    	return campos[0];
    }
    
    public JTextField getAutor () {
    	return campos[1];
    }

	public JTextField getYear() {
    	return campos[2];
    }
    
    public JTextField getISBN() {
    	return campos[3];
    }

	public JTextField getSinopsis() {
    	return campos[4];
    }

	public JTextField getEditorial() {
    	return campos[5];
    }

	public JTextField getEdicion() {
    	return campos[6];
    }
	
}
