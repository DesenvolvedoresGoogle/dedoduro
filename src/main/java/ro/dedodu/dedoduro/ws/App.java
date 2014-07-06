package ro.dedodu.dedoduro.ws;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.context.embedded.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import javax.servlet.MultipartConfigElement;
import java.util.List;

/**
 * Backend para as aplicações da plataforma Dedo Duro.
 * @author Daniel R C Frank
 * @since 05/07/2014
 * @version 1.0.0
 */
@Configuration
@ComponentScan
@EnableAutoConfiguration
public class App extends WebMvcConfigurerAdapter {

    @Bean
    MultipartConfigElement multipartConfigElement() {
        MultipartConfigFactory factory = new MultipartConfigFactory();
        //factory.setMaxFileSize("128KB");
        //factory.setMaxRequestSize("128KB");
        //TODO Implementar os limites para o arquivo.

        return factory.createMultipartConfig();
    }

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
        PageableHandlerMethodArgumentResolver resolver = new PageableHandlerMethodArgumentResolver();

        argumentResolvers.add(resolver);

        super.addArgumentResolvers(argumentResolvers);
    }

    /**
     * Ponto de entrada.
     * @param args Argumetos passado para a aplicação.
     */
    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }
}