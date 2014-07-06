package ro.dedodu.dedoduro.ws.model.entity;

import org.springframework.data.jpa.domain.AbstractPersistable;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Entidade que corresponde aos Comentários dos Eventos
 * registrados pelos Usuários da plataforma.
 * @author Daniel R C Frank
 * @since 05/07/2014
 * @version 1.0.0
 */
@Entity
@Table(name = "gps_register_comment")
public class GpsRegisterComment  extends AbstractPersistable<Long> {

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    /**
     * Obtêm o usuário que realizou o comentário.
     * @return Usuário.
     */
    public User getUser() {
        return user;
    }

    /**
     * Define o usuário que realizou o comentário.
     * @param user Usuário.
     */
    public void setUser(User user) {
        this.user = user;
    }

    @ManyToOne
    @JoinColumn(name = "gps_register_id")
    private GpsRegister gpsRegister;

    /**
     * Obtêm o registro ao qual o comentário pertence.
     * @return Registro.
     */
    public GpsRegister getGpsRegister() {
        return gpsRegister;
    }

    /**
     * Define o registro ao qual o comentário pertence.
     * @param gpsRegister Registro.
     */
    public void setGpsRegister(GpsRegister gpsRegister) {
        this.gpsRegister = gpsRegister;
    }

    private String comment;

    /**
     * Obtêm o comentário em si.
     * @return Comentário.
     */
    public String getComment() {
        return comment;
    }

    /**
     * Define o comentário em si.
     * @param comment Comentário.
     */
    public void setComment(String comment) {
        this.comment = comment;
    }
}