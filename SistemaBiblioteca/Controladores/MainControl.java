package SistemaBiblioteca.Controladores;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import SistemaBiblioteca.Visual.*;
import SistemaBiblioteca.modelos.*;

public class MainControl implements ActionListener{

    private ArrayList<LibroData> librosUsuario = new ArrayList<>();

    private VisualMain visualMain = new VisualMain();

    public MainControl(VisualMain visualMain){
        this.visualMain = visualMain;
        this.visualMain.addListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println(e.getActionCommand());
        
    }

    private void createConection(){
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException ex) {
			Logger.getLogger(SistemaBiblioteca.Controladores.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

}
