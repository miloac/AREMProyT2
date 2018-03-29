package co.edu.escuelaing.arem.webserveraws;

import co.edu.escuelaing.arem.webserveraws.impl.HTMLBuilderImpl;
import java.io.IOException;
import java.net.ServerSocket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Main Class, encargada de levantar el servidor web.
 * Hace un test de variable de ambiente, si no se encuentra corre en el puerto 8081.
 * Crea un ServerSocket y un ThreadPool para recibir conexiones en MultiThread
 * @author Daniel Ospina
 */
public class ServidorWeb {

    private static final int CONCURRENT_THREADS = 4;
    
    public static void main(String[] args) {
         
        ServerSocket serverSocket = null;
        Integer port = null;
        try {
            port = (System.getenv("PORT") == null) ? 8081 : new Integer(System.getenv("PORT"));
            System.out.println("PORT: " + port);
            serverSocket = new ServerSocket(port);
        } catch (IOException e) {
            System.err.println("Could not listen on port:" + port);
            System.exit(1);
        }
        
        ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
        HTMLBuilder htmlBuilder = ac.getBean(HTMLBuilderImpl.class);
        
        ExecutorService exService = Executors.newFixedThreadPool(CONCURRENT_THREADS);
        for (int x = 0; x < CONCURRENT_THREADS; x++) {
            Runnable server = new ServerThread(serverSocket, htmlBuilder);
            exService.execute(server);
        } 
    }
}
