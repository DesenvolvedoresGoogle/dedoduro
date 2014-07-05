package ro.dedodu.dedoduro.ws.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ro.dedodu.dedoduro.ws.model.entity.GpsRegisterRate;

/**
 * Repositório dos Karmas relativos
 * aos Eventos registrados pelos Usuários da plataforma..
 * @author Daniel R C Frank
 * @since 05/07/2014
 * @version 1.0.0
 */
@Repository
public interface GpsRegisterRateRepository extends JpaRepository<GpsRegisterRate, Long> {}