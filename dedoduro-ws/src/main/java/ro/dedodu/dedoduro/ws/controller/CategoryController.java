package ro.dedodu.dedoduro.ws.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import ro.dedodu.dedoduro.ws.model.entity.Category;
import ro.dedodu.dedoduro.ws.model.repository.CategoryRepository;

import java.util.List;

/**
 * Controlador que provê o acesso às possíveis Categorias
 * de Eventos registrados pelos Usuários da plataforma.
 * @author Daniel R C Frank
 * @since 05/07/2014
 * @version 1.0.0
 */
@RequestMapping("categories")
@RestController
public class CategoryController {

    private final CategoryRepository repository;

    /**
     * Construtor.
     * @param repository Repositório de categorias.
     */
    @Autowired
    public CategoryController(CategoryRepository repository) {
        this.repository = repository;
    }

    /**
     * Busca por todas as categorias.
     * @return Categorias.
     */
    @RequestMapping(method = RequestMethod.GET)
    public List<Category> findAll() {
        return repository.findAll();
    }

    /**
     * Inclui/Altera uma categoria.
     * @param category Categoria.
     * @return Identificador.
     */
    @Transactional
    @RequestMapping(method = RequestMethod.POST)
    public Long save(@RequestBody Category category) {
        repository.save(category);

        return category.getId();
    }
}