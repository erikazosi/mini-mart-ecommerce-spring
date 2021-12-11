package edu.miu.waa.minimartecommerce.exceptionHandling.exceptions;

public class DuplicateUserNameException extends RuntimeException {
    public DuplicateUserNameException(){super("Account with given username already exist. Cannot create account");}
}
