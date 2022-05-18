package com.smartcash.engine.repository;

import com.smartcash.engine.models.domain.Nota;
import com.smartcash.engine.models.enums.TipoNota;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface NotaRepository extends PagingAndSortingRepository<Nota, Long> {

    List<Nota> findByContaIdAndDataBetween(Long idConta, LocalDate start, LocalDate end);

    @Query("select count(valor) from Nota where tipo = :tipoNota")
    Double countTotal(TipoNota tipoNota);
}
