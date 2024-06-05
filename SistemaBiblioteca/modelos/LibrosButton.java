package SistemaBiblioteca.modelos;

import javax.swing.JButton;

import com.fasterxml.jackson.annotation.JsonIgnore;

/*
Esta clase nos va a servir para los botones de los libros, 
es mas sencillo mostrarlos de esta manera ya que podemos manipular
el tamaño de los mismos mientras la imagen se adapta
*/

public class LibrosButton{
    @JsonIgnore
    private JButton botonLibro = new JButton();
    private String dirImage = "";

    public LibrosButton(){

    }

    public JButton getBotonLibro() {
        return botonLibro;
    }

    public void setBotonLibro(JButton botonLibro) {
        this.botonLibro = botonLibro;
    }

    public String getDirImage() {
        return dirImage;
    }

    public void setDirImage(String dirImage) {
        this.dirImage = dirImage;
    }
}
