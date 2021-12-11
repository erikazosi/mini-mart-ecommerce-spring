package edu.miu.waa.minimartecommerce.exceptionHandling.exceptions;

public class AuthenticationException extends RuntimeException {
    private static final long serialVersionUID = -4866090712366577361L;

    public AuthenticationException(String message, Throwable cause){super(message,cause);}
}
