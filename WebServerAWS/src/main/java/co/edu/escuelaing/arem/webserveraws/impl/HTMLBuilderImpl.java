/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.escuelaing.arem.webserveraws.impl;

import co.edu.escuelaing.arem.webserveraws.HTMLBuilder;
import co.edu.escuelaing.arem.webserveraws.apps.APIApplication;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author daniel
 */
@Service
public class HTMLBuilderImpl implements HTMLBuilder {
    
    private APIApplication api;
    private ObjectMapper mapper;
    
    public HTMLBuilderImpl() {
        this.mapper = new ObjectMapper();
    }
    
    @Autowired
    public void setAPIApplication(APIApplication api) {
        this.api = api;
    }
    
    @Override
    public String getHTML(URL requestURL) {
        String response = "Esta pagina no existe, lo siento";
        URL resource;
        if (requestURL.getPath().equals("/")) {
            resource = HTMLBuilderImpl.class.getResource("/index.html");
        }
        else {
            resource = HTMLBuilderImpl.class.getResource(requestURL.getPath());
            System.out.println(resource);
        }
        File file = new File(resource.getFile());
        try {
            response = FileUtils.readFileToString(file, StandardCharsets.UTF_8);
        } catch (IOException ex) {
            Logger.getLogger(HTMLBuilderImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        if (requestURL.getQuery() != null) {
            System.out.println("api is null ? " + (api == null));
            String res = api.getResult(requestURL.getQuery());
            System.out.println(res);
            JsonNode node1 = null;
            JsonNode node2 = null;
            try {
                node1 = mapper.readTree(res).path("valor");
                node2 = mapper.readTree(res).path("cuadrado");
            } catch (IOException ex) {
                Logger.getLogger(HTMLBuilderImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
            System.out.println("Node2 as text:" + node2.asText());
            response = response.replace("{valor}", node1.asText());
            response = response.replace("{cuadrado}", node2.asText());
        }
        
        return response;
    }
    
}
