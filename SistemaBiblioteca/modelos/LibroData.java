package SistemaBiblioteca.modelos;

/* 
Propiedades de un libro: 
-TITULO
-AUTOR
-PORTADA
-SINOPSIS
-ISBN: El isbn va a ser nuestro numero de control, para verificar, podemos tener
distintas versiones del libro, de esta manera lograremos relacionar todos los libros
a través de un algoritmo
-EDITORIAL
-AÑO
*/ 


public class LibroData extends LibrosButton{

    private String titulo;
    private String autor;
    private String year;
    private String isbn;
    private String sinopsis;
    private String editorial;
    private String edicion;

    public LibroData(){
        super();
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getSinopsis() {
        return sinopsis;
    }

    public void setSinopsis(String sinopsis) {
        this.sinopsis = sinopsis;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getEditorial() {
        return editorial;
    }

    public void setEditorial(String ediorial) {
        this.editorial = ediorial;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getEdicion() {
        return edicion;
    }

    public void setEdicion(String edicion) {
        this.edicion = edicion;
    }

    
    

}
