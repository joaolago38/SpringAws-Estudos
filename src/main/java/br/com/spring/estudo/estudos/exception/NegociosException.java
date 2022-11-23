package br.com.spring.estudo.estudos.exception;

public class NegociosException extends RuntimeException {
    public NegociosException(String message){
        super(message);
    }

    public NegociosException(Integer id) {
        super("Could not find user with id " + id + ".");
    }
}
