package ro.dedodu.dedoduro.ws;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * Backend para as aplicações da plataforma Dedo Duro.
 * @author Daniel R C Frank
 * @since 05/07/2014
 * @version 1.0.0
 */
@Configuration
@ComponentScan
@EnableAutoConfiguration
public class App {

    /**
     * Ponto de entrada.
     * @param args Argumetos passado para a aplicação.
     */
    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }
}