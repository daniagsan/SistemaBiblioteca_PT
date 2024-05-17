package SistemaBiblioteca.Visual;

import java.util.ResourceBundle;

public class DisplayText {
    

    private String defaultNameFile  = "SistemaBiblioteca.lang_es_MX";
    private ResourceBundle rb =  ResourceBundle.getBundle(defaultNameFile);

    public DisplayText(){

    }

    public ResourceBundle language(String language){

        switch(language){
            case "es_MX":
            rb = ResourceBundle.getBundle(defaultNameFile);
            return rb;

            default:
            return rb;
        }

    }


    public String getDefaultNameFile() {
        return defaultNameFile;
    }

    public void setDefaultNameFile(String fileName) {
        this.defaultNameFile = fileName;
    }

    public ResourceBundle getRb() {
        return rb;
    }

    public void setRb(ResourceBundle rb) {
        this.rb = rb;
    }

    



}
