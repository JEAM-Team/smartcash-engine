package com.smartcash.engine.services;

import com.smartcash.engine.exceptions.usuario.EmailDuplicadoException;
import com.smartcash.engine.models.data.UsuarioDetails;
import com.smartcash.engine.models.domain.Usuario;
import com.smartcash.engine.models.dtos.UsuarioDTO;
import com.smartcash.engine.repository.UsuarioRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Locale;

@Service
@Slf4j
public class UsuarioService implements UserDetailsService {

    @Autowired
    private CarteiraService carteiraService;

    @Autowired
    private MessageSource ms;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PasswordEncoder encoder;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        var usuario = usuarioRepository.findByEmailIgnoreCase(email);
        if (usuario.isEmpty()) {
            throw new UsernameNotFoundException("Usuário [" + email + "] não encontrado");
        }

        return new UsuarioDetails(usuario);
    }

    public void save(UsuarioDTO dto) {
        usuarioRepository.findByEmailIgnoreCase(dto.email()).ifPresent(u -> {
            throw new EmailDuplicadoException(ms.getMessage("exception.usuario.email-duplicado", null, Locale.getDefault()));
        });
        var usuario = new Usuario();
        BeanUtils.copyProperties(dto, usuario, "id, carteira, senha");
        usuario.setCarteiras(Collections.singletonList(carteiraService.save()));
        usuario.setSenha(encoder.encode(dto.senha()));
        usuarioRepository.save(usuario);
    }
}
