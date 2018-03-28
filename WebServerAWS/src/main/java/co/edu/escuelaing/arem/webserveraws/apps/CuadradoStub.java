package co.edu.escuelaing.arem.webserveraws.apps;

import org.springframework.stereotype.Service;

/**
 * Código Stub con propósitos de prueba durante el desarrollo
 * @author Daniel Ospina
 */
public class CuadradoStub implements APIApplication {

    @Override
    public String getResult(String query) {
        return "{\"valor\":\"10\", \"cuadrado\":\"100\"}";
    }
    
}
