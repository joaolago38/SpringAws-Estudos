package br.com.spring.estudo.estudos.exception;

public class NegociosException extends RuntimeException {
    public NegociosException(String message){
        super(message);
    }
}
