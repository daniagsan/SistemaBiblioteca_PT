package SistemaBiblioteca.Visual;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

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
import javax.swing.JTextField;
import javax.swing.SpringLayout;

import SistemaBiblioteca.modelos.LibroData;

public class VisualMain extends JPanel{

    //Ventana ventana = new Ventana();
    DisplayText displayStrings  = new DisplayText();
    //VentanaFormularioNuevoLibro formularioNuevoLibro = new VentanaFormularioNuevoLibro(this);
    ResourceBundle rb = displayStrings.getRb();

    JButton nuevoLibro = new JButton(rb.getString("newBookButton"));
    JButton buscar = new JButton(rb.getString("searchButton"));
    JButton cambiarVista = new JButton(rb.getString("updatePanelButton"));
    JButton librosPrueba = new JButton("Generar libros");
    
    ArrayList<JButton> bookButtons = new ArrayList<>();
    JPanel areaLibros = new JPanel(new FlowLayout(FlowLayout.LEFT));
    JTextField barraBusqueda  = new JTextField();


    public VisualMain(){

        setLayout(new BorderLayout(5,5));
		setBorder(BorderFactory.createEmptyBorder(5,5,5,5));
        

        add(panelBotonesWest(), BorderLayout.WEST);
        add(areaLibros, BorderLayout.CENTER);
        add(panelBotonesSouth(), BorderLayout.SOUTH);
        
    }

    public JPanel panelBotonesSouth() {
        JPanel inputSouthArea = new JPanel(new FlowLayout(FlowLayout.LEFT));

        cambiarVista.setHorizontalAlignment(JButton.CENTER);

        inputSouthArea.add(cambiarVista);
        inputSouthArea.add(librosPrueba);
    
        return inputSouthArea;
    }

    public JPanel panelBotonesWest() {
        JPanel inputArea = new JPanel();
        inputArea.setLayout(new BoxLayout(inputArea, BoxLayout.Y_AXIS));
        
        JPanel areaBotones = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JPanel searchArea = new JPanel(new SpringLayout());
    
        nuevoLibro.setHorizontalAlignment(JButton.CENTER);
        buscar.setHorizontalAlignment(JButton.CENTER);
        cambiarVista.setHorizontalAlignment(JButton.CENTER);

        areaBotones.add(nuevoLibro);
        areaBotones.add(buscar);
    
        barraBusqueda.setPreferredSize(areaBotones.getPreferredSize());
        searchArea.add(barraBusqueda);
    
        inputArea.add(areaBotones);
        inputArea.add(searchArea);
    
        return inputArea;
    }

    public JPanel informacionLibro(LibroData libro){
        JPanel infoPanel = new JPanel();
        infoPanel.setLayout(new BoxLayout(infoPanel, BoxLayout.Y_AXIS));

        JLabel datos[] = {new JLabel(libro.getTitulo()),
                          new JLabel(libro.getAutor()),
                          new JLabel(libro.getYear()),
                          new JLabel(libro.getSinopsis()),
                          new JLabel(libro.getIsbn()),
                          new JLabel(libro.getEditorial()),
                          new JLabel(libro.getEdicion())};

        infoPanel.add(datos[0]);
        /*
         * Toda la informacion del libro,  comenzando por el titulo, despues por la portada
         */


        return  infoPanel;
    }

    public void busquedaLibro(String libro){
        System.out.println("entra");
        int ctrl = 0;
        
        for(JButton b: bookButtons){
            if(b.getText().equals(libro)){
                areaLibros.removeAll();
                areaLibros.add(b);
                areaLibros.revalidate();
                areaLibros.repaint();
                break;
            }else if(ctrl == bookButtons.size()){
                //va checando todos y en algun momento lo compara
                JOptionPane.showMessageDialog(
                this, rb.getString("missingBookMessage") + ": " + libro, 
                rb.getString("winNameWarning"), JOptionPane.INFORMATION_MESSAGE);
                break;
            }
            ctrl++;
        }
    }

    public String getBarraBusqueda() {
        return barraBusqueda.getText();
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
        cambiarVista.addActionListener(listener);
        librosPrueba.addActionListener(listener);
    }




    public JButton creadorLibro(String titulo, String imageDir){

    JButton libro =  new JButton(titulo);
    int newWidth = 100;
    int newHeight = 150;
    Dimension defaultButtonSize = new Dimension(newWidth, newHeight);

    libro.setPreferredSize(defaultButtonSize);
 
        //Carga los botones de manera asincrona
        try {
            File imageFile = new File(imageDir);

            if (!imageFile.exists()) {
                throw new IOException("Image file not found: " + imageDir);
            }
                Image originalImage = ImageIO.read(imageFile);
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
            JOptionPane.showMessageDialog(this, rb.getString("imageLoadFailedMessage"), rb.getString("winNameWarning"), 2);
            //ex.printStackTrace();
        }

        
        bookButtons.add(libro);
        return libro;
    }
}
