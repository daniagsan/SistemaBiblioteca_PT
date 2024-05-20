package SistemaBiblioteca.Controladores;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import SistemaBiblioteca.Visual.VisualFormularioNuevoLibro;

public class NewBookController implements ActionListener{

    public  VisualFormularioNuevoLibro panelFormulario = new VisualFormularioNuevoLibro();

    public  NewBookController(VisualFormularioNuevoLibro panelFormulario){
        this.panelFormulario = panelFormulario;
        this.panelFormulario.addListener(this);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'actionPerformed'");
    }

}
