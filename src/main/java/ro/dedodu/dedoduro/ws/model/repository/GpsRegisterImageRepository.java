package ro.dedodu.dedoduro.ws.model.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ro.dedodu.dedoduro.ws.model.entity.GpsRegister;
import ro.dedodu.dedoduro.ws.model.entity.GpsRegisterImage;

/**
 * Repositório das Imagens dos Eventos
 * registrados pelos Usuários da plataforma.
 * @author Daniel R C Frank
 * @since 05/07/2014
 * @version 1.0.0
 */
@Repository
public interface GpsRegisterImageRepository extends JpaRepository<GpsRegisterImage, Long> {

    public Page<GpsRegisterImage> findAllByGpsRegister(GpsRegister register, Pageable pageable);
}