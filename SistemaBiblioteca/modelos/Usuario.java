package SistemaBiblioteca.modelos;



import java.sql.Timestamp;

public class Usuario {

	private int id;
	private String nombreUsuario;
    private String contrasena;
    private Timestamp ultimoLogin;
    
    public Usuario(String nombreUsuario, String contrasena) {
        this.nombreUsuario = nombreUsuario;
        this.contrasena = contrasena;
    }
    
    public Usuario(int id, String nombreUsuario, String contrasena) {
    	this.id = id;
        this.nombreUsuario = nombreUsuario;
        this.contrasena = contrasena;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Timestamp getUltimoLogin() {
		return ultimoLogin;
	}

	public void setUltimoLogin(Timestamp ultimoLogin) {
		this.ultimoLogin = ultimoLogin;
	}
    
    
	
}

