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

    /**
     * Obtêm o registro ao qual a imagem pertence.
     * @return Registro.
     */
    public GpsRegister getGpsRegister() {
        return gpsRegister;
    }

    /**
     * Define o registro ao qual a imagem pertence.
     * @param gpsRegister Registro.
     */
    public void setGpsRegister(GpsRegister gpsRegister) {
        this.gpsRegister = gpsRegister;
    }

    private String description;

    /**
     * Obtêm a descrição da imagem.
     * @return Descrição.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Define a descrição da imagem.
     * @param description Descrição.
     */
    public void setDescription(String description) {
        this.description = description;
    }

    private String image;

    /**
     * Obtêm o nome da imagem.
     * @return Imagem.
     */
    public String getImage() {
        return image;
    }

    /**
     * DEfine o nome da imagem.
     * @param image Imagem.
     */
    public void setImage(String image) {
        this.image = image;
    }
}