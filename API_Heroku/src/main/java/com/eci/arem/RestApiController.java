package com.eci.arem;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.logging.Level;
import java.util.logging.Logger;

@RestController
@RequestMapping(value = "/cuadrado")
public class RestApiController {

    @RequestMapping(path = "/{numero}", method = RequestMethod.GET)
    public ResponseEntity<?> manejadorGetRecursoCuadrado(@PathVariable int numero){
        try {
            return new ResponseEntity<>((int) Math.pow(numero,2),HttpStatus.ACCEPTED);
        } catch (Exception ex) {
            Logger.getLogger(RestApiController.class.getName()).log(Level.SEVERE, null, ex);
            return new ResponseEntity<>("Error, no se pudo sacar el cuadrado del numero",HttpStatus.NOT_FOUND);
        }
    }
}