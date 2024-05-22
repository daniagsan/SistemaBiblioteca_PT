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
import java.awt.Image;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

public class VisualMain extends JPanel{

    //Ventana ventana = new Ventana();
    DisplayText displayStrings  = new DisplayText();
    //VentanaFormularioNuevoLibro formularioNuevoLibro = new VentanaFormularioNuevoLibro(this);
    ResourceBundle rb = displayStrings.getRb();

    JButton nuevoLibro = new JButton(rb.getString("newBookButton"));
    JButton buscar = new JButton(rb.getString("searchButton"));
    
    ArrayList<JButton> bookButtons = new ArrayList<>();
    JPanel areaLibros = new JPanel(new FlowLayout(FlowLayout.LEFT));
    JScrollPane scrollPane = new JScrollPane(areaLibros);
    JTextField busqueda  = new JTextField();


    public VisualMain(){

        setLayout(new BorderLayout(5,5));
		setBorder(BorderFactory.createEmptyBorder(5,5,5,5));
        

        add(panelBotones(), BorderLayout.NORTH);
        add(areaLibros, BorderLayout.CENTER);
        
    }


    public JPanel panelBotones(){

        JPanel areaBotones = new JPanel(new FlowLayout(FlowLayout.LEFT));
        areaBotones.setBackground(Color.black);
        

        nuevoLibro.setFont(new Font("Calibri",Font.PLAIN,18));
        nuevoLibro.setHorizontalAlignment(JButton.CENTER);
        buscar.setFont(new Font("Calibri",Font.PLAIN,18));
        buscar.setHorizontalAlignment(JButton.CENTER);
        busqueda.setFont(new Font("Calibri",Font.PLAIN,18));


        areaBotones.add(nuevoLibro);
        areaBotones.add(buscar);
        areaBotones.add(busqueda);
        

        return areaBotones;
    }

    public void updateBookPanel() {
        areaLibros.removeAll();
        for (JButton b : bookButtons) {
            areaLibros.add(b);
        }
        
        areaLibros.revalidate();
        areaLibros.repaint();

        
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
        } catch (IOException ex) {
            //Apartado de aviso
            //JOptionPane.showMessageDialog(this, ex, "Falla al cargar archivo de imagen", 0);
            ex.printStackTrace();
        }

        
        bookButtons.add(libro);
        updateBookPanel();
        return libro;
    }


}
