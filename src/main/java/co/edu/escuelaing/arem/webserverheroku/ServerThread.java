package co.edu.escuelaing.arem.webserverheroku;

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
    
    @Override
    public void run(){
        try {
            ServerThread server = new ServerThread();
            server.startConnection();
        } catch (IOException ex) {
            Logger.getLogger(ServerThread.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void startConnection() throws IOException {
        ServerSocket serverSocket = null;
        Integer port = null;
        try {
            port = (System.getenv("PORT") == null) ? 8080 : new Integer(System.getenv("PORT"));
            System.out.println("PORT: " + port);
            serverSocket = new ServerSocket(port);
        } catch (IOException e) {
            System.err.println("Could not listen on port:" + port);
            System.exit(1);
            }
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
        serverSocket.close();
    }
    
    public String getContent(String getRequest) throws IOException {
        
        URL resource = null;
        if (getRequest.split(" ")[1].equals("/")) {
            resource = ServidorWeb.class.getResource("/index.html");
        }
        else {
            resource = ServidorWeb.class.getResource(getRequest.split(" ")[1]);
        }
        File file = new File(resource.getFile());
        return "HTTP/1.1 200 OK\r\n"
                + "Content-Type: text/html\r\n\r\n" + FileUtils.readFileToString(file, StandardCharsets.UTF_8);
    }
}
