package SistemaBiblioteca.Visual;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
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

import SistemaBiblioteca.modelos.LibroData;

public class VisualMain extends JPanel{

    ArrayList<LibroData> bookButtons = new ArrayList<>();

    public VisualMain(){

        setLayout(new BorderLayout(5,5));
		setBorder(BorderFactory.createEmptyBorder(5,5,5,5));

        add(panelLibros(), BorderLayout.CENTER);
        
    }

    public void addListener(ActionListener listener) {

        for(LibroData b: bookButtons){
            b.getBotonLibro().addActionListener(listener);
        }
    }


    public LibroData creadorLibro(String titulo, String autor, String imageDir){
        //Primero  se ponen los  parametros del boton
        LibroData libro =  new LibroData();
        libro.setTitulo(titulo);
        libro.setAutor(autor);
        libro.setDirFilePortada(imageDir);
        libro.setBotonLibro(new JButton(titulo));
        
        libro.getBotonLibro().setHorizontalTextPosition(JButton.CENTER);
        libro.getBotonLibro().setRolloverEnabled(true);
        //se asigna el tamano de los botones
        int newWidth = 100;
        int newHeight = 150;
        Dimension defaultButtonSize = new Dimension(newWidth, newHeight);

        try{
            BufferedImage originalImage = ImageIO.read(new File(libro.getDirFilePortada()));
            Image resizedImage = originalImage.getScaledInstance(newWidth, newHeight, Image.SCALE_AREA_AVERAGING);
            
            //anade un color vacio
            libro.getBotonLibro().setBackground(new Color(0,0,0,0));
            //quitamos el borde
            libro.getBotonLibro().setBorderPainted(false);
            //la posicion del texto la pone en medio
            libro.getBotonLibro().setHorizontalTextPosition(JButton.CENTER);
            //darle un color transparente
            libro.getBotonLibro().setForeground(new Color(0,0,0,0));
            /*
             * Esta configuracion nos permite crear un boton y mandarlo a llamar con
             * el texto, por lo que no sera visible al publico pero si para 
             * el desarrollador
             */
            libro.getBotonLibro().setIcon(new ImageIcon(resizedImage));
            
        }catch(IOException ex){
            
            libro.getBotonLibro().setPreferredSize(defaultButtonSize);
            libro.getBotonLibro().setBackground(Color.white);
            //Proceso contrario para mostrar el texto
            libro.getBotonLibro().setBorderPainted(true);
            libro.getBotonLibro().setForeground(Color.black);

            //Apartado de aviso
            //JOptionPane.showMessageDialog(this, ex, "Falla al cargar archivo de imagen", 0);
            ex.printStackTrace();
        }

    
        bookButtons.add(libro);

        //return areaLibros;
        return libro;
    }

    public JPanel panelLibros(){

        libroEjemplo();
        JPanel areaLibros = new JPanel(new FlowLayout(FlowLayout.LEFT));

        for(LibroData b: bookButtons){
            areaLibros.add(b.getBotonLibro());
        }

        return areaLibros;

    }

    public void libroEjemplo(){
        creadorLibro("1984", "", "");
        creadorLibro("las mil un batallas", "", "");
        creadorLibro("1984", "", "");
        creadorLibro("1984", "", "");
        creadorLibro("1984", "", "");

    }

}
