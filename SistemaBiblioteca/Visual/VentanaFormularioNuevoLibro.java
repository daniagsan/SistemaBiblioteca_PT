package SistemaBiblioteca.Visual;

import java.util.ResourceBundle;

import javax.swing.JFrame;
import javax.swing.JPanel;

import SistemaBiblioteca.Controladores.NewBookController;

public class VentanaFormularioNuevoLibro extends  JFrame{

    DisplayText displayStrings  = new DisplayText();
    ResourceBundle rb = displayStrings.getRb();

    public VentanaFormularioNuevoLibro(JPanel mainWindow){
        setVisible(true);
		
		int top = getInsets().top;
		int left = getInsets().left;
		int bottom = getInsets().bottom;
		int right = getInsets().right;
		
		setSize(480 + left + right, 240 + top + bottom);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(true);
		setTitle(rb.getString("newBookFormWindowName"));
		setLocationRelativeTo(mainWindow);
	
		VisualFormularioNuevoLibro formulario = new VisualFormularioNuevoLibro();
		add(formulario);

		NewBookController newBookControl = new NewBookController(formulario);


		validate();
		repaint();
    }

}
