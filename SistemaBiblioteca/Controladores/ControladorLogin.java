package SistemaBiblioteca.Controladores;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Timestamp;
import java.time.format.DateTimeFormatter;

import SistemaBiblioteca.Visual.VistaLogin;
import SistemaBiblioteca.modelos.UsuarioDAO;


public class ControladorLogin {

	private VistaLogin vista;
	private UsuarioDAO usuarioDao;
	
	public ControladorLogin(VistaLogin vista, UsuarioDAO usuarioDao) {
		this.vista = vista;
		this.usuarioDao = usuarioDao;
		
		vista.addLoginListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				autenticar();
			}
		});
	}
	
	private void autenticar() {
		String nombreUsuario = vista.getNombreUsuario();
		String contrasena = vista.getContrasena();
		
		if(usuarioDao.autenticarUsuario(nombreUsuario, contrasena)) {
			System.out.println("Se inició sesión");
			Timestamp fechaHora = usuarioDao.verUltimoLogin(nombreUsuario); 
			vista.setStatusMessage("<html>Login exitoso! Último inicio de sesión: <br> " + 
					fechaHora.toLocalDateTime().format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss")) + "</html>");
			vista.limpiarCampos();
			//Abrir ventana
			//vista.dispose();
		}else {
			vista.setStatusMessage("Usuario o contraseña incorrectos");
		}
	}
	
	
}
