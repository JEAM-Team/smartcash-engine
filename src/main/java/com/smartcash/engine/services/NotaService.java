package com.smartcash.engine.services;

import com.smartcash.engine.exceptions.NotFoundException;
import com.smartcash.engine.models.domain.Atividade;
import com.smartcash.engine.models.domain.Nota;
import com.smartcash.engine.models.dtos.CalculaResultadoDto;
import com.smartcash.engine.models.dtos.EditNota;
import com.smartcash.engine.models.dtos.NotaDTO;
import com.smartcash.engine.models.dtos.NotaView;
import com.smartcash.engine.models.enums.TipoCarteira;
import com.smartcash.engine.repository.NotaRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

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
        var tag = dto.tagId() != null ? tagService.findById(dto.tagId()) : null;
        var carteira = carteiraService.findById(dto.carteiraId());
        var nota = new Nota();
        if (carteira.getTipo().equals(TipoCarteira.COMERCIAL) && dto.produtoId() != null)
            nota.setProduto(produtoService.findById(dto.produtoId()));
        var conta = contaService.findById(dto.contaId());

        BeanUtils.copyProperties(dto, nota, "id", "tagId", "contaId", "carteiraId", "produtoId");
        nota.setTag(tag);
        nota.setCarteira(carteira);
        nota.setConta(conta);
        atividadeService.create(Atividade.builder().nota(nota).carteira(nota.getCarteira()).build());
        createRepeticao(nota);
        repository.save(nota);
    }


    public List<NotaView> findAll() {
        var notas = repository.findAll();
        var views = new ArrayList<NotaView>();
        notas.forEach(nota -> {
            var notaDto = NotaView.builder()
                    .titulo(nota.getTitulo())
                    .valor(nota.getValor())
                    .data(nota.getData())
                    .build();
            views.add(notaDto);
        });
        return views;
    }

    public Nota findById(Long id) {
        return repository.findById(id).orElseThrow(() -> new NotFoundException("Não pode ser encontrado a nota com ID: " + id));
    }

    public void update(Long id, EditNota dto) {
        var nota = findById(id);
        BeanUtils.copyProperties("id", "tagId", "contaId", "carteiraId", "produtoId");
        repository.save(nota);
    }

    public void delete(Long id) {
        var nota = findById(id);
        repository.delete(nota);
    }

    public List<Nota> findByContaId(Long contaId, LocalDate start, LocalDate end) {
        return repository.findByContaIdAndDataBetween(contaId, start, end);
    }

    public void createRepeticao(Nota nota) {
        if (nota.getQtdVezes() > 1 && nota.getRepeticao().equals(true)) {
            LocalDate data = nota.getData();
            for (int i = 1; i < nota.getQtdVezes(); i++) {
                Nota notaRepeticao = Nota.builder().titulo(nota.getTitulo()).valor(nota.getValor()).repeticao(true).qtdVezes(0)
                        .data(data.plusDays(30L * i)).tipo(nota.getTipo()).tag(nota.getTag())
                        .produto(nota.getProduto()).conta(nota.getConta()).carteira(nota.getCarteira()).build();
                atividadeService.create(Atividade.builder().nota(notaRepeticao).carteira(nota.getCarteira()).build());
                repository.save(notaRepeticao);
            }
        }
    }

    public CalculaResultadoDto calculaTotal() {
        var notas = repository.findAll();
        Double recebimento = 0.0;
        Double pagamento = 0.0;
        for (Nota nota : notas) {
            switch (nota.getTipo()) {
                case PAGAMENTO -> pagamento += nota.getValor();
                case RECEBIMENTO -> recebimento += nota.getValor();
            }
        }
        return CalculaResultadoDto.builder().totalPagamento(pagamento).totalRecebimento(recebimento).build();

    }
}
