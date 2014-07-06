package ro.dedodu.dedoduro.ws.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import ro.dedodu.dedoduro.ws.model.entity.GpsRegisterComment;
import ro.dedodu.dedoduro.ws.model.repository.GpsRegisterCommentRepository;

import java.util.List;

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
    public List<GpsRegisterComment> findAll() {
        return repository.findAll();
    }

    @Transactional
    @RequestMapping(method = RequestMethod.POST)
    public Long save(@RequestBody GpsRegisterComment comment) {
        repository.save(comment);

        return comment.getId();
    }
}