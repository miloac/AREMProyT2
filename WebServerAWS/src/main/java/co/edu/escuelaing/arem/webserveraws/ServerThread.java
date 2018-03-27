package co.edu.escuelaing.arem.webserveraws;

import co.edu.escuelaing.arem.webserveraws.impl.HTMLBuilderImpl;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.io.FileUtils;

/**
 *
 * @author daniel
 */
public class ServerThread implements Runnable {
    
    ServerSocket serverSocket;
    HTMLBuilder htmlBuilder;
    
    public ServerThread(ServerSocket s, HTMLBuilder htmlBuilder) {
        serverSocket = s;
        this.htmlBuilder = htmlBuilder;
    }
    
    @Override
    public void run(){
        try {
            while (true) {
                startConnection();
            }
        } catch (IOException ex) {
            Logger.getLogger(ServerThread.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void startConnection() throws IOException {
        Socket clientSocket = null;
        try {
            System.out.println("Listo para recibir ...");
            clientSocket = serverSocket.accept();
        } catch (IOException e) {
            System.err.println("Accept failed.");
            e.printStackTrace();
            System.exit(1);
        }
        
        PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
        BufferedReader in = new BufferedReader(
                new InputStreamReader(
                        clientSocket.getInputStream()
        ));
        String inputLine, outputLine;
        
        while ((inputLine = in.readLine()) != null) {
            System.out.println("Received: " + inputLine);
            if (inputLine.startsWith("GET")) {
                outputLine = getContent(inputLine);
                out.println(outputLine);
            }
            if (!in.ready()) {
                break;
            }
            if (inputLine.equals("")) break;
        }
                
        out.close();
        in.close();
        clientSocket.close();
    }
    
    public String getContent(String stringRequest) throws IOException {
        URL requestURL = new URL("http://localhost:8080" + stringRequest.split(" ")[1]);
        System.out.println("Query: " + requestURL.getQuery());
        String htmlCode = htmlBuilder.getHTML(requestURL);
        return "HTTP/1.1 200 OK\r\n"
                + "Content-Type: text/html\r\n\r\n" + htmlCode;

    }
}
