package ro.dedodu.dedoduro.ws.model.entity;

import org.springframework.data.jpa.domain.AbstractPersistable;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Entidade que corresponde aos Usuários da plataforma.
 * @author Daniel R C Frank
 * @since 05/07/2014
 * @version 1.0.0
 */
@Entity
@Table(name = "user")
public class User extends AbstractPersistable<Long> {
    private String email;

    /**
     * Obtêm o e-mail do usuário.
     * @return E-mail.
     */
    public String getEmail() {
        return email;
    }

    /**
     * Define o e-mail do usuário.
     * @param email E-nmail.
     */
    public void setEmail(String email) {
        this.email = email;
    }

    private String password;

    /**
     * Obtêm a senha do usuário.
     * @return Usuário.
     */
    public String getPassword() {
        return password;
    }

    /**
     * Define a senha do usuário.
     * @param password Senha.
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Define a chave primária (Utilizado apenas para a definição de exemplos).
     * @param id Chave Primária.
     */
    public void setId(Long id) {
        super.setId(id);
    }
}