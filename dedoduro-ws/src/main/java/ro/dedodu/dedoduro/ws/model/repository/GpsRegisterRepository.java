package ro.dedodu.dedoduro.ws.model.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ro.dedodu.dedoduro.ws.model.entity.Category;
import ro.dedodu.dedoduro.ws.model.entity.GpsRegister;

/**
 * Repositório dos Eventos registrados pelos
 * Usuários da plataforma.
 * @author Daniel R C Frank
 * @since 05/07/2014
 * @version 1.0.0
 */
@Repository
public interface GpsRegisterRepository extends JpaRepository<GpsRegister, Long> {

    /**
     * Busca todos registros de uma dada categoria.
     * @param category Categoria desejada.
     * @param pageable Dados da paginação.
     * @return Página de resultados.
     */
    public Page<GpsRegister> findAllByCategory(Category category, Pageable pageable);
}