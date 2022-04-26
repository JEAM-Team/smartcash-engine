package com.smartcash.engine.services;

import com.smartcash.engine.models.data.UsuarioDetails;
import com.smartcash.engine.models.domain.Usuario;
import com.smartcash.engine.repository.UsuarioRepository;
import com.smartcash.engine.services.contracts.UsuarioService;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@Log4j
public class UsuarioServiceImpl implements UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        var usuario = usuarioRepository.findByEmailIgnoreCase(email);
        if (usuario.isEmpty()) {
            throw new UsernameNotFoundException("Usuário [" + email + "] não encontrado");
        }

        return new UsuarioDetails(usuario);
    }
}
