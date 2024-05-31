package SistemaBiblioteca.Controladores;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

import SistemaBiblioteca.Visual.*;
import SistemaBiblioteca.modelos.*;

public class MainControl implements ActionListener{

    public DisplayText displayStrings  = new DisplayText();
    public ResourceBundle rb = displayStrings.getRb();
    public VisualMain visualMain = new VisualMain();
    public Ventana_FormularioNuevoLibro formulario;
    public Ventana_LibroDatos datosLibro;
    public LibroData libro = new LibroData();
    public JFileChooser imageDir;
    public File archivoImagen = new File("");
    public String dirimage = "";
    public ArrayList<LibroData> librosUsuario = new ArrayList<>();
    public String buttonAcess[] = {rb.getString("newBookButton"), 
                                    rb.getString("searchButton"),
                                    rb.getString("newBookRegisterButton"), 
                                    rb.getString("newBookCleanDataButton"),
                                    rb.getString("newBookAddCoverButton"),
                                    rb.getString("updatePanelButton")};

    public MainControl(VisualMain visualMain){
        this.visualMain = visualMain;
        this.visualMain.addListener(this);
        librosUsuario = new ArrayList<LibroData>();
        imageDir = new JFileChooser();
		imageDir.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
		imageDir.addChoosableFileFilter(new FileNameExtensionFilter(rb.getString("fileType"), "jpg", "jpeg", "png"));
        botonesLibros();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        //en este apartado, donde iran los listeners, el proceso
        //por default que sea darle click a los libros
        //commitest
        /*Colocamos esto para que el nombre de los botones
        se  tome  del archivo properties
        */
        if(e.getActionCommand().equals(buttonAcess[0])){
            ventanaNuevoLibro();
        }else if(e.getActionCommand().equals(buttonAcess[1])){
            visualMain.busquedaLibro(visualMain.getBarraBusqueda());
        }else if(e.getActionCommand().equals(buttonAcess[2])){
            registrarLibro();
        }else if(e.getActionCommand().equals(buttonAcess[3])){
            formulario.limpiarCampos();
        }else if(e.getActionCommand().equals(buttonAcess[4])){
            asignarPortada();
        }else if(e.getActionCommand().equals(buttonAcess[5])){
            visualMain.updateBookPanel();
        }else if(e.getActionCommand().equals("Generar libros")){
            librosPrueba();
        }else{
            //aqui es si presionamos el boton de un libro
            //buscar el nombre  del libro a  traves del  action command mandanndo el
            //dato a una funcion
            ventanaInfoLibro(e.getActionCommand());
            System.out.println();
        }
        

    }

    public void librosPrueba() {
        String[] titulos = {"El Gran Gatsby", "Cien Años de Soledad", "Don Quijote", "Matar a un Ruiseñor", "1984"};
        String[] autores = {"F. Scott Fitzgerald", "Gabriel García Márquez", "Miguel de Cervantes", "Harper Lee", "George Orwell"};
        String[] years = {"1925", "1967", "1605", "1960", "1949"};
        String[] sinopsis = {
            "Una novela sobre la esperanza y la desilusión.",
            "Una saga épica de la familia Buendía.",
            "Las aventuras del ingenioso hidalgo Don Quijote de la Mancha.",
            "Un drama sobre la justicia y el racismo en el sur de los Estados Unidos.",
            "Una visión distópica del futuro."
        };
        String[] editoriales = {"Scribner", "Editorial Sudamericana", "Francisco de Robles", "J.B. Lippincott & Co.", "Secker & Warburg"};
        String[] ediciones = {"1ª", "1ª", "1ª", "1ª", "1ª"};

        for (int i = 0; i < 5; i++) {
            LibroData libro = new LibroData();
            libro.setTitulo(titulos[i]);
            libro.setAutor(autores[i]);
            libro.setYear(years[i]);
            libro.setSinopsis(sinopsis[i]);
            libro.setEditorial(editoriales[i]);
            libro.setEdicion(ediciones[i]);
            libro.setBotonLibro(visualMain.creadorLibro(titulos[i], "")); // No image path provided
            libro.getBotonLibro().addActionListener(this);
            librosUsuario.add(libro);
        }

        visualMain.updateBookPanel();
    }

    public void ventanaNuevoLibro(){
        
        formulario = new Ventana_FormularioNuevoLibro();
        formulario.asignarListeners(this);
        formulario.setLocationRelativeTo(visualMain);
        formulario.setVisible(true);
    
    }

    public void ventanaInfoLibro(String titulo){

        for(LibroData l: librosUsuario){
            if(titulo.equals(l.getTitulo())){
                datosLibro = new Ventana_LibroDatos(l);
                datosLibro.setLocationRelativeTo(visualMain);
                datosLibro.setVisible(true);
            }
        }
        

    }

    public void registrarLibro(){
        
        if(!validarCampos()){

            JOptionPane.showMessageDialog(
                formulario, rb.getString("emptyFieldsMessage"), 
                rb.getString("winNameWarning"), 0);
        
        }else{
            libro = new LibroData();
            libro.setTitulo(formulario.getTitulo().getText());
            libro.setAutor(formulario.getAutor().getText());
            libro.setYear(formulario.getYear().getText());
            libro.setSinopsis(formulario.getSinopsis().getText());
            libro.setEditorial(formulario.getEditorial().getText());
            libro.setEdicion(formulario.getEdicion().getText());
            libro.setBotonLibro(visualMain.creadorLibro(formulario.getTitulo().getText(), dirimage));
            libro.getBotonLibro().addActionListener(this);
            librosUsuario.add(libro); 
            formulario.dispose();
            visualMain.updateBookPanel();
            
        }
    }

    public void asignarPortada() {

        int respuesta = imageDir.showOpenDialog(formulario);

        if (respuesta == JFileChooser.APPROVE_OPTION) {

            archivoImagen = imageDir.getSelectedFile();
            dirimage = archivoImagen.getAbsolutePath();
            formulario.portadaAsignada();


        }
    }

    public void botonesLibros(){
        for(LibroData l: librosUsuario){
            l.getBotonLibro().addActionListener(this);
        }
    }

    public boolean validarCampos() {
		if(formulario.getTitulo().getText().isEmpty() ||
           formulario.getAutor().getText().isEmpty() ||
           formulario.getYear().getText().isEmpty() ||
           formulario.getISBN().getText().isEmpty() ||
           formulario.getSinopsis().getText().isEmpty() ||
           formulario.getEditorial().getText().isEmpty() ||
           formulario.getEdicion().getText().isEmpty()) {

			return false;
		}
		
		return true;
	}

}
