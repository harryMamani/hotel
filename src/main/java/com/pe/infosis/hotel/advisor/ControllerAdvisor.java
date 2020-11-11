package com.pe.infosis.hotel.advisor;

import java.util.List;

import javax.validation.ConstraintViolationException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

import com.pe.infosis.hotel.bean.ErrorValidation;
import com.pe.infosis.hotel.bean.Status;
import com.pe.infosis.hotel.bean.StatusValidation;
import com.pe.infosis.hotel.enumeration.ErrorCodeEnum;
import com.pe.infosis.hotel.exception.HotelException;

@RestControllerAdvice
public class ControllerAdvisor {

	private static final Logger log = LogManager.getLogger(ControllerAdvisor.class);

	@ExceptionHandler({ Exception.class })
	public ResponseEntity<Status> handlerException(Exception ex) {
		log.error(ex.getMessage(), ex);
		return new ResponseEntity<>(ErrorCodeEnum.FAIL.status(), HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler({ AccessDeniedException.class })
	public ResponseEntity<Status> handlerException(AccessDeniedException ex) {
		log.error(ex.getMessage(), ex);
		return new ResponseEntity<>(ErrorCodeEnum.ACCESS_DENIED.status(), HttpStatus.FORBIDDEN);
	}
	
	@ExceptionHandler({ ConstraintViolationException.class })
	public ResponseEntity<Status> handlerException(ConstraintViolationException ex) {
		Status status = ErrorCodeEnum.VALIDACION.status();
		status.setMessage(ex.getMessage());
		log.error(ex.getMessage(), ex);
		return new ResponseEntity<>(status, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler({ MethodArgumentNotValidException.class})
	public ResponseEntity<StatusValidation> handlerException(MethodArgumentNotValidException ex) {
		StatusValidation status = new StatusValidation();
		status.setCode("12");
		List<ErrorValidation> errors = status.getErrors();
		for (final FieldError error : ex.getBindingResult().getFieldErrors()) {
			errors.add(new ErrorValidation(error.getField(), error.getDefaultMessage()));
		}
		for (final ObjectError error : ex.getBindingResult().getGlobalErrors()) {
			errors.add(new ErrorValidation(error.getObjectName(), error.getDefaultMessage()));
		}
		
		log.error(ex.getMessage(), ex);
		return new ResponseEntity<>(status, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler({ HotelException.class })
	public ResponseEntity<Status> handlerException(HotelException ex) {
		log.error("Requets: [" + ex.getRequets().toString() + "]");
		log.error(ex.getMessage(), ex);
		return new ResponseEntity<>(ex.getErrorCode().status(), HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(NoHandlerFoundException.class)
	@ResponseStatus(value = HttpStatus.NOT_FOUND)
	public ResponseEntity<Status> handlerResponse(Exception ex) {
		log.error(ex.getMessage(), ex);
		return new ResponseEntity<>(ErrorCodeEnum.RESOURCE_NOT_EXIST.status(), HttpStatus.NOT_FOUND);
	}
}
