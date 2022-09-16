package br.com.microservices.authenticate.filter;

import br.com.microservices.authenticate.service.AuthenticateService;
import br.com.microservices.authenticate.service.TokenService;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class TokenFilter extends OncePerRequestFilter {

    private TokenService tokenService;

    private AuthenticateService authenticateService;

    public TokenFilter(TokenService tokenService, AuthenticateService authenticateService) {
        this.tokenService = tokenService;
        this.authenticateService = authenticateService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String token = this.getToken(request);

        boolean isValid = tokenService.isValidToken(token);

        if(isValid){
            this.doAuth(token);
        }

        filterChain.doFilter(request, response);
    }

    private String getToken(HttpServletRequest request) {
        String token = request.getHeader("Authorization");
        if(token==null || token.isEmpty() || !token.startsWith("Bearer ")){
            return null;
        }
        return token.substring(7, token.length());
    }

    private void doAuth(String token) {
        Long idUser = tokenService.getUserId(token);
        UserDetails user = authenticateService.loadUserByUsername(String.valueOf(idUser));
        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }
}
