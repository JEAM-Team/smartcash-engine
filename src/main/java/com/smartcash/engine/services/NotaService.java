package com.smartcash.engine.services;

import com.smartcash.engine.models.domain.Atividade;
import com.smartcash.engine.models.domain.Nota;
import com.smartcash.engine.models.dtos.NotaDto;
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
        createRepeticao(nota);
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

    public void createRepeticao(Nota nota) {
        if (nota.getQtdVezes() > 1 && nota.getRepeticao().equals(true)) {
            for (int i = 0; i < nota.getQtdVezes()-1; i++) {
                //Ajustar data da nota
                Nota notaRepeticao = Nota.builder().titulo(nota.getTitulo()).valor(nota.getValor()).repeticao(true)
                        .data(nota.getData().plusDays(30)).tipo(nota.getTipo()).tag(nota.getTag())
                        .produto(nota.getProduto()).conta(nota.getConta()).carteira(nota.getCarteira()).build();
                atividadeService.create(Atividade.builder().nota(notaRepeticao).carteira(nota.getCarteira()).build());
                repository.save(notaRepeticao);
            }
        }
    }
}
