package SistemaBiblioteca.Visual;

import java.awt.Color;

import javax.swing.JFrame;

import SistemaBiblioteca.Controladores.MainControl;

    public class Ventana extends JFrame{

    public Ventana() {
		
		setVisible(true);
		
		int top = getInsets().top;
		int left = getInsets().left;
		int bottom = getInsets().bottom;
		int right = getInsets().right;
		
		setSize(854 + left + right, 480 + top + bottom);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(true);
		setTitle("Biblioteca General");
		setLocationRelativeTo(null);
		
		VisualMain mainPanel = new VisualMain();
		add(mainPanel);
		MainControl mainControl = new MainControl(mainPanel);


		validate();
		repaint();

        //el pack nos servira para que sea responsivo, recuerden usar el jpanel siempre
        //pack();
	}

}
