package br.com.gerenciamentohoteis.gerenciador.Exception.exceptions;

public class HotelNotFoundException extends RuntimeException {
    public HotelNotFoundException(String message) {
        super(message);
    }
}
