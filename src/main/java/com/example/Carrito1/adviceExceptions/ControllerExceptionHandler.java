package com.example.Carrito1.adviceExceptions;


import com.example.Carrito1.exceptions.IdNoEncontradoException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class ControllerExceptionHandler {

// el siguiente método manejará los mensajes de error en la validación de los input de los controllers
// basado en la respuesta de "MethodArgumentNotValidException" que se genera en la validación

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler (MethodArgumentNotValidException.class)
    public Map<String, String> manejoDeArgumentosInvalidos(MethodArgumentNotValidException ex){

        Map<String, String> errorMap = new HashMap<>();  //obtengo la el mapa de los errores capturados

        ex.getBindingResult().getFieldErrors().forEach(error ->{
            errorMap.put(error.getField(), error.getDefaultMessage());
        });
        return errorMap;
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler (IdNoEncontradoException.class)
    public Map<String, String> manejoIdNoEncontrado(IdNoEncontradoException ex){
        Map<String, String> errorMap = new HashMap<>();
        errorMap.put("errorMessage", ex.getMessage());

        return errorMap;

    }

}
