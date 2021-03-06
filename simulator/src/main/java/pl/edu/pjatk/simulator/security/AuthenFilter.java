package pl.edu.pjatk.simulator.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.tomcat.util.net.openssl.ciphers.Authentication;

import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collections;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;

import static pl.edu.pjatk.simulator.security.util.SecurityConstants.EXPIRATION_TIME;
import static pl.edu.pjatk.simulator.security.util.SecurityConstants.SECRET;

public class AuthenFilter extends UserPassAuthen {
    AuthenticationManager authenticationManager;

    public AuthenFilter(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;

        setFilterProcessesUrl("/login");
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        try {
            UserDetailsImp userDetails = new ObjectMapper().readValue(request.getInputStream(), UserDetailsImp.class);

            UserPassAuthen authentication = new UserPassAuthen(
                    userDetails.getUsername(),
                    userDetails.getPassword(),
                    Collections.emptyList());
            return authenticationManager.authenticate(authentication);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request,
                                            HttpServletResponse response,
                                            FilterChain chain,
                                            Authentication auth) throws IOException {

        var subject = auth.getPrincipal().toString();

        List<String> authorities = auth.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());

        var token = JWT.create()
                .withSubject(subject)
                .withExpiresAt(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .withClaim("authorities", authorities)
                .sign(Algorithm.HMAC512(SECRET));

        var payload = new LinkedHashMap<String, Object>();
        payload.put("username", subject);
        payload.put("token", token);

        response.getWriter().write(new ObjectMapper().writeValueAsString(payload));
        response.getWriter().flush();
    }
}