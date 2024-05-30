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
        System.out.println(e.getActionCommand());
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
        }
        

    }

    public void ventanaNuevoLibro(){
        
        formulario = new Ventana_FormularioNuevoLibro();
        formulario.asignarListeners(this);
        formulario.setLocationRelativeTo(visualMain);
        formulario.setVisible(true);
        

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

    public void registrosPrueba(){
        
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
