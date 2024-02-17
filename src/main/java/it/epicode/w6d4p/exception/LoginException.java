package it.epicode.w6d4p.exception;

public class LoginException extends RuntimeException{
    public LoginException(String message){
        super(message);
    }
}
