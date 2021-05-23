package com.springrestapi.exceptionhandler;

//import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.springrestapi.domain.exception.EntityNotFoundException;
import com.springrestapi.domain.exception.ExceptionBusiness;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@ControllerAdvice
public class ExceptionHandler extends ResponseEntityExceptionHandler {
	
	private MessageSource messageSource;

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		List<Problem.Field> fields = new ArrayList<>();
		
		for(ObjectError error: ex.getBindingResult().getAllErrors()) {
			String name = ((FieldError) error).getField();
//			String mensage = error.getDefaultMessage();
			String mensage = messageSource.getMessage(error, LocaleContextHolder.getLocale());
			
			fields.add(new Problem.Field(name, mensage));
		}
		
		Problem problem = new Problem();
		problem.setStatus(status.value());
		problem.setDataHour(OffsetDateTime.now());
		problem.setTitle("Verifique os valores preenchidos nos campos e tente novamente!");
		problem.setFields(fields);

		return handleExceptionInternal(ex, problem, headers, status, request);
	}
	
	@org.springframework.web.bind.annotation.ExceptionHandler(EntityNotFoundException.class)
	public ResponseEntity<Object> handleEntityNotFoundException(EntityNotFoundException ex, WebRequest request) {
		HttpStatus status = HttpStatus.NOT_FOUND;
		
		Problem problem = new Problem();
		problem.setStatus(status.value());
		problem.setDataHour(OffsetDateTime.now());
		problem.setTitle(ex.getMessage());
		
		return handleExceptionInternal(ex, problem, new HttpHeaders(), status, request);
	}
	
	@org.springframework.web.bind.annotation.ExceptionHandler(ExceptionBusiness.class)
	public ResponseEntity<Object> handleBusiness(ExceptionBusiness ex, WebRequest request){
		HttpStatus status = HttpStatus.BAD_REQUEST;
		
		Problem problem = new Problem();
		problem.setStatus(status.value());
		problem.setDataHour(OffsetDateTime.now());
		problem.setTitle(ex.getMessage());
		
		return handleExceptionInternal(ex, problem, new HttpHeaders(), status, request);
	}
}
