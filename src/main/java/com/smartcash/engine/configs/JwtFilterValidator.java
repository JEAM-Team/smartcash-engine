package com.smartcash.engine.configs;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class JwtFilterValidator extends BasicAuthenticationFilter {

    private static final ResourceBundle RB = ResourceBundle.getBundle("application");

    public JwtFilterValidator(AuthenticationManager authenticationManager) {
        super(authenticationManager);
    }


    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain chain) throws IOException, ServletException {

        var authCode = request.getHeader(RB.getString("security.auth.name"));

        if (authCode == null) {
            chain.doFilter(request, response);
            return;
        }

        if (!authCode.startsWith(RB.getString("security.auth.prefix"))) {
            chain.doFilter(request, response);
            return;
        }

        var token = authCode.replace(RB.getString("security.auth.prefix"), "");
        var authenticationToken = getAuthenticationToken(token);

        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        chain.doFilter(request, response);
    }

    private UsernamePasswordAuthenticationToken getAuthenticationToken(String token) {

        var usuario = JWT.require(Algorithm.HMAC512(RB.getString("security.auth.client-secret")))
                .build()
                .verify(token)
                .getSubject();

        if (usuario == null) {
            return null;
        }

        return new UsernamePasswordAuthenticationToken(usuario,null, new ArrayList<>());
    }
}
