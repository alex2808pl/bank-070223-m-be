package de.telran.bank.controller.advice;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.support.WebExchangeBindException;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@ControllerAdvice
public class DefaultAdvice /*extends ResponseEntityExceptionHandler*/ {//унаследовались от обработчика-заготовки

    @ExceptionHandler(ResponseException.class)
    public ResponseEntity<ResponseDto> handleException(ResponseException e) {
        ResponseDto responseDto = new ResponseDto(e.getMessage(), e.getLocalizedMessage());

        return new ResponseEntity<>(responseDto, HttpStatus.resolve(400));
    }

//    @ExceptionHandler(value = { ResponseException.class, HttpClientErrorException.class })
//    public ResponseEntity<ResponseDto> handleException(Exception e ) {
//        ResponseDto responseDto = new ResponseDto(e.getMessage());
//
//        return new ResponseEntity<>(responseDto, HttpStatus.resolve(400));
//    }

//    @Override//переопределили метод родительского класса
//    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
//        Response response = new Response("Не правильный JSON",ex.getMessage());
//        return new ResponseEntity<>(response, status);
//    }

    @ExceptionHandler(MethodArgumentNotValidException.class) //WebExchangeBindException.class
    public ResponseEntity<Map<String, List<String>>> handleValidationErrors(MethodArgumentNotValidException ex) {
        List<String> errors = ex.getBindingResult().getFieldErrors()
                .stream().map(fe -> fe.getDefaultMessage()).collect(Collectors.toList());
        return new ResponseEntity<>(getErrorsMap(errors), new HttpHeaders(), HttpStatus.BAD_REQUEST);
    }

    private Map<String, List<String>> getErrorsMap(List<String> errors) {
        Map<String, List<String>> errorResponse = new HashMap<>();
        errorResponse.put("errors", errors);
        return errorResponse;
    }

//    @ResponseStatus(HttpStatus.BAD_REQUEST)
//    @ExceptionHandler(value = {MethodArgumentNotValidException.class})
//    protected ResponseEntity<ErrorResponse> handleMethodArgNotValidException(MethodArgumentNotValidException ex, Locale locale) {
//
//        String errorMessage = ex.getBindingResult()
//                .getFieldErrors()
//                .stream()
//                .map(fieldError -> fieldError.getField() + ": " + fieldError.getDefaultMessage())
//                .collect(Collectors.joining("; "));
//        ErrorResponse
//        // put errorMessage into ErrorResponse
//         return ResponseEntity<ErrorResponse>
//    }

//    @ExceptionHandler(MethodArgumentNotValidException.class) //value = { HttpClientErrorException.class }
//    public ResponseEntity<Response> handleValidationErrors(MethodArgumentNotValidException ex) {
//        Stream<String> fieldErrors = ex.getFieldErrors().stream()
//                .map(e -> String.format("%s: %s", e.getField(), e.getDefaultMessage()));
//
//        Stream<String> globalErrors = ex.getGlobalErrors().stream()
//                .map(e -> String.format("%s: %s", e.getObjectName(), e.getDefaultMessage()));
//
//        String error = Stream.concat(fieldErrors, globalErrors)
//                .collect(Collectors.joining("; "));
//
//        Response response = new Response(error);
//
//        return new ResponseEntity<>(response, new HttpHeaders(), HttpStatus.BAD_REQUEST);
//    }

}