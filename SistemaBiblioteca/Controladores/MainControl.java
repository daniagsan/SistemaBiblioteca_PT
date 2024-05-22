package SistemaBiblioteca.Controladores;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.ResourceBundle;

import SistemaBiblioteca.Visual.*;
import SistemaBiblioteca.modelos.*;

public class MainControl implements ActionListener{

    public DisplayText displayStrings  = new DisplayText();
    public ResourceBundle rb = displayStrings.getRb();
    public VisualMain visualMain = new VisualMain();
    public Ventana_FormularioNuevoLibro formulario;
    
    public ArrayList<LibroData> librosUsuario = new ArrayList<>();

    public MainControl(VisualMain visualMain){
        this.visualMain = visualMain;
        this.visualMain.addListener(this);
        librosUsuario = new ArrayList<LibroData>();
        //panelLibrosListeners();
        //nuevoLibro();
        botonesLibros();
        
        
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        //en este apartado, donde iran los listeners, el proceso
        //por default que sea darle click a los libros
        System.out.println(e.getActionCommand());
        switch(e.getActionCommand()){

            case "+ Nuevo libro":
            /* 
            formulario = new Ventana_FormularioNuevoLibro();
            formulario.asignarListeners(this);
            formulario.setLocationRelativeTo(visualMain);
            formulario.setVisible(true);
            */
            nuevoLibro();
            break;
            
        }
        
    }

    public void nuevoLibro(){

        //le mandamos el visual main para colocarlo en el setRelativeTo
        //VentanaFormularioNuevoLibro formulario = new VentanaFormularioNuevoLibro(visualMain);
        
         
        LibroData libro = new LibroData();
        libro.setAutor("asdadsas");
        libro.setDirFilePortada("SistemaBiblioteca\\files\\portadas\\1984.jpg");
        libro.setBotonLibro(visualMain.creadorLibro(libro.getAutor(), libro.getDirFilePortada()));
        librosUsuario.add(libro);
        //visualMain.actualizarLibros();
        

    }

    public void botonesLibros(){
        for(LibroData l: librosUsuario){
            l.getBotonLibro().addActionListener(this);
        }
    }

}
