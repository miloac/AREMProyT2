/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.escuelaing.arem.webserveraws;

import java.io.IOException;
import java.net.URL;

/**
 *
 * @author daniel
 */
public interface HTMLBuilder {
    
    String getHTML(URL request);
    
}
