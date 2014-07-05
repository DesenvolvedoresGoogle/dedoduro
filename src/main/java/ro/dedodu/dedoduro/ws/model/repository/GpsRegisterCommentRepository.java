package ro.dedodu.dedoduro.ws.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ro.dedodu.dedoduro.ws.model.entity.GpsRegisterComment;

/**
 * Repositório dos  Comentários dos Eventos
 * registrados pelos Usuários da plataforma.
 * @author Daniel R C Frank
 * @since 05/07/2014
 * @version 1.0.0
 */
@Repository
public interface GpsRegisterCommentRepository extends JpaRepository<GpsRegisterComment, Long> {}