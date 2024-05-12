package SistemaBiblioteca.Controladores;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import SistemaBiblioteca.Visual.VisualMain;

public class MainControl implements ActionListener{

    private VisualMain visualMain = new VisualMain();

    public MainControl(VisualMain visualMain){
        this.visualMain = visualMain;
        this.visualMain.addListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println(e.getActionCommand());
        
    }

}
