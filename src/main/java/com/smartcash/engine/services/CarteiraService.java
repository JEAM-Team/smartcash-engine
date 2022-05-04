package com.smartcash.engine.services;

import com.smartcash.engine.models.domain.Carteira;
import com.smartcash.engine.models.enums.TipoCarteira;
import com.smartcash.engine.repository.CarteiraRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarteiraService {

    @Autowired
    private CarteiraRepository repository;

    public Carteira save(){
        var carteira = Carteira.builder()
                .tipo(TipoCarteira.PESSOAL)
                .build();
        return repository.save(carteira);
    }

    public void create(Carteira carteira) {
        repository.save(carteira);
    }

    public List<Carteira> findAll() {
        return repository.findAll();
    }
}
