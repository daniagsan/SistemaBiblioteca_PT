package SistemaBiblioteca.modelos;

import javax.swing.JButton;

/*
Esta clase nos va a servir para los botones de los libros, 
es mas sencillo mostrarlos de esta manera ya que podemos manipular
el tamaño de los mismos mientras la imagen se adapta
*/

public class LibrosButton{

    private JButton botonLibro = new JButton();

    public LibrosButton(){

    }

    public JButton getBotonLibro() {
        return botonLibro;
    }

    public void setBotonLibro(JButton botonLibro) {
        this.botonLibro = botonLibro;
    }

}
