package SistemaBiblioteca.Controladores;

import java.awt.Desktop;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.filechooser.FileNameExtensionFilter;

import com.itextpdf.io.font.constants.StandardFonts;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.colors.DeviceGray;
import com.itextpdf.kernel.colors.DeviceRgb;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.borders.Border;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Image;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.properties.TextAlignment;
import com.itextpdf.layout.properties.UnitValue;

import SistemaBiblioteca.Visual.*;
import SistemaBiblioteca.modelos.*;

public class MainControl implements ActionListener, WindowListener{

    public DisplayText displayStrings  = new DisplayText();
    public ResourceBundle rb = displayStrings.getRb();
    public VisualMain visualMain = new VisualMain();
    public Ventana_FormularioNuevoLibro formulario;
    public Ventana_LibroDatos datosLibro;
    public LibroData libro = new LibroData();
    public JFileChooser imageDir;
    public File archivoImagen = new File("");
    public String dirimage = "";
    public ArrayList<LibroData> librosUsuario = new ArrayList<>();
    public String buttonAcess[] = {rb.getString("newBookButton"), 
                                    rb.getString("searchButton"),
                                    rb.getString("newBookRegisterButton"), 
                                    rb.getString("newBookCleanDataButton"),
                                    rb.getString("newBookAddCoverButton"),
                                    rb.getString("updatePanelButton")};

    JButton auxButton;

    public MainControl(VisualMain visualMain){
        this.visualMain = visualMain;
        this.visualMain.addListener(this);
        librosUsuario = new ArrayList<LibroData>();
        imageDir = new JFileChooser();
		imageDir.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
		imageDir.addChoosableFileFilter(new FileNameExtensionFilter(rb.getString("fileType"), "jpg", "jpeg", "png"));
        botonesLibros();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        //en este apartado, donde iran los listeners, el proceso
        //por default que sea darle click a los libros
        //commitest
        /*Colocamos esto para que el nombre de los botones
        se  tome  del archivo properties
        */
        if(e.getActionCommand().equals(buttonAcess[0])){
            ventanaNuevoLibro();
        }else if(e.getActionCommand().equals(buttonAcess[1])){
            visualMain.busquedaLibro(visualMain.getBarraBusqueda());
        }else if(e.getActionCommand().equals(buttonAcess[2])){
            registrarLibro();
        }else if(e.getActionCommand().equals(buttonAcess[3])){
            formulario.limpiarCampos();
        }else if(e.getActionCommand().equals(buttonAcess[4])){
            asignarPortada();
        }else if(e.getActionCommand().equals(buttonAcess[5])){
            visualMain.updateBookPanel();
        }else if(e.getActionCommand().equals("Generar libros")){
            librosPrueba();
        }else if(e.getActionCommand().equals("Generar Pdf")){
            generarPDF();    
        }else{
            //aqui es si presionamos el boton de un libro
            //buscar el nombre  del libro a  traves del  action command mandanndo el
            //dato a una funcion
            ventanaInfoLibro(e.getActionCommand());
            System.out.println();
        }
        

    }

    public void librosPrueba() {
        String[] titulos = {"El Gran Gatsby", "Cien Años de Soledad", "Don Quijote", "Matar a un Ruiseñor", "1984"};
        String[] autores = {"F. Scott Fitzgerald", "Gabriel García Márquez", "Miguel de Cervantes", "Harper Lee", "George Orwell"};
        String[] years = {"1925", "1967", "1605", "1960", "1949"};
        String[] sinopsis = {
            "Una novela sobre la esperanza y la desilusión.",
            "Una saga épica de la familia Buendía.",
            "Las aventuras del ingenioso hidalgo Don Quijote de la Mancha.",
            "Un drama sobre la justicia y el racismo en el sur de los Estados Unidos.",
            "Una visión distópica del futuro."
        };
        String[] editoriales = {"Scribner", "Editorial Sudamericana", "Francisco de Robles", "J.B. Lippincott & Co.", "Secker & Warburg"};
        String[] ediciones = {"1ª", "1ª", "1ª", "1ª", "1ª"};
        String[] isbns = {"9780743273565", "9780307474728", "9788491050274", "9780061120084", "9780451524935"};
    
        for (int i = 0; i < 5; i++) {
            LibroData libro = new LibroData();
            libro.setTitulo(titulos[i]);
            libro.setAutor(autores[i]);
            libro.setYear(years[i]);
            libro.setSinopsis(sinopsis[i]);
            libro.setEditorial(editoriales[i]);
            libro.setEdicion(ediciones[i]);
            libro.setIsbn(isbns[i]);
            libro.setBotonLibro(visualMain.creadorLibro(titulos[i], "")); // No image path provided
            libro.getBotonLibro().addActionListener(this);
            librosUsuario.add(libro);
        }
    
        visualMain.updateBookPanel();
    }
    

    public void ventanaNuevoLibro(){
        
        formulario = new Ventana_FormularioNuevoLibro();
        formulario.asignarListeners(this);
        formulario.setLocationRelativeTo(visualMain);
        formulario.setVisible(true);
    
    }

    public void ventanaInfoLibro(String titulo){

        for(LibroData l: librosUsuario){
            if(titulo.equals(l.getTitulo())){
                datosLibro = new Ventana_LibroDatos(l);
                datosLibro.addWindowListener(this); 
                datosLibro.setLocationRelativeTo(visualMain);
                datosLibro.setVisible(true);
                auxButton = l.getBotonLibro();
            }
        }
    }

    public void registrarLibro(){
        
        if(!validarCampos()){

            JOptionPane.showMessageDialog(
                formulario, rb.getString("emptyFieldsMessage"), 
                rb.getString("winNameWarning"), 0);
        
        }else{
            libro = new LibroData();
            libro.setTitulo(formulario.getTitulo().getText());
            libro.setAutor(formulario.getAutor().getText());
            libro.setYear(formulario.getYear().getText());
            libro.setIsbn(formulario.getISBN().getText());
            libro.setSinopsis(formulario.getSinopsis().getText());
            libro.setEditorial(formulario.getEditorial().getText());
            libro.setEdicion(formulario.getEdicion().getText());
            libro.setBotonLibro(visualMain.creadorLibro(formulario.getTitulo().getText(), dirimage));
            libro.getBotonLibro().addActionListener(this);
            librosUsuario.add(libro); 
            formulario.dispose();
            visualMain.updateBookPanel();
        }
    }

    public void asignarPortada() {

        int respuesta = imageDir.showOpenDialog(formulario);

        if (respuesta == JFileChooser.APPROVE_OPTION) {

            archivoImagen = imageDir.getSelectedFile();
            dirimage = archivoImagen.getAbsolutePath();
            formulario.portadaAsignada();
        }
    }

    public void botonesLibros(){
        for(LibroData l: librosUsuario){
            l.getBotonLibro().addActionListener(this);
        }
    }

    public boolean validarCampos() {
		if(formulario.getTitulo().getText().isEmpty() ||
           formulario.getAutor().getText().isEmpty() ||
           formulario.getYear().getText().isEmpty() ||
           formulario.getISBN().getText().isEmpty() ||
           formulario.getSinopsis().getText().isEmpty() ||
           formulario.getEditorial().getText().isEmpty() ||
           formulario.getEdicion().getText().isEmpty()) {

			return false;
		}
		
		return true;
	}

 private void generarPDF() {
        UIManager.put("FileChooser.cancelButtonText", "Cancelar");
        JFileChooser fileChooser = new JFileChooser("C:\\Users\\Gama\\Desktop");
        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        fileChooser.setAcceptAllFileFilterUsed(false);
        FileNameExtensionFilter pdfs = new FileNameExtensionFilter("Documentos PDF", "pdf");
        fileChooser.addChoosableFileFilter(pdfs);
        fileChooser.setFileFilter(pdfs);
        
        int respuesta = fileChooser.showDialog(visualMain, "Generar PDF");
        
        if (respuesta == JFileChooser.CANCEL_OPTION) {
            JOptionPane.showMessageDialog(visualMain, "Se canceló la generación del PDF");
            return;
        }
        
        try (PdfDocument pdfDoc = new PdfDocument(new PdfWriter(fileChooser.getSelectedFile()));
             Document doc = new Document(pdfDoc, PageSize.LETTER)) {
            float[] anchoColumnas = {1, 2, 2, 2, 2, 2};
            Table tabla = new Table(UnitValue.createPercentArray(anchoColumnas)).useAllAvailableWidth();
            /* 
            //Mis logitos
            Image logo = new Image(ImageDataFactory.create("src/archivos/dasc.png"));
            logo.setWidth(120); 
            float x = 450; 
            float y = 720; 
            logo.setFixedPosition(x, y);
            logo.setBorder(Border.NO_BORDER);
            
            doc.add(logo);
            doc.add(new Paragraph("\n\n\n"));
            
            
            Image logo2 = new Image(ImageDataFactory.create("src/archivos/logouabcs.png"));
            logo2.setWidth(120); 
            float x2 = 50; 
            float y2 = 720; 
            logo2.setFixedPosition(x2, y2);
            logo2.setBorder(Border.NO_BORDER);
            
            doc.add(logo2);
            
            */
            
            
            
            
            
            
            PdfFont font = PdfFontFactory.createFont(StandardFonts.HELVETICA_BOLD);
            Cell cell = new Cell(1, 6)
                    .add(new Paragraph("Tabla de Libros"))
                    .setFont(font)
                    .setFontSize(18)
                    .setFontColor(DeviceGray.WHITE)
                    .setBackgroundColor(new DeviceRgb(79, 129, 189))
                    .setTextAlignment(TextAlignment.CENTER);
            
            tabla.addHeaderCell(cell);
            
            // Agregar encabezados de tabla
            String[] encabezados = {"ISBN", "Título", "Autor", "Año", "Editorial", "Edición"};
            for (String encabezado : encabezados) {
                Cell celdaEncabezado = new Cell()
                        .setBackgroundColor(new DeviceRgb(216, 216, 216))
                        .add(new Paragraph(encabezado))
                        .setFont(font)
                        .setFontSize(12)
                        .setTextAlignment(TextAlignment.CENTER);
                tabla.addHeaderCell(celdaEncabezado);
            }
            
            // Agregar datos de usuarios a la tabla
            for (int i = 0; i < librosUsuario.size(); i++) {
                LibroData libro = librosUsuario.get(i);
                //tabla.addCell(new Cell().setTextAlignment(TextAlignment.CENTER).add(new Paragraph(String.valueOf(i + 1))));
                tabla.addCell(new Cell().setTextAlignment(TextAlignment.CENTER).add(new Paragraph(libro.getIsbn())));
                tabla.addCell(new Cell().setTextAlignment(TextAlignment.CENTER).add(new Paragraph(libro.getTitulo())));
                tabla.addCell(new Cell().setTextAlignment(TextAlignment.CENTER).add(new Paragraph(libro.getAutor())));
                tabla.addCell(new Cell().setTextAlignment(TextAlignment.CENTER).add(new Paragraph(libro.getYear())));
                tabla.addCell(new Cell().setTextAlignment(TextAlignment.CENTER).add(new Paragraph(libro.getEditorial())));
                tabla.addCell(new Cell().setTextAlignment(TextAlignment.CENTER).add(new Paragraph(libro.getEdicion())));
            }
            
            doc.add(tabla);
            
            if (Desktop.isDesktopSupported()) {
                try {
                    Desktop.getDesktop().open(fileChooser.getSelectedFile());
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(visualMain, "No se pudo abrir el archivo generado", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        } catch (java.io.IOException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(visualMain, "No se pudo exportar el PDF", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }



    @Override
    public void windowOpened(WindowEvent e) {
        // TODO Auto-generated method stub
        //throw new UnsupportedOperationException("Unimplemented method 'windowOpened'");
    }

    @Override
    public void windowClosing(WindowEvent e) {
        // TODO Auto-generated method stub
       // throw new UnsupportedOperationException("Unimplemented method 'windowClosing'");
    }

    @Override
    public void windowClosed(WindowEvent e) {
        if (e.getWindow() == datosLibro) {
            for(LibroData l: librosUsuario){
                if(l.getBotonLibro().equals(auxButton)){
                    l.getBotonLibro().setEnabled(true);
                }
            }
            visualMain.updateBookPanel();
        }
    }

    @Override
    public void windowIconified(WindowEvent e) {
        // TODO Auto-generated method stub
        //throw new UnsupportedOperationException("Unimplemented method 'windowIconified'");
    }

    @Override
    public void windowDeiconified(WindowEvent e) {
        // TODO Auto-generated method stub
        //throw new UnsupportedOperationException("Unimplemented method 'windowDeiconified'");
    }

    @Override
    public void windowActivated(WindowEvent e) {
        // TODO Auto-generated method stub
        //throw new UnsupportedOperationException("Unimplemented method 'windowActivated'");
    }

    @Override
    public void windowDeactivated(WindowEvent e) {
        // TODO Auto-generated method stub
        //throw new UnsupportedOperationException("Unimplemented method 'windowDeactivated'");
    }

}
