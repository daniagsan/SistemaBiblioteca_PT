package SistemaBiblioteca.Visual;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Image;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.swing.JPanel;

public class VisualMain extends JPanel{

    JButton prueba = new JButton("1984");

    public VisualMain(){

        setLayout(new BorderLayout(5,5));
		setBorder(BorderFactory.createEmptyBorder(5,5,5,5));
        add(zonaLibros(), BorderLayout.CENTER);
        
    }

    public void addListener(ActionListener listener) {
    
        prueba.addActionListener(listener);
		
    }

    public JPanel zonaLibros(){
        JPanel areaLibros = new JPanel(new FlowLayout(FlowLayout.LEFT));
        /*
         * Aqui se van a mostrar los libros en el centro
         */
        
        try{
            int newWidth = 100;
            int newHeight = 150;
            BufferedImage originalImage = ImageIO.read(new File("SistemaBiblioteca/files/portadas/1984.jpg"));
            Image resizedImage = originalImage.getScaledInstance(newWidth, newHeight, Image.SCALE_AREA_AVERAGING);
            
            //anade un color vacio
            prueba.setBackground(new Color(0,0,0,0));
            //quitamos el borde
            prueba.setBorderPainted(false);
            //la posicion del texto la pone en medio
            prueba.setHorizontalTextPosition(JButton.CENTER);
            //darle un color transparente
            prueba.setForeground(new Color(0,0,0,0));
            /*
             * Esta configuracion nos permite crear un boton y mandarlo a llamar con
             * el texto, por lo que no sera visible al publico pero si para 
             * el desarrollador
             */
            prueba.setIcon(new ImageIcon(resizedImage));
            
        }catch(Exception ex){
            ex.printStackTrace();
        }
    
        areaLibros.add(prueba);

        return areaLibros;
    }

}
