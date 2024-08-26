package com.exception;

import java.time.LocalDateTime;
import java.util.stream.Collectors;

import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;


@RestControllerAdvice
public class ExceptionController {

	@ExceptionHandler(value = { HttpRequestMethodNotSupportedException.class})
	public ResponseEntity<Object> handleHttpRequestMethodNotSupported(
			HttpRequestMethodNotSupportedException exception) {
		String error = "Request method is " + exception.getMethod() + ", Supported method is "
				+ exception.getSupportedHttpMethods().stream().findFirst().get();
		return ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED).body(error);
	}
	
	@ExceptionHandler(value = {ConstraintViolationException.class})
	public ResponseEntity<Object> handleConstraintViolationException(ConstraintViolationException ex){
		return ResponseEntity.internalServerError().body(ex.getConstraintViolations().stream()
				.map(ConstraintViolation::getMessage).collect(Collectors.toList()));
	}

	@ExceptionHandler(value = { NotFoundException.class })
	public ResponseEntity<Object> handleCarNotFoundException(NotFoundException exception) {
		return ResponseEntity.badRequest()
				.body(ErrorDTO.builder().message(exception.getMessage()).time(LocalDateTime.now()).build());
	}

	@ExceptionHandler(value = {MethodArgumentNotValidException.class})
	public ResponseEntity<Object> handleMethodArgumentException(MethodArgumentNotValidException exception) {
		return ResponseEntity.badRequest().body(ErrorDTO
				.builder().message(exception.getFieldErrors().stream()
						.map(DefaultMessageSourceResolvable::getDefaultMessage).collect(Collectors.joining(",")))
				.time(LocalDateTime.now()).build());
	}
}
