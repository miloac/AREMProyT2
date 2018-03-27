/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.escuelaing.arem.webserveraws.apps;

import org.springframework.stereotype.Service;

/**
 *
 * @author daniel
 */
@Service
public class CuadradoStub implements APIApplication {

    @Override
    public String getResult(String query) {
        return "{\"valor\":\"10\", \"cuadrado\":\"100\"}";
    }
    
}
