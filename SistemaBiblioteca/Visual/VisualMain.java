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

import javax.swing.JPanel;

public class VisualMain extends JPanel{

    ButtonGroup bookButton = new ButtonGroup();

    public VisualMain(){

        setLayout(new BorderLayout(5,5));
		setBorder(BorderFactory.createEmptyBorder(5,5,5,5));
        add(panelLibros(), BorderLayout.CENTER);
        
    }

    public void addListener(ActionListener listener) {
    
        creadorLibros("").addActionListener(listener);
    }

    public JButton creadorLibros(String imageDir){

        JButton libro = new JButton();
        //Parametros visuales: Imagen,
        //La imagen puede ser prescindible, en caso de que no haya imagen, colocar texto
        int newWidth = 100;
        int newHeight = 150;
        imageDir = "SistemaBiblioteca/files/portadas/1984.jpg";
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
            Dimension defaultButtonSize = new Dimension(newWidth, newHeight);
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


        bookButton.add(libro);
        return libro;
    }

    public JPanel panelLibros(){

        creadorLibros("");
        JPanel areaLibros = new JPanel(new FlowLayout(FlowLayout.LEFT));
        /*
         * Aqui se van a mostrar los libros en el centro
         */
    
        

        return areaLibros;
    }

}
