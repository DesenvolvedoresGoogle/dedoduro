package ro.dedodu.dedoduro.ws.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import ro.dedodu.dedoduro.ws.model.entity.GpsRegister;
import ro.dedodu.dedoduro.ws.model.entity.GpsRegisterRate;
import ro.dedodu.dedoduro.ws.model.entity.User;
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

    @RequestMapping(value = "byGpsRegisterAndUser", method = RequestMethod.GET)
    public GpsRegisterRate findByGpsRegisterAndUser(@RequestParam("registerId") Long registerId,
                                                    @RequestParam("userId") Long userId) {
        GpsRegister register = new GpsRegister();
        register.setId(registerId);

        User user = new User();
        user.setId(userId);

        return repository.findByGpsRegisterAndUser(register, user);
    }

    @RequestMapping(value = "sum", method = RequestMethod.GET)
    public Long summarizeByGpsRegister(@RequestParam("registerId") Long registerId) {
        return repository.summarizeByGpsRegisterId(registerId);
    }
}