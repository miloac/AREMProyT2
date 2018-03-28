package com.eci.arem;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@RestController
@RequestMapping("/cuadrado")
/**
 * Controlador para el API de calculo de cuadrados
 */
public class RestApiController {

    @RequestMapping(value = "")
    /**
     * Manejador del API que ingresado un numero al parametro "valor", retorna 
     * en formato JSON el numero ingresado y su cuadrado
     * @return ResponseEntity con el JSON del numero y su cuadrado
     */
    public ResponseEntity<?> ManejadorCuadrado(
        @RequestParam("valor") int valor) {

        Response resp = new Response(valor, (int) Math.pow(valor,2));
        return new ResponseEntity<>(resp, HttpStatus.ACCEPTED);

    }

}