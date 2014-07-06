package ro.dedodu.dedoduro.ws.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import ro.dedodu.dedoduro.ws.model.entity.GpsRegister;
import ro.dedodu.dedoduro.ws.model.entity.GpsRegisterComment;
import ro.dedodu.dedoduro.ws.model.repository.GpsRegisterCommentRepository;

/**
 * Controlador que provê o acesso aos Comentários dos Eventos
 * registrados pelos Usuários da plataforma.
 * @author Daniel R C Frank
 * @since 05/07/2014
 * @version 1.0.0
 */
@RequestMapping("comments")
@RestController
public class GpsRegisterCommentController {

    private final GpsRegisterCommentRepository repository;

    @Autowired
    public GpsRegisterCommentController(GpsRegisterCommentRepository repository) {
        this.repository = repository;
    }

    @RequestMapping(method = RequestMethod.GET)
    public Page<GpsRegisterComment> findAll(Pageable pageable) {
        return repository.findAll(pageable);
    }

    @RequestMapping(value = "byRegister/{registerId}", method = RequestMethod.GET)
    public Page<GpsRegisterComment> findAllByGpsRegisterId(@PathVariable("registerId") Long registerId, Pageable pageable) {
        GpsRegister register = new GpsRegister();
        register.setId(registerId);

        return repository.findAllByGpsRegister(register, pageable);
    }

    @Transactional
    @RequestMapping(method = RequestMethod.POST)
    public Long save(@RequestBody GpsRegisterComment comment) {
        repository.save(comment);

        return comment.getId();
    }
}