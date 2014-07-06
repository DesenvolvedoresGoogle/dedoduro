package ro.dedodu.dedoduro.ws.controller;

import com.google.common.base.Preconditions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import ro.dedodu.dedoduro.ws.model.entity.User;
import ro.dedodu.dedoduro.ws.model.repository.UserRepository;

import java.util.List;

/**
 * Controlador que provê o acesso aos Usuários da plataformaa.
 * @author Daniel R C Frank
 * @since 05/07/2014
 * @version 1.0.0
 */
@RequestMapping("users")
@RestController
public class UserController {

    private final UserRepository repository;

    @Autowired
    public UserController(UserRepository repository) {
        this.repository = repository;
    }

    @RequestMapping(method = RequestMethod.GET)
    public List<User> findAll() {
        return repository.findAll();
    }

    @Transactional
    @RequestMapping(method = RequestMethod.POST)
    public Long save(@RequestBody User user) {
        repository.save(user);
        return user.getId();
    }

    @RequestMapping(value = "login", method = RequestMethod.GET)
    public User login(@RequestParam("email") String email, @RequestParam("password") String password) {
        User user = repository.findByEmail(email);

        Preconditions.checkNotNull(user, "Usuário inexistente.");

        if (password.equals(user.getPassword())) {
            return user;
        }

        return null;
    }
}