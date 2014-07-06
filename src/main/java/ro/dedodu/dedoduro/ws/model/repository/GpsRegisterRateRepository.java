package ro.dedodu.dedoduro.ws.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ro.dedodu.dedoduro.ws.model.entity.GpsRegister;
import ro.dedodu.dedoduro.ws.model.entity.GpsRegisterRate;
import ro.dedodu.dedoduro.ws.model.entity.User;

/**
 * Repositório dos Karmas relativos
 * aos Eventos registrados pelos Usuários da plataforma..
 * @author Daniel R C Frank
 * @since 05/07/2014
 * @version 1.0.0
 */
@Repository
public interface GpsRegisterRateRepository extends JpaRepository<GpsRegisterRate, Long> {

    /**
     * Sumariza o karma de um dado registro.
     * @param registerId Identificador do registro.
     * @return Sumarização.
     */
    @Query("select sum(r.rate) from  GpsRegisterRate r where r.gpsRegister.id = :registerId")
    public Long summarizeByGpsRegisterId(@Param("registerId") Long registerId);

    /**
     * Busca o karma atribuído por um dado usuário para um dado registro.
     * @param register Registro.
     * @param user Usuário.
     * @return Karma.
     */
    public GpsRegisterRate findByGpsRegisterAndUser(GpsRegister register, User user);
}