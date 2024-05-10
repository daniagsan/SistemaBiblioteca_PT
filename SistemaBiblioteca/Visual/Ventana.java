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
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(true);
		setTitle("Biblioteca General");
		setLocationRelativeTo(null);
		
		validate();
		repaint();

        //el pack nos servira para que sea responsivo, recuerden usar el jpanel siempre
        //pack();
	}

}
