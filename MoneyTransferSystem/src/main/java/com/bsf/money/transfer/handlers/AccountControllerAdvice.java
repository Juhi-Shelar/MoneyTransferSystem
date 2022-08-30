package com.bsf.money.transfer.handlers;

import com.bsf.money.transfer.dao.AccountDao;
import com.bsf.money.transfer.exception.InvalidBalanceException;
import com.bsf.money.transfer.responses.ErrorResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.swing.text.html.parser.Entity;

@ControllerAdvice
public class AccountControllerAdvice {
    private final Logger logger = LoggerFactory.getLogger(AccountControllerAdvice.class);

    @ExceptionHandler(value = {IllegalArgumentException.class})
    public ResponseEntity<ErrorResponse> handleIllegalArgumentException(
            IllegalArgumentException exception) {
        logger.error("Illegal argument exception. Error message = " + exception.getMessage(), exception);

        ErrorResponse response = ErrorResponse.builder()
                .code(HttpStatus.BAD_REQUEST.toString())
                .title(exception.getMessage())
                .build();

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = {InvalidBalanceException.class})
    public ResponseEntity<ErrorResponse> handleInvalidBalanceException(
            InvalidBalanceException exception) {
        logger.error("Invalid Balance exception. Error message = " + exception.getMessage(), exception);

        ErrorResponse response = ErrorResponse.builder()
                .code(HttpStatus.BAD_REQUEST.toString())
                .title(exception.getMessage())
                .build();

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = {Exception.class})
    public ResponseEntity<ErrorResponse> handleInternalServerException(
            Exception exception) {
        logger.error("Internal Server Error. Error message = " + exception.getMessage(), exception);

        ErrorResponse response = ErrorResponse.builder()
                .code(HttpStatus.INTERNAL_SERVER_ERROR.toString())
                .title(exception.getMessage())
                .build();

        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
