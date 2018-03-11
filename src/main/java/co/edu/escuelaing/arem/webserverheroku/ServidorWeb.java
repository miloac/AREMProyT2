package co.edu.escuelaing.arem.webserverheroku;

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

/**
 *
 * @author 2110242
 */
public class ServidorWeb {
    
    public static void main(String[] args) {
        while (true) {
            Runnable server = new ServerThread();
            Thread thread = new Thread(server);
            thread.start();
            try {
                thread.join();
            } catch (InterruptedException ex) {
                Logger.getLogger(ServidorWeb.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
