package it.epicode.w6d4p.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ResponseExceptionHandler {

    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorResponse notFoundExceptionHandler(NotFoundException e){
        return new ErrorResponse(e.getMessage());
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorResponse ExceptionHandler(Exception e){
        return new ErrorResponse(e.getMessage());
    }

    @ExceptionHandler(BadRequestException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse badRequestExceptionHandler(BadRequestException e){
        return new ErrorResponse(e.getMessage());
    }

    @ExceptionHandler(EmailYetUsedException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse emailYetUsedExceptionHandler(EmailYetUsedException e){
        return new ErrorResponse(e.getMessage());
    }

    @ExceptionHandler(LoginException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse loginExceptionHandler(LoginException e){
        return new ErrorResponse(e.getMessage());
    }

    @ExceptionHandler(UnauthorizedException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ErrorResponse unauthorizedExceptionHandler(UnauthorizedException e){
        return new ErrorResponse(e.getMessage());
    }


}
