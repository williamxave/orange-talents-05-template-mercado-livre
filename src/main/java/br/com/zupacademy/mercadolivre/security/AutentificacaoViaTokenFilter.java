package br.com.zupacademy.mercadolivre.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import br.com.zupacademy.mercadolivre.model.Usuario;
import br.com.zupacademy.mercadolivre.repositories.UsuarioRepository;

public class AutentificacaoViaTokenFilter extends OncePerRequestFilter{

    private GeradorDeToken geradorDeToken;

    private UsuarioRepository  usuarioRepository;

    public AutentificacaoViaTokenFilter(GeradorDeToken geradorDeToken, UsuarioRepository usuarioRepository) {
        this.geradorDeToken = geradorDeToken;
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String token = recuperaToken(request);
        boolean valido = geradorDeToken.isTokenValid(token);
        if(valido){
            autenticarCliente(token);
        }
        filterChain.doFilter(request, response);
    }

    private String recuperaToken(HttpServletRequest request){
        String pegarTokenDoHeader = request.getHeader("Authorization");
        if(pegarTokenDoHeader == null || pegarTokenDoHeader.isEmpty() || !pegarTokenDoHeader.startsWith("Bearer ")){
            return null;
        }
        return pegarTokenDoHeader.substring(7, pegarTokenDoHeader.length());
    } 

    private void autenticarCliente(String token){

        Long idUsuario = geradorDeToken.getIdUsuario(token);

        Usuario usuario = usuarioRepository.findById(idUsuario).get();

        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(usuario,null, usuario.getAuthorities());

        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
    }
}
