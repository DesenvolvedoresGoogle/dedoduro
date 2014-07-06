package ro.dedodu.dedoduro.ws.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import ro.dedodu.dedoduro.ws.model.entity.GpsRegister;
import ro.dedodu.dedoduro.ws.model.repository.GpsRegisterRepository;

import java.util.List;

/**
 * Controlador que provê o acesso aos Eventos
 * registrados pelos Usuários da plataforma.
 * @author Daniel R C Frank
 * @since 05/07/2014
 * @version 1.0.0
 */
@RequestMapping("registers")
@RestController
public class GpsRegisterController {

    private final GpsRegisterRepository repository;

    @Autowired
    public GpsRegisterController(GpsRegisterRepository repository) {
        this.repository = repository;
    }

    @RequestMapping(method = RequestMethod.GET)
    public List<GpsRegister> findAll() {
        return repository.findAll();
    }

    @Transactional
    @RequestMapping(method = RequestMethod.POST)
    public Long save(@RequestBody GpsRegister register) {
        repository.save(register);

        return register.getId();
    }

    //TODO Implementar a obtenção dos registros com base nos parâmetros zoom e karma.
}