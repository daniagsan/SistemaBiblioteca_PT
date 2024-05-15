package SistemaBiblioteca.Visual;

import javax.swing.JFrame;

import SistemaBiblioteca.Controladores.MainControl;

    public class Ventana extends JFrame{

		DisplayText rb = new DisplayText();
		String language = "es_MX";

    public Ventana() {

		setVisible(true);
		
		int top = getInsets().top;
		int left = getInsets().left;
		int bottom = getInsets().bottom;
		int right = getInsets().right;
		
		setSize(854 + left + right, 480 + top + bottom);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(true);
		setTitle(rb.language(language).getString("mainWindowName"));
		setLocationRelativeTo(null);
		
		VisualMain mainPanel = new VisualMain();
		add(mainPanel);
		MainControl mainControl = new MainControl(mainPanel);


		validate();
		repaint();

        //el pack nos servira para que sea responsivo, recuerden usar el jpanel siempre
        //pack();
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	

}
