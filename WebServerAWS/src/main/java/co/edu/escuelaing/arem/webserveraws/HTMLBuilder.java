package co.edu.escuelaing.arem.webserveraws;

import java.net.URL;

/**
 * Interface para constructores/lectores de HTML, útil si el usuario requiere que la página web tenga funcionalidades específicas.
 * @author Daniel Ospina
 */
public interface HTMLBuilder {
    
    String getHTML(URL request);
    
}
