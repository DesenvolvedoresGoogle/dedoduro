package ro.dedodu.dedoduro.ws.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import ro.dedodu.dedoduro.ws.model.entity.GpsRegisterImage;
import ro.dedodu.dedoduro.ws.model.repository.GpsRegisterImageRepository;

import java.util.List;

/**
 * Controlador que provê o acesso às Imagens dos Eventos
 * registrados pelos Usuários da plataforma.
 * @author Daniel R C Frank
 * @since 05/07/2014
 * @version 1.0.0
 */
@RequestMapping("images")
@RestController
public class GpsRegisterImageController {

    private final GpsRegisterImageRepository repository;

    @Autowired
    public GpsRegisterImageController(GpsRegisterImageRepository repository) {
        this.repository = repository;
    }

    @RequestMapping(method = RequestMethod.GET)
    public List<GpsRegisterImage> findAll() {
        return repository.findAll();
    }

    @Transactional
    @RequestMapping(method = RequestMethod.POST)
    public void save(@RequestBody GpsRegisterImage image) {
        repository.save(image);
    }

    //TODO implementar o upload das imagens.
}