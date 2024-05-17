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
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class VisualMain extends JPanel{

    //Ventana ventana = new Ventana();
    DisplayText displayStrings  = new DisplayText();
    ArrayList<JButton> bookButtons = new ArrayList<>();
    ArrayList<JButton> botones = new ArrayList<>();

    ResourceBundle rb = displayStrings.getRb();
    JButton nuevoLibro = new JButton(rb.getString("newBookButton"));
    JButton buscar = new JButton(rb.getString("searchButton"));


    public VisualMain(){

        setLayout(new BorderLayout(5,5));
		setBorder(BorderFactory.createEmptyBorder(5,5,5,5));

        panelLibros();

        
    }

    public void panelLibros(){

        //ejemplos();
        JPanel mainPanel = new JPanel(new BorderLayout());
        JPanel areaLibros = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JPanel areaBotones = new JPanel(new FlowLayout(FlowLayout.RIGHT));

        for(JButton b: bookButtons){
            areaLibros.add(b);
        }

        //personalizacion botones

        areaBotones.add(buscar);
        areaBotones.add(nuevoLibro);

        mainPanel.add(areaBotones, BorderLayout.NORTH);
        mainPanel.add(areaLibros, BorderLayout.CENTER);

        add(mainPanel, BorderLayout.CENTER);
    }

    public void addListener(ActionListener listener) {

        for(JButton b: bookButtons){
            b.addActionListener(listener);
        }

        nuevoLibro.addActionListener(listener);
        buscar.addActionListener(listener);
    }


    public JButton creadorLibro(String titulo, String imageDir){
    JButton libro =  new JButton(titulo);
    libro.setHorizontalTextPosition(JButton.CENTER);
    int newWidth = 100;
    int newHeight = 150;
    Dimension defaultButtonSize = new Dimension(newWidth, newHeight);

        //Carga los botones de manera asincrona
        new Thread(() -> {
         try {
                Image originalImage = ImageIO.read(new File(imageDir));
                Image resizedImage = originalImage.getScaledInstance(newWidth, newHeight, Image.SCALE_AREA_AVERAGING);
                ImageIcon icon = new ImageIcon(resizedImage);

             //Aqui se actualizan los botones
             SwingUtilities.invokeLater(() -> {
             libro.setIcon(icon);
             libro.revalidate();
             libro.repaint();
            });
        } catch (IOException ex) {
            //Apartado de aviso
            //JOptionPane.showMessageDialog(this, ex, "Falla al cargar archivo de imagen", 0);
            ex.printStackTrace();
        }
        }).start();

        libro.setPreferredSize(defaultButtonSize);
        libro.setBackground(Color.white);
        libro.setBorderPainted(true);
        libro.setForeground(Color.black);

        bookButtons.add(libro);
        return libro;
    }

}
