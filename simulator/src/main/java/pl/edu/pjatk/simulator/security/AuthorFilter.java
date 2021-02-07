package pl.edu.pjatk.simulator.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public class AuthorFilter {
    public AuthorFilter(AuthenticationManager authenticationManager) {
        super(authenticationManager);
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain chain) throws IOException, ServletException {
        String header = request.getHeader(HEADER_STRING);
        if (header == null || !header.startsWith(TOKEN_PREFIX)) {
            chain.doFilter(request, response);
        } else {
            UserPassAuthen authentication = getAuthentication(request);

            SecurityContextHolder.getContext().setAuthentication(authentication);
            chain.doFilter(request, response);
        }
    }

    private UserPassAuthen getAuthentication(HttpServletRequest request) {
        var token = request.getHeader(HEADER_STRING);
        if (token != null) {
            DecodedJWT decoded = JWT.require(Algorithm.HMAC512(SECRET))
                    .build()
                    .verify(token.replace(TOKEN_PREFIX, ""));
            var user = decoded.getSubject();

            List<SimpleGrantedAuthority> authorities = decoded.getClaim("authorities").asList(SimpleGrantedAuthority.class);

            if (user != null) {
                return new UserPassAuthen(user, user, authorities);
            }
            return null;
        }

        return null;
    }
}