package edu.miu.waa.minimartecommerce.exceptionHandling.exceptions;

public class ExtensionMismatchException extends RuntimeException {
    public ExtensionMismatchException(){
        super("Extension doesn't match");
    }
}
