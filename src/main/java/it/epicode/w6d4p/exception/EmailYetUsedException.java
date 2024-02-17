package it.epicode.w6d4p.exception;

public class EmailYetUsedException extends RuntimeException{
    public EmailYetUsedException(String message){
        super(message);
    }
}
