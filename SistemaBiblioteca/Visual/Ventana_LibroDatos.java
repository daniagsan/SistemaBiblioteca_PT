package SistemaBiblioteca.Visual;

import java.util.ResourceBundle;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextPane;
import javax.swing.text.BadLocationException;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;

import SistemaBiblioteca.modelos.LibroData;

public class Ventana_LibroDatos extends JFrame{

    private DisplayText displayStrings  = new DisplayText();
    private ResourceBundle rb = displayStrings.getRb();

    public Ventana_LibroDatos(LibroData libro){

        JPanel panelLibroDatos = new JPanel();
        panelLibroDatos.setLayout(new BoxLayout(panelLibroDatos, BoxLayout.Y_AXIS));
        
        //en vez de que sea el boton, que sea la direccion de la imagen
        //que la informaciion aparezca pegada con el boton  o la imagen del boton
        //de  preferencia la imagen
        libro.getBotonLibro().setEnabled(false);

        JTextPane textInfo = new JTextPane();
        textInfo.setContentType("text/plain");
        textInfo.setEditable(false);

        StyledDocument doc = textInfo.getStyledDocument();

        SimpleAttributeSet center = new SimpleAttributeSet();
        StyleConstants.setAlignment(center, StyleConstants.ALIGN_CENTER);
        StyleConstants.setFontFamily(center, "Arial");
        StyleConstants.setFontSize(center, 16);
        StyleConstants.setBold(center, true);

        doc.setParagraphAttributes(0, doc.getLength(), center, false);

        try {
            doc.insertString(doc.getLength(), rb.getString("title") + ": " + libro.getTitulo() + "\n", center);
            doc.insertString(doc.getLength(), rb.getString("autor") + ": " + libro.getAutor() + "\n", center);
            doc.insertString(doc.getLength(), rb.getString("year") + ": " + libro.getYear() + "\n", center);
            doc.insertString(doc.getLength(), rb.getString("sinopsis") + ": " + libro.getSinopsis() + "\n", center);
            doc.insertString(doc.getLength(), rb.getString("isbn") + ": " + libro.getIsbn() + "\n", center);
            doc.insertString(doc.getLength(), rb.getString("editorial") + ": " + libro.getEditorial() + "\n", center);
            doc.insertString(doc.getLength(), rb.getString("edition") + ": " + libro.getEdicion() + "\n", center);
        } catch (BadLocationException e) {
            e.printStackTrace();
        }

        panelLibroDatos.add(textInfo);
        panelLibroDatos.add(libro.getBotonLibro());


        add(panelLibroDatos);
    
        setSize(300, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setVisible(true);

    }

}
