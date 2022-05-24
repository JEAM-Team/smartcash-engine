package com.smartcash.engine.services;

import br.com.caelum.stella.validation.CPFValidator;
import com.google.gson.Gson;
import com.smartcash.engine.exceptions.usuario.CamposInvalidosException;
import com.smartcash.engine.exceptions.usuario.EmailDuplicadoException;
import com.smartcash.engine.models.data.UsuarioDetails;
import com.smartcash.engine.models.domain.Carteira;
import com.smartcash.engine.models.domain.Usuario;
import com.smartcash.engine.models.dtos.ContaComercialPost;
import com.smartcash.engine.models.dtos.UsuarioDTO;
import com.smartcash.engine.models.enums.TipoCarteira;
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
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

@Service
@Slf4j
public class UsuarioService implements UserDetailsService {

    @Autowired
    private CarteiraService carteiraService;

    @Autowired
    private ContaService contaService;

    @Autowired
    private MessageSource ms;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PasswordEncoder encoder;

    @Autowired
    private Gson gson;

    private final CPFValidator cpfValidator = new CPFValidator();

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        var usuario = usuarioRepository.findByEmail(email);
        if (usuario.isEmpty()) {
            throw new UsernameNotFoundException("Usuário [" + email + "] não encontrado");
        }
        return new UsuarioDetails(usuario);
    }

    public void save(UsuarioDTO dto) {
        usuarioRepository.findByEmail(dto.email()).ifPresent(u -> {
            throw new EmailDuplicadoException(ms.getMessage("exception.usuario.email-duplicado", null, Locale.getDefault()));
        });
        validacaoCadastro(dto);
        var usuario = new Usuario();
        BeanUtils.copyProperties(dto, usuario, "id, carteira, senha");
        var carteira = carteiraService.save(TipoCarteira.PESSOAL);
        contaService.createDefault(carteira);
        usuario.setCarteiras(Collections.singletonList(carteira));
        usuario.setSenha(encoder.encode(dto.senha()));
        usuarioRepository.save(usuario);
    }

    public Usuario getByEmail(String email) {
        return usuarioRepository.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException("Usuário [" + email + "] não encontrado"));
    }

    public void update(String email, UsuarioDTO dto) {
        var usuario = getByEmail(email);
        BeanUtils.copyProperties(dto, usuario, "id, carteira, cpf");
        usuarioRepository.save(usuario);
    }

    public void update(ContaComercialPost contaComercial) {
        var usuario = getByEmail(contaComercial.email());
        var carteira = carteiraService.save(TipoCarteira.COMERCIAL);
        contaService.createDefault(carteira);
        usuario.getCarteiras().add(carteira);
        usuarioRepository.save(usuario);
    }

    public void validacaoCadastro(UsuarioDTO dto) {
        boolean isBlank = dto.nome().isBlank() || dto.sobrenome().isBlank() || dto.email().isBlank() || dto.senha().isBlank() || dto.cpf().isBlank();
        if (isBlank)
            throw new CamposInvalidosException("Existem campos sem preenchimento.");
        cpfValidator.assertValid(dto.cpf());
    }

    public List<Carteira> findContasByEmail(String email, TipoCarteira tipo) {
        var usuario = getByEmail(email);
        return null != tipo ? usuario.getCarteiras().stream().filter(carteira -> carteira.getTipo().equals(tipo)).collect(Collectors.toList()) : usuario.getCarteiras();
    }
}
