package com.eci.arem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
/**
 * Clase ejecutora del API
 */
public class RestApiApp {
    public static void main( String[] args ) {
        SpringApplication.run(RestApiApp.class, args);
    }
}
