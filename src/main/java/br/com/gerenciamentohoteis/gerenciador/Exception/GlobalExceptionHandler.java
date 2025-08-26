package br.com.gerenciamentohoteis.gerenciador.Exception;

import br.com.gerenciamentohoteis.gerenciador.Exception.exceptions.HotelNotFoundException;
import br.com.gerenciamentohoteis.gerenciador.Exception.exceptions.QuartoNotFoundException;
import br.com.gerenciamentohoteis.gerenciador.Exception.exceptions.UserAlreadyCreatedException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(HotelNotFoundException.class)
    private ResponseEntity<String> hotelNaoExiste(HotelNotFoundException exception){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exception.getMessage());
    }
    @ExceptionHandler(QuartoNotFoundException.class)
    private ResponseEntity<String> quartoNaoExiste(QuartoNotFoundException exception){
     return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exception.getMessage());
    }
    @ExceptionHandler(UserAlreadyCreatedException.class)
    private ResponseEntity<String> usuarioJaCriado(UserAlreadyCreatedException exception){
        return ResponseEntity.status(HttpStatus.CONFLICT).body(exception.getMessage());
    }
}
