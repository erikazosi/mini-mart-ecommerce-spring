package edu.miu.waa.minimartecommerce.exceptionHandling.exceptions;

public class ProductException extends RuntimeException {
    public ProductException(String message, Throwable cause){
        super(message,cause);
    }
}
