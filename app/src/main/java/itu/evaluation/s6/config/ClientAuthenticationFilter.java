package itu.evaluation.s6.config;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;

import java.io.IOException;

public class ClientAuthenticationFilter extends AbstractAuthenticationProcessingFilter {
    public ClientAuthenticationFilter(AuthenticationManager authenticationManager) {
        super("/clients/authentication"); // 🔥 URL de traitement spécifique au client
        setAuthenticationManager(authenticationManager);
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException, IOException, ServletException {
        String phoneNumber = request.getParameter("phoneNumber"); // 🔥 Récupérer le téléphone du formulaire

        UsernamePasswordAuthenticationToken authRequest =
                new UsernamePasswordAuthenticationToken(phoneNumber, null);

        return getAuthenticationManager().authenticate(authRequest);
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response,
                                            FilterChain chain, Authentication authResult) throws IOException, ServletException {
        response.sendRedirect("/reservations"); // 🔥 Redirection après succès
    }

    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response,
                                              AuthenticationException failed) throws IOException, ServletException {
        response.sendRedirect("/login?error=true"); // 🔥 Redirection après échec
    }
}
