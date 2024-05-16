package SistemaBiblioteca.Visual;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JOptionPane;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Image;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JPanel;

public class VisualMain extends JPanel{

    ArrayList<JButton> bookButtons = new ArrayList<>();

    public VisualMain(){

        setLayout(new BorderLayout(5,5));
		setBorder(BorderFactory.createEmptyBorder(5,5,5,5));
        add(panelLibros(), BorderLayout.CENTER);
        
    }

    public void addListener(ActionListener listener) {

        for(JButton b: bookButtons){
            b.addActionListener(listener);
        }
    }

    public JButton creadorLibros(String imageDir){

        
        return libro;
    }

    public JPanel panelLibros(JButton libro){

        
        JPanel areaLibros = new JPanel(new FlowLayout(FlowLayout.LEFT));
        /*
         * Aqui se van a mostrar los libros en el centro
         */

         JButton libro = new JButton();
        //Parametros visuales: Imagen,
        //La imagen puede ser prescindible, en caso de que no haya imagen, colocar texto
        int newWidth = 100;
        int newHeight = 150;
        Dimension defaultButtonSize = new Dimension(newWidth, newHeight);

        try{
            
            BufferedImage originalImage = ImageIO.read(new File(imageDir));
            Image resizedImage = originalImage.getScaledInstance(newWidth, newHeight, Image.SCALE_AREA_AVERAGING);
            
            //anade un color vacio
            libro.setBackground(new Color(0,0,0,0));
            //quitamos el borde
            libro.setBorderPainted(false);
            //la posicion del texto la pone en medio
            libro.setHorizontalTextPosition(JButton.CENTER);
            //darle un color transparente
            libro.setForeground(new Color(0,0,0,0));
            /*
             * Esta configuracion nos permite crear un boton y mandarlo a llamar con
             * el texto, por lo que no sera visible al publico pero si para 
             * el desarrollador
             */
            libro.setIcon(new ImageIcon(resizedImage));
            
        }catch(IOException ex){
            libro.setPreferredSize(defaultButtonSize);
            libro.setBackground(Color.white);
            //quitamos el borde
            libro.setBorderPainted(true);
            //la posicion del texto la pone en medio
            libro.setHorizontalTextPosition(JButton.CENTER);
            //darle un color transparente
            libro.setForeground(Color.black);

            //Apartado de aviso
            //JOptionPane.showMessageDialog(this, ex, "Falla al cargar archivo de imagen", 0);
            ex.printStackTrace();
        }


        bookButtons.add(libro);


        areaLibros.add(bookButtons.get(0));
    
        

        return areaLibros;
    }

}
