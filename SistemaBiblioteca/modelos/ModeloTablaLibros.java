package SistemaBiblioteca.modelos;

import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

public class ModeloTablaLibros extends AbstractTableModel {

    private static final long serialVersionUID = 1L;
    private ArrayList<LibroData> libros;
    private String[] columnas = {"ISBN", "Título", "Autor", "Año", "Editorial", "Edición"};

    public ModeloTablaLibros() {
        this.libros = new ArrayList<>();
    }

    @Override
    public int getRowCount() {
        return libros.size();
    }

    @Override
    public int getColumnCount() {
        return columnas.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        LibroData libro = libros.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return libro.getIsbn();
            case 1:
                return libro.getTitulo();
            case 2:
                return libro.getAutor();
            case 3:
                return libro.getYear();
            case 4:
                return libro.getEditorial();
            case 5:
                return libro.getEdicion();
            default:
                return null;
        }
    }

    @Override
    public String getColumnName(int column) {
        return columnas[column];
    }

    public void addLibro(LibroData libro) {
        libros.add(libro);
        fireTableRowsInserted(libros.size() - 1, libros.size() - 1);
    }

    public void clearAllLibros() {
        libros.clear();
        fireTableDataChanged();
    }

    public LibroData getLibroAt(int rowIndex) {
        return libros.get(rowIndex);
    }
}
