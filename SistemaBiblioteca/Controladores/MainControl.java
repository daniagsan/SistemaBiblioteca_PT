package SistemaBiblioteca.Controladores;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import SistemaBiblioteca.Visual.*;
import SistemaBiblioteca.modelos.*;

public class MainControl implements ActionListener{

    public VisualMain visualMain = new VisualMain();
    public ArrayList<LibroData> librosUsuario = new ArrayList<>();

    public MainControl(VisualMain visualMain){
        this.visualMain = visualMain;
        this.visualMain.addListener(this);
        librosUsuario = new ArrayList<LibroData>();
        //panelLibrosListeners();
        ingresarDato();
        botonesLibros();
        
        
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        //en este apartado, donde iran los listeners, el proceso
        //por default que sea darle click a los libros
        System.out.println(e.getActionCommand());
        switch(e.getActionCommand()){
            
        }
        
    }

    public void ingresarDato(){

        LibroData libro = new LibroData();
        libro.setAutor("asdadsas");
        libro.setDirFilePortada("");
        libro.setBotonLibro(visualMain.creadorLibro(libro.getAutor(), libro.getDirFilePortada()));
        librosUsuario.add(libro);
        visualMain.panelLibros();

    }

    public void botonesLibros(){
        for(LibroData l: librosUsuario){
            l.getBotonLibro().addActionListener(this);
        }
    }

}
