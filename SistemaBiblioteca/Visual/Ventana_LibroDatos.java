package SistemaBiblioteca.Visual;

import java.awt.GridLayout;
import java.util.ResourceBundle;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import SistemaBiblioteca.modelos.LibroData;

public class Ventana_LibroDatos extends JFrame{

    private DisplayText displayStrings  = new DisplayText();
    private ResourceBundle rb = displayStrings.getRb();

    public Ventana_LibroDatos(LibroData libro){

        // Add the button to the frame
        JPanel panelLibroDatos = new JPanel(new GridLayout(2,0,0,5));
        //panelLibroDatos.setLayout(new BoxLayout(panelLibroDatos, BoxLayout.Y_AXIS));
        //en vez de que sea el boton, que sea la direccion de la imagen

        libro.getBotonLibro().setEnabled(false);
        JTextArea textInfo = new JTextArea();

        textInfo.setText(rb.getString("title") + ": " + libro.getTitulo() + "\n" +
                          rb.getString("autor") + ": " + libro.getAutor() + "\n" +
                          rb.getString("sinopsis") + ": " + libro.getSinopsis() + "\n" +
                          rb.getString("isbn") + ": " + libro.getIsbn() + "\n" +
                          rb.getString("editorial") + ": " + libro.getEditorial() + "\n" +
                          rb.getString("edition") + ": " + libro.getEdicion());

        textInfo.setLineWrap(true);
        textInfo.setEditable(false);
        /* 
        JTextPane sinopsisInfo = new JTextPane();

        sinopsisInfo.setContentType("text/plain");
        sinopsisInfo.setEditable(false);

        // StyledDocument to center text
        StyledDocument doc = sinopsisInfo.getStyledDocument();

        SimpleAttributeSet center = new SimpleAttributeSet();
        StyleConstants.setAlignment(center, StyleConstants.ALIGN_CENTER);
        StyleConstants.setFontFamily(center, "Arial");
        StyleConstants.setFontSize(center, 16);
        StyleConstants.setBold(center, true);

        doc.setParagraphAttributes(0, doc.getLength(), center, false);

        try {
            doc.insertString(0, rb.getString("sinopsis") + ": " + libro.getSinopsis(), null);
        } catch (BadLocationException e) {
            e.printStackTrace();
        }
        */
        panelLibroDatos.add(libro.getBotonLibro());
        panelLibroDatos.add(textInfo);

        add(panelLibroDatos);
    
        setSize(200, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setVisible(true);
        //pack();

    }

}