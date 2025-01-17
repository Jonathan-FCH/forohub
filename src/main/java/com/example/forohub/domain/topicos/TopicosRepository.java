package com.example.forohub.domain.topicos;

import com.example.forohub.topico.Topico;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface TopicosRepository extends JpaRepository <Topico, Long>  {
//    Page<Topico> findAll(Pageable paginacion);

    @Query("SELECT t FROM Topico t WHERE t.status = true")
    Page<Topico> findAllTopicos(Pageable paginacion);

    ///////////////////////////////////////////////////////////////////////////////////////

    @Query("SELECT t FROM Topico t WHERE t.id = :id AND t.status = true")
    Optional<Topico> findByIdAndStatusTrue(@Param("id") Long id);






}
