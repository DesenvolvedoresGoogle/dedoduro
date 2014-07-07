package ro.dedodu.dedoduro.ws.model.entity;

import org.springframework.data.jpa.domain.AbstractPersistable;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Entidade que corresponde ás possíveis Categorias
 * de Eventos registrados pelos Usuários da plataforma.
 * @author Daniel R C Frank
 * @since 05/07/2014
 * @version 1.0.0
 */
@Entity
@Table(name = "category")
public class Category extends AbstractPersistable<Long> {

    private String name;

    /**
     * Obtêm o nome da categoria.
     * @return Nome.
     */
    public String getName() {
        return name;
    }

    /**
     * Define o nome da categoria.
     * @param name Nome.
     */
    public void setName(String name) {
        this.name = name;
    }

    private String img;

    /**
     * Obtêm o nome da imagem que representa a categoria.
     * @return Imagem representativa.
     */
    public String getImg() {
        return img;
    }

    /**
     * Define o nome da imagem que representa a categoria.
     * @param img Imagem representativa.
     */
    public void setImg(String img) {
        this.img = img;
    }

    /**
     * Define a chave primária (Utilizado apenas para a definição de exemplos).
     * @param id Chave Primária.
     */
    public void setId(Long id) {
        super.setId(id);
    }
}