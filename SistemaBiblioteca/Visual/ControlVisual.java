package SistemaBiblioteca.Visual;

import javax.swing.BorderFactory;
import javax.swing.JButton;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import javax.swing.JPanel;

public class ControlVisual extends JPanel{


    public ControlVisual(){

        setLayout(new BorderLayout(5,5));
		setBorder(BorderFactory.createEmptyBorder(5,5,5,5));
        add(zonaLibros());
        
    }

    public JPanel zonaLibros(){
        /*
         * Aqui se van a mostrar los libros en el centro
         */
        JPanel areaLibros = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JButton prueba = new JButton("Boton de prueba");

        areaLibros.add(prueba);

        return areaLibros;
    }

}
