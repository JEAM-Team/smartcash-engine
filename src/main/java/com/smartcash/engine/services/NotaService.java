package com.smartcash.engine.services;

import com.smartcash.engine.exceptions.NotFoundException;
import com.smartcash.engine.exceptions.carteira.BadRequestException;
import com.smartcash.engine.models.domain.Atividade;
import com.smartcash.engine.models.domain.Conta;
import com.smartcash.engine.models.domain.Nota;
import com.smartcash.engine.models.domain.Usuario;
import com.smartcash.engine.models.dtos.*;
import com.smartcash.engine.models.enums.TipoCarteira;
import com.smartcash.engine.models.enums.TipoNota;
import com.smartcash.engine.repository.NotaRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

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

    @Autowired
    private UsuarioService usuarioService;

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

    public CalculaResultadoDto calculaTotal(Boolean saldoPessoal, Boolean saldoComercial, Boolean pagamentoPessoal, Boolean pagamentoComercial, String email) {
        var allNull = saldoPessoal == null && saldoComercial == null && pagamentoPessoal == null && pagamentoComercial == null;
        if (allNull) {
            throw new BadRequestException("Informe ao menos um tipo de nota para realizar a busca.");
        }
        var pessoal = Boolean.FALSE.equals(saldoPessoal) && Boolean.FALSE.equals(pagamentoPessoal) ? null : new NotaTotalFilter(TipoCarteira.PESSOAL, saldoPessoal, pagamentoPessoal);
        var comercial = Boolean.FALSE.equals(saldoComercial) && Boolean.FALSE.equals(pagamentoComercial) ? null : new NotaTotalFilter(TipoCarteira.COMERCIAL, saldoComercial, pagamentoComercial);

        if (ObjectUtils.isEmpty(pessoal) && ObjectUtils.isEmpty(comercial)) {
            throw new BadRequestException("Informe ao menos um tipo de nota para realizar a busca.");
        }

        NotaTotal notaTotalPessoal = null;
        NotaTotal notaTotalComercial = null;

        for (NotaTotalFilter filter : Arrays.asList(pessoal, comercial)) {
            var usuario = usuarioService.getByEmail(email);
            if (ObjectUtils.isEmpty(filter) || filter.tipoCarteira().equals(TipoCarteira.COMERCIAL) && usuario.getCarteiras().size() == 1) {
                continue;
            }

            var totalSaldo = new AtomicReference<>(0.0);
            var totalPagamento = new AtomicReference<>(0.0);

            var contas = filtrarContasPorTipoCarteira(usuario, filter.tipoCarteira());

            if (filter.saldo()) {
                contas.forEach(conta -> totalSaldo.updateAndGet(v -> v + conta.getValorTotal()));
            }
            if (filter.pagamento()) {
                contas.forEach(conta -> conta.getNotas().stream()
                        .filter(nota -> nota.getTipo().equals(TipoNota.PAGAMENTO))
                        .filter(nota -> nota.getData().compareTo(LocalDate.now()) >= 0)
                        .forEach(nota -> totalPagamento.updateAndGet(v -> v + nota.getValor()))
                );
            }
            switch (filter.tipoCarteira()) {
                case PESSOAL ->
                        notaTotalPessoal = new NotaTotal(TipoCarteira.PESSOAL, totalSaldo.get(), totalPagamento.get());
                case COMERCIAL ->
                        notaTotalComercial = new NotaTotal(TipoCarteira.COMERCIAL, totalSaldo.get(), totalPagamento.get());
            }
        }
        return new CalculaResultadoDto(notaTotalPessoal, notaTotalComercial);
    }
    public boolean convertToFalse(Boolean campo){
        return null != campo && campo;
    }

    public List<Nota> findByTipoNota(String email, TipoCarteira tipoCarteira, TipoNota tipoNota) {
        var usuario = usuarioService.getByEmail(email);
        var contas = filtrarContasPorTipoCarteira(usuario, tipoCarteira);
        var notas = new ArrayList<Nota>();
        contas.forEach(conta -> conta.getNotas().stream().filter(nota -> nota.getTipo().equals(tipoNota)).forEach(notas::add));
        return notas;
    }

    public List<Conta> filtrarContasPorTipoCarteira(Usuario usuario, TipoCarteira tipo){
        return usuario.getCarteiras().stream()
                .filter(carteira -> carteira.getTipo().equals(tipo))
                .toList().get(0).getContas();
    }
}
