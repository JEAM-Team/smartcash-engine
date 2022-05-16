package com.smartcash.engine.services;

import com.smartcash.engine.exceptions.NotFoundException;
import com.smartcash.engine.exceptions.carteira.BadRequestException;
import com.smartcash.engine.models.domain.Carteira;
import com.smartcash.engine.models.domain.Conta;
import com.smartcash.engine.models.domain.Usuario;
import com.smartcash.engine.models.enums.TipoCarteira;
import com.smartcash.engine.repository.CarteiraRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class CarteiraService {

    @Autowired
    private CarteiraRepository repository;

    public Carteira save(TipoCarteira tipo, Conta conta){
        var carteira = Carteira.builder()
                .tipo(tipo)
                .contas(Collections.singletonList(conta))
                .build();
        return create(carteira);
    }

    public Carteira create(Carteira carteira) {
        return repository.save(carteira);
    }

    public List<Carteira> findAll() {
        return repository.findAll();
    }

    public Carteira findById(Long id){
        return repository.findById(id).orElseThrow(() -> new NotFoundException("Não pode ser encontrado a carteira com id: " + id));
    }
}
