package com.smartcash.engine.models.data;

import com.smartcash.engine.models.domain.Usuario;
import lombok.AllArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Optional;

@AllArgsConstructor
public class UsuarioDetails implements UserDetails {

    private Optional<Usuario> usuario;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return usuario.map(Usuario::getSenha).orElse(null);
    }

    @Override
    public String getUsername() {
        return usuario.map(Usuario::getEmail).orElse(null);
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
