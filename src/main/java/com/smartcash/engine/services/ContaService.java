package com.smartcash.engine.services;

import com.smartcash.engine.exceptions.NotFoundException;
import com.smartcash.engine.exceptions.carteira.BadRequestException;
import com.smartcash.engine.models.domain.Carteira;
import com.smartcash.engine.models.domain.Conta;
import com.smartcash.engine.models.dtos.ContaDTO;
import com.smartcash.engine.models.enums.TipoCarteira;
import com.smartcash.engine.models.enums.TipoConta;
import com.smartcash.engine.repository.ContaRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Optional;

@Service
public class ContaService {

    @Autowired
    private ContaRepository repository;

    @Autowired
    private CarteiraService carteiraService;


    public void create(ContaDTO dto) {
        if (null == dto.carteiraId())
            throw new BadRequestException("Preencha o id da Carteira desejada");
        var carteira = carteiraService.findById(dto.carteiraId());
        var conta = new Conta();
        BeanUtils.copyProperties(dto, conta, "carteiraId");
        conta.setCarteira(carteira);
        repository.save(conta);
    }

    public Conta createDefault() {
        var conta = Conta.builder().tipo(TipoConta.DN).nome("Conta Padrão").valorTotal(0.0).build();
        return repository.save(conta);
    }

    public List<Conta> findAll() {
        return repository.findAll();
    }

    public Conta findById(Long id) {
        return repository.findById(id).orElseThrow(() -> new NotFoundException("Não foi possível encontrar a conta com ID: " + id));
    }

    public List<Conta> findByCarteiraId(Long carteiraId) {
        return repository.findByCarteiraId(carteiraId);
    }

    public void update(Long id, ContaDTO dto) {
        var conta = repository.findById(id);
    }

    public void delete(Long id) {
        var conta = findById(id);
        repository.delete(conta);
    }
}
