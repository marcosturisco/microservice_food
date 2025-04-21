package br.com.alurafood.avaliacao.avaliacao.exception;

public class RecebimentoMensagemException extends RuntimeException {

    public RecebimentoMensagemException(String message) {
        super(message);
    }

    public RecebimentoMensagemException(String message, Throwable cause) {
        super(message, cause);
    }
}
