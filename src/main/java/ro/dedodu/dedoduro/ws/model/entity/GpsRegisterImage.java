package ro.dedodu.dedoduro.ws.model.entity;

import org.springframework.data.jpa.domain.AbstractPersistable;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Entidade que corresponde às Imagens dos Eventos
 * registrados pelos Usuários da plataforma.
 * @author Daniel R C Frank
 * @since 05/07/2014
 * @version 1.0.0
 */
@Entity
@Table(name = "gps_register_image")
public class GpsRegisterImage extends AbstractPersistable<Long> {

    @ManyToOne
    @JoinColumn(name = "gps_register_id")
    private GpsRegister gpsRegister;

    public GpsRegister getGpsRegister() {
        return gpsRegister;
    }

    public void setGpsRegister(GpsRegister gpsRegister) {
        this.gpsRegister = gpsRegister;
    }

    private String description;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    private String image;

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}