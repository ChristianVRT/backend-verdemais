package com.example.backend_verdemais.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.example.backend_verdemais.entities.Usuario;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class TokenService {
    @Value("${api.security.token.secret}")
    private String secret;

    public String generateToken(Usuario usuario) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);

            String token = JWT.create()
                    .withIssuer("backend-verdemais")
                    .withSubject(usuario.getEmail())
                    .withClaim("role", usuario.getRole())
                    .withExpiresAt(this.generateExpirationDate())
                    .sign(algorithm);
            return token;
        } catch (JWTCreationException exception) {
            throw new RuntimeException("Erro ao tentar gerar token.");
        }
    }

    public DecodedJWT getDecodedJWT(String token) {
        Algorithm algorithm = Algorithm.HMAC256(secret);
        return JWT.require(algorithm)
                .withIssuer("backend-verdemais")
                .build()
                .verify(token);
    }

    public String validateToken(String token){
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            return JWT.require(algorithm)
                    .withIssuer("login-auth-api")
                    .build()
                    .verify(token)
                    .getSubject();
        } catch (JWTVerificationException exception) {
            return null;
        }
    }

    public String extrairRole(String token){
        try {
            DecodedJWT decodedJWT = getDecodedJWT(token);
            return decodedJWT.getClaim("role").asString();
        } catch (JWTVerificationException exception) {
            throw new RuntimeException("Erro ao tentar gerar token.");
        }
    }

    public String extrairEmail(String token){
        DecodedJWT decodedJWT = getDecodedJWT(token);
        return decodedJWT.getClaim("email").asString();
    }

    public boolean isAdmin(String token){
        DecodedJWT decodedJWT = getDecodedJWT(token);
        return decodedJWT.getClaim("role").asString().equals("admin");
    }

    private Instant generateExpirationDate(){
        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00"));
    }
}
