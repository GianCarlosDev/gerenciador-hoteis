package br.com.gerenciamentohoteis.gerenciador.Service;

import br.com.gerenciamentohoteis.gerenciador.Dto.Request.AuthenticationDTO;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {

    private final AuthenticationManager authenticationManager;

    public AuthenticationService(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    public AuthenticationDTO authentication(AuthenticationDTO data){
        UsernamePasswordAuthenticationToken token =
                new UsernamePasswordAuthenticationToken(data.getEmail(),data.getPassword());
        authenticationManager.authenticate(token);
        return data;
    }

}
