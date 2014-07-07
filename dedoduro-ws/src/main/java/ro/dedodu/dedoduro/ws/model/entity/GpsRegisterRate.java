package ro.dedodu.dedoduro.ws.model.entity;

import org.springframework.data.jpa.domain.AbstractPersistable;

import javax.persistence.*;

/**
 * Entidade que corresponde ao Karma relativo
 * aos Eventos registrados pelos Usuários da plataforma.
 * @author Daniel R C Frank
 * @since 05/07/2014
 * @version 1.0.0
 */
@Entity
@Table(name = "gps_register_rate")
public class GpsRegisterRate extends AbstractPersistable<Long> {

    @OneToOne
    @JoinColumn(name = "gps_register_id")
    private GpsRegister gpsRegister;

    /**
     * Obtêm o registro vinculado a este karma.
     * @return Registro.
     */
    public GpsRegister getGpsRegister() {
        return gpsRegister;
    }

    /**
     * Define o registro vinculado a este karma.
     * @param gpsRegister Registro.
     */
    public void setGpsRegister(GpsRegister gpsRegister) {
        this.gpsRegister = gpsRegister;
    }

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    /**
     * Obtêm o usuário que registrou o karma.
     * @return Usuário.
     */
    public User getUser() {
        return user;
    }

    /**
     * Define o usuário que registrou o karma.
     * @param user Usuário.
     */
    public void setUser(User user) {
        this.user = user;
    }

    private Integer rate;

    /**
     * Obtêm o grau do karma (-1, 1).
     * @return Grau.
     */
    public Integer getRate() {
        return rate;
    }

    /**
     * Define o grau do karma (-1, 1).
     * @param rate Grau.
     */
    public void setRate(Integer rate) {
        this.rate = rate;
    }
}