package de.telran.bank.controller.advice;

public class ResponseException extends Exception{
    public ResponseException(String message) {
        super(message);
    }
}