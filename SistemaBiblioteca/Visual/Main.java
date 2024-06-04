package SistemaBiblioteca.Visual;

import java.util.TimeZone;

import com.formdev.flatlaf.FlatLightLaf;

import SistemaBiblioteca.Controladores.ControladorLogin;
import SistemaBiblioteca.modelos.UsuarioDAO;

public class Main {

	public static void main(String[] args) {

		FlatLightLaf.setup();
		Ventana ventanaPrincipal  = new Ventana();
		UsuarioDAO usuarioDao = new UsuarioDAO();
		VistaLogin vista = new VistaLogin();
		TimeZone.setDefault(TimeZone.getTimeZone("America/Hermosillo"));
		new ControladorLogin(vista, usuarioDao);  

	}
	
}
