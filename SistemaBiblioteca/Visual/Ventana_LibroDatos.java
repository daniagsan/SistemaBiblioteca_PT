package SistemaBiblioteca.Visual;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.util.ResourceBundle;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.itextpdf.layout.borders.Border;

import SistemaBiblioteca.modelos.LibroData;

public class Ventana_LibroDatos extends JFrame{

    private DisplayText displayStrings  = new DisplayText();
    private ResourceBundle rb = displayStrings.getRb();

    public Ventana_LibroDatos(LibroData libro){
        setVisible(true);
		setTitle(rb.getString("dataBookWindowName"));
		setLayout(new BorderLayout());
        setPreferredSize(getPreferredSize());

        JPanel infoPanel = new JPanel(new BorderLayout());
        infoPanel.setLayout(new BoxLayout(infoPanel, BoxLayout.Y_AXIS));

        JLabel datos[] = {new JLabel(libro.getTitulo()),
                          new JLabel(libro.getAutor()),
                          new JLabel(libro.getYear()),
                          new JLabel(libro.getSinopsis()),
                          new JLabel(libro.getIsbn()),
                          new JLabel(libro.getEditorial()),
                          new JLabel(libro.getEdicion())};

        for(int x = 0; x < datos.length; x++){
            datos[x].setFont(new java.awt.Font("Arial", java.awt.Font.BOLD, 15));
            infoPanel.add(datos[x]);
        }

        
        setPreferredSize(new Dimension(300, 500));

        // Pack the frame to respect preferred sizes of its components
        pack();
        add(infoPanel, BorderLayout.CENTER);
    }

}
