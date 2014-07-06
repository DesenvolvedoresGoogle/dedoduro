package ro.dedodu.dedoduro.ws.model.entity;

import org.springframework.data.jpa.domain.AbstractPersistable;

import javax.persistence.*;

/**
 * Entidade que corresponde aos Eventos
 * registrados pelos Usuários da plataforma.
 * @author Daniel R C Frank
 * @since 05/07/2014
 * @version 1.0.0
 */
@Entity
@Table(name = "gps_register")
public class GpsRegister  extends AbstractPersistable<Long> {

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    /**
     * Obtêm o usuário responsável pelo registro.
     * @return Usuário responsável.
     */
    public User getUser() {
        return user;
    }

    /**
     * Define o usuário responsável pelo registro.
     * @param user Usuário responsavel.
     */
    public void setUser(User user) {
        this.user = user;
    }

    private double lat;

    /**
     * Obtêm a latitude do registro.
     * @return Latitude
     */
    public double getLat() {
        return lat;
    }

    /**
     * Define a latitude do registro.
     * @param lat Latitude.
     */
    public void setLat(double lat) {
        this.lat = lat;
    }

    private double lng;

    /**
     * Obtêm a longitude do registro.
     * @return Longitude.
     */
    public double getLng() {
        return lng;
    }

    /**
     * Define a longitude do registro.
     * @param lng Longitude.
     */
    public void setLng(double lng) {
        this.lng = lng;
    }

    private String description;

    /**
     * Obtêm a descrição do registro.
     * @return Descrição.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Define a descrição do registro.
     * @param description Descrição.
     */
    public void setDescription(String description) {
        this.description = description;
    }

    @OneToOne
    @JoinColumn(name = "category_id")
    private Category category;

    /**
     * Obtêm a categoria a qual o registro pertence.
     * @return Categoria.
     */
    public Category getCategory() {
        return category;
    }

    /**
     * Define a categoria a qual o registro pertence.
     * @param category Categoria.
     */
    public void setCategory(Category category) {
        this.category = category;
    }

    /**
     * Define a chave primária (Utilizado apenas para a definição de exemplos).
     * @param id Chave Primária.
     */
    public void setId(Long id) {
        super.setId(id);
    }
}