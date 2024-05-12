package SistemaBiblioteca.modelos;

import java.io.File;

import javax.swing.ImageIcon;
import javax.swing.JButton;

/*
Esta clase nos va a servir para los botones de los libros, 
es mas sencillo mostrarlos de esta manera ya que podemos manipular
el tamaño de los mismos mientras la imagen se adapta
*/

public class LibrosButton{

    private ImageIcon portada;
    private File filePortada;
    private JButton botonLibro = new JButton();

    public LibrosButton(){

    }

    public ImageIcon getPortada() {
        return portada;
    }

    public void setPortada(ImageIcon portada) {
        this.portada = portada;
    }

    public JButton getBotonLibro() {
        return botonLibro;
    }

    public void setBotonLibro(JButton botonLibro) {
        this.botonLibro = botonLibro;
    }

    public File getFilePortada() {
        return filePortada;
    }

    public void setFilePortada(File filePortada) {
        this.filePortada = filePortada;
    }

}
