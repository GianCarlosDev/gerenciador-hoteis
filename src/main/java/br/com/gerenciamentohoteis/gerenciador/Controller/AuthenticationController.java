package br.com.gerenciamentohoteis.gerenciador.Controller;

import br.com.gerenciamentohoteis.gerenciador.Dto.Request.AuthenticationDTO;
import br.com.gerenciamentohoteis.gerenciador.Dto.Request.CreateUserDTO;
import br.com.gerenciamentohoteis.gerenciador.Service.AuthenticationService;
import br.com.gerenciamentohoteis.gerenciador.Service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthenticationController{
    private final AuthenticationService authenticationService;
    private final UserService userService;

    public AuthenticationController(AuthenticationService authenticationService, UserService userService) {
        this.authenticationService = authenticationService;

        this.userService = userService;
    }

    @PostMapping("/login")
    public ResponseEntity<AuthenticationDTO> login(@RequestBody @Validated AuthenticationDTO data){
        authenticationService.authentication(data);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
    @PostMapping("/register")
    public ResponseEntity<CreateUserDTO> register(@RequestBody @Validated CreateUserDTO userDTO){
    userService.createUser(userDTO);
    return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
