package br.com.gerenciamentohoteis.gerenciador.Exception.exceptions;

public class UserAlreadyCreatedException extends RuntimeException {
    public UserAlreadyCreatedException(String message) {
        super(message);
    }
}
