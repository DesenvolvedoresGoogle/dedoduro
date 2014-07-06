package ro.dedodu.dedoduro.ws.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import ro.dedodu.dedoduro.ws.model.entity.GpsRegisterRate;
import ro.dedodu.dedoduro.ws.model.repository.GpsRegisterRateRepository;

import java.util.List;

/**
 * Controlador que provê o acesso ao Karma relativo
 * aos Eventos registrados pelos Usuários da plataforma.
 * @author Daniel R C Frank
 * @since 05/07/2014
 * @version 1.0.0
 */
@RequestMapping("karma")
@RestController
public class GpsRegisterRateController {

    private final GpsRegisterRateRepository repository;

    @Autowired
    public GpsRegisterRateController(GpsRegisterRateRepository repository) {
        this.repository = repository;
    }

    @RequestMapping(method = RequestMethod.GET)
    public List<GpsRegisterRate> findAll() {
        return repository.findAll();
    }

    @Transactional
    @RequestMapping(method = RequestMethod.POST)
    public Long save(@RequestBody GpsRegisterRate rate) {
        repository.save(rate);

        return rate.getId();
    }

    @RequestMapping(value = "sum", method = RequestMethod.GET)
    public Long summarizeByGpsRegister(@RequestParam("registerId") Long registerId) {
        return repository.summarizeByGpsRegisterId(registerId);
    }
}