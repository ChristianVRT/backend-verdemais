package com.example.backend_verdemais.security;

import java.util.Collection;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

public class UserAuthentication extends UsernamePasswordAuthenticationToken {
    public UserAuthentication(String principal, Collection<? extends GrantedAuthority> authorities) {
        super(principal, null, authorities);
    }
}