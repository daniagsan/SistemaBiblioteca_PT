package SistemaBiblioteca.Visual;

import java.awt.BorderLayout;
import java.util.ResourceBundle;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import SistemaBiblioteca.modelos.LibroData;

public class Ventana_LibroDatos extends JFrame{

    private DisplayText displayStrings  = new DisplayText();
    private ResourceBundle rb = displayStrings.getRb();

    public Ventana_LibroDatos(LibroData libro){
        setVisible(true);
		setTitle(rb.getString("dataBookWindowName"));
		setLayout(new BorderLayout());

        JPanel infoPanel = new JPanel();

        JLabel datos[] = {new JLabel(libro.getTitulo()),
                          new JLabel(libro.getAutor()),
                          new JLabel(libro.getYear()),
                          new JLabel(libro.getSinopsis()),
                          new JLabel(libro.getIsbn()),
                          new JLabel(libro.getEditorial()),
                          new JLabel(libro.getEdicion())};

        for(int x = 0; x < datos.length; x++){
            infoPanel.add(datos[x]);
        }

        add(infoPanel);
    }

}
