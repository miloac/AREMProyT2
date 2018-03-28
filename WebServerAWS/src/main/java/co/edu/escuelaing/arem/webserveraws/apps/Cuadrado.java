package co.edu.escuelaing.arem.webserveraws.apps;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.stereotype.Service;

/**
 * Esta clase se conecta al API https://cuadradoapp.herokuapp.com y retorna el resultado.
 * Si se encuentra un API local que pueda dar respuesta, se prefiere este. (Esto con proposito de pruebas de desarrollo.)
 * @author Daniel Ospina
 */
@Service
public class Cuadrado implements APIApplication{

    private final String API_URL = "https://cuadradoapp.herokuapp.com";

    @Override
    public String getResult(String query) {
        URL url = null;
        boolean local = testLocalConnection();
        String host;
        if (local) {
            host = "http://localhost:8080/cuadrado?";
            System.out.println("Detected and using local API at: " + host);
        }
        else host = API_URL;
        try {
            url = new URL(host + query);
        } catch (MalformedURLException ex) {
            Logger.getLogger(Cuadrado.class.getName()).log(Level.SEVERE, null, ex);
        }
        String response = "";
        try (BufferedReader reader = new BufferedReader(
            new InputStreamReader(url.openStream()))) { 
                String inputLine = null; 
                while ((inputLine = reader.readLine()) != null) { 
                    response = response + inputLine;
                } 
        } catch (IOException x) { 
            System.err.println(x); 
        }
        return response;
    }
    
    private boolean testLocalConnection() {
        URL url = null;
        HttpURLConnection urlConn;
        boolean local = false;
        try {
            url = new URL("http://localhost:8080/cuadrado?valor=1");
            urlConn = (HttpURLConnection) url.openConnection();
            urlConn.connect();
            if (urlConn.getResponseCode() == HttpURLConnection.HTTP_ACCEPTED) local = true;
            urlConn.disconnect();
        } catch (MalformedURLException ex) {
            System.err.println("Error: URL Malformada");
        } catch (IOException ex) {
            System.out.println("No se pudo detectar un API local");
        }
        return local;
    }
    
}
