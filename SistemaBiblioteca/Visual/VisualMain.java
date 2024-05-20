package SistemaBiblioteca.Visual;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

public class VisualMain extends JPanel{

    //Ventana ventana = new Ventana();
    DisplayText displayStrings  = new DisplayText();
    //VentanaFormularioNuevoLibro formularioNuevoLibro = new VentanaFormularioNuevoLibro(this);
    ResourceBundle rb = displayStrings.getRb();

    JButton nuevoLibro = new JButton(rb.getString("newBookButton"));
    JButton buscar = new JButton(rb.getString("searchButton"));

    ArrayList<JButton> bookButtons = new ArrayList<>();
    ArrayList<JButton> botones = new ArrayList<>();
    

    JTextField busqueda  = new JTextField(30);


    public VisualMain(){

        setLayout(new BorderLayout(5,5));
		setBorder(BorderFactory.createEmptyBorder(5,5,5,5));

        panelLibros();

        
    }

    public JPanel formularioNuevoLibro(){
        JPanel formulario = new JPanel(new GridLayout(0,1));

        JTextField autorZone = new JTextField();
        formulario.add(autorZone);


        return formulario;
    }

    public void panelLibros(){

        setBackground(Color.blue);
        //ejemplos();
        JPanel mainPanel = new JPanel(new BorderLayout());
        JPanel areaLibros = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JPanel areaBotones = new JPanel(new FlowLayout(FlowLayout.LEFT));


        areaBotones.setBackground(Color.black);
        areaLibros.setBackground(Color.red);

        for(JButton b: bookButtons){
            areaLibros.add(b);
        }

        //personalizacion botones
        //busqueda.setBorder(BorderFactory.createLineBorder(Color.black));
        nuevoLibro.setFont(new Font("Calibri",Font.PLAIN,18));
        nuevoLibro.setHorizontalAlignment(JButton.CENTER);
        buscar.setFont(new Font("Calibri",Font.PLAIN,18));
        buscar.setHorizontalAlignment(JButton.CENTER);
        busqueda.setFont(new Font("Calibri",Font.PLAIN,18));

        
        areaBotones.add(nuevoLibro);
        areaBotones.add(buscar);
        areaBotones.add(busqueda);
        
        
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
    int newWidth = 100;
    int newHeight = 150;
    Dimension defaultButtonSize = new Dimension(newWidth, newHeight);

    libro.setPreferredSize(defaultButtonSize);
 
        //Carga los botones de manera asincrona
        new Thread(() -> {
         try {
                Image originalImage = ImageIO.read(new File(imageDir));
                Image resizedImage = originalImage.getScaledInstance(newWidth, newHeight, Image.SCALE_AREA_AVERAGING);
                ImageIcon icon = new ImageIcon(resizedImage);
                
                libro.setBackground(new Color(0,0,0));
                libro.setBorderPainted(false);
                libro.setHorizontalTextPosition(JButton.CENTER);
                libro.setForeground(new Color(0,0,0,0));
                libro.setIcon(icon);
                

             //Aqui se actualizan los botones
             SwingUtilities.invokeLater(() -> {
    
            libro.revalidate();
            libro.repaint();
            });
        } catch (IOException ex) {
            //Apartado de aviso
            //JOptionPane.showMessageDialog(this, ex, "Falla al cargar archivo de imagen", 0);
            ex.printStackTrace();
        }
        }).start();

        bookButtons.add(libro);

        return libro;
    }


}
