package com.smartcash.engine.services;

import com.smartcash.engine.exceptions.NotFoundException;
import com.smartcash.engine.models.domain.Atividade;
import com.smartcash.engine.models.domain.Nota;
import com.smartcash.engine.models.dtos.NotaDTO;
import com.smartcash.engine.models.enums.TipoCarteira;
import com.smartcash.engine.repository.NotaRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class NotaService {

    @Autowired
    private NotaRepository repository;

    @Autowired
    private AtividadeService atividadeService;

    @Autowired
    private TagService tagService;

    @Autowired
    private ProdutoService produtoService;

    @Autowired
    private ContaService contaService;

    @Autowired
    private CarteiraService carteiraService;

    public void create(NotaDTO dto) {
        var tag = tagService.findById(dto.tagId());
        var carteira = carteiraService.findById(dto.carteiraId());
        var nota = new Nota();
        if (carteira.getTipo().equals(TipoCarteira.COMERCIAL))
            nota.setProduto(produtoService.findById(dto.produtoId()));
        var conta = contaService.findById(dto.contaId());

        BeanUtils.copyProperties(dto, nota, "id", "tagId", "contaId", "carteiraId", "produtoId");
        nota.setTag(tag);
        nota.setCarteira(carteira);
        nota.setConta(conta);
        atividadeService.create(Atividade.builder().nota(nota).carteira(nota.getCarteira()).build());
        repository.save(nota);
    }


    public List<Nota> findAll() {
        return repository.findAll();
    }

    public Nota findById(Long id) {
        return repository.findById(id).orElseThrow(() -> new NotFoundException("Não pode ser encontrado a nota com ID: " + id));
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
}
