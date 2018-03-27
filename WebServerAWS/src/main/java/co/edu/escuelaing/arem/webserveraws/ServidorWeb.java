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
import java.net.URLClassLoader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.io.FileUtils;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * @author 2110242
 */
public class ServidorWeb {

    private static final int CONCURRENT_THREADS = 4;
    
    public static void main(String[] args) {
        
        
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
        
        ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
        HTMLBuilder htmlBuilder = ac.getBean(HTMLBuilderImpl.class);
        
        
        //HTMLBuilder htmlBuilder = new HTMLBuilderImpl();
        ExecutorService exService = Executors.newFixedThreadPool(CONCURRENT_THREADS);
        for (int x = 0; x < CONCURRENT_THREADS; x++) {
            Runnable server = new ServerThread(serverSocket, htmlBuilder);
            exService.execute(server);
        } 
    }
}
