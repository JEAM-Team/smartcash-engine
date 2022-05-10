package com.smartcash.engine.services;

import com.smartcash.engine.models.domain.Atividade;
import com.smartcash.engine.models.domain.Nota;
import com.smartcash.engine.models.dtos.CalculaResultadoDto;
import com.smartcash.engine.models.dtos.NotaDto;
import com.smartcash.engine.models.enums.TipoNota;
import com.smartcash.engine.repository.NotaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class NotaService {

    @Autowired
    NotaRepository repository;

    @Autowired
    AtividadeService atividadeService;

    public void create(Nota nota) {
        atividadeService.create(Atividade.builder().nota(nota).carteira(nota.getCarteira()).build());
        repository.save(nota);
    }


    public List<NotaDto> findAll() {
        List<Nota> notas = repository.findAll();
        List<NotaDto> notaDtos = new ArrayList<>();
        notas.forEach(nota -> {
            NotaDto notaDto = new NotaDto();
            notaDto.setTitulo(nota.getTitulo());
            notaDto.setValor(nota.getValor());
            notaDto.setData(nota.getData());
            notaDtos.add(notaDto);
        });
        return notaDtos;
    }

    public Optional<Nota> findById(Long id) {
        return repository.findById(id);
    }

    public void update(Long id, Nota nota) {
        repository.findById(id).map(not -> {
            nota.setId(id);
            repository.save(nota);
            return nota;
        });
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }

    public List<Nota> findByContaId(Long contaId) {
        return repository.findByContaId(contaId);
    }

    public CalculaResultadoDto calculaTotal() {
        Double totalPagamento = repository.countTotal(TipoNota.PAGAMENTO);
        Double totalRecebimento = repository.countTotal(TipoNota.RECEBIMENTO);

        return CalculaResultadoDto.builder().totalPagamento(totalPagamento).totalRecebimento(totalRecebimento).build();

    }
}
