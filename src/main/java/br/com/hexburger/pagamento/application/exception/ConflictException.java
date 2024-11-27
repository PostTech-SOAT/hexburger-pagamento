package br.com.hexburger.pagamento.application.exception;

public class ConflictException extends RuntimeException {

    public ConflictException(String message) {
        super(message, null, false, false);
    }
}
