package SistemaBiblioteca.Visual;

import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class VistaLogin extends JFrame{
	
	private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton loginButton;
    private JLabel statusLabel;
    
    public VistaLogin() {
        setTitle("Login");
        setSize(300, 220);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);

        JLabel usernameLabel = new JLabel("Usuario:");
        usernameLabel.setBounds(20, 20, 80, 25);
        add(usernameLabel);

        usernameField = new JTextField("");
        usernameField.setBounds(100, 20, 165, 25);
        add(usernameField);

        JLabel passwordLabel = new JLabel("Contraseña:");
        passwordLabel.setBounds(20, 60, 80, 25);
        add(passwordLabel);

        passwordField = new JPasswordField("");
        passwordField.setBounds(100, 60, 165, 25);
        add(passwordField);

        loginButton = new JButton("Login");
        loginButton.setBounds(100, 100, 80, 25);
        add(loginButton);

        statusLabel = new JLabel("");
        statusLabel.setBounds(20, 140, 250, 25);
        add(statusLabel);
        
        setVisible(true);

    }
    
    public String getNombreUsuario() {
        return usernameField.getText();
    }

    public String getContrasena() {
        return new String(passwordField.getPassword());
    }
    
    public void addLoginListener(ActionListener listener) {
        loginButton.addActionListener(listener);
    }
    
    public void setStatusMessage(String message) {
        statusLabel.setText(message);
    }
    
    public void limpiarCampos() {
    	passwordField.setText("");
    	usernameField.setText("");
    }

}
