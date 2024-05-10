package SistemaBiblioteca.Visual;

import javax.swing.JFrame;

    public class Ventana extends JFrame{

    public Ventana() {
		
		setVisible(true);
		
		int top = getInsets().top;
		int left = getInsets().left;
		int bottom = getInsets().bottom;
		int right = getInsets().right;
		
		setSize(1280 + left + right, 720 + top + bottom);
        //no quitar el  close operation porque no se abre la ventana
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(true);
		setTitle("Biblioteca General");
		setLocationRelativeTo(null);
		
		validate();
		repaint();

        //el pack nos servira para que sea responsivo, recuerden usar el jpanel siempre
        //pack();
	}

}
