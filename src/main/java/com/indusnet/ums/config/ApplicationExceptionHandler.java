package com.indusnet.ums.config;

import com.indusnet.ums.common.MessageTypeConst;
import com.indusnet.ums.exception.*;
import com.indusnet.ums.common.LoggingResponseModel;
import com.indusnet.ums.common.ResponseModel;
import com.google.gson.Gson;
import com.mongodb.MongoQueryException;

import jakarta.validation.ConstraintViolationException;
import jakarta.validation.UnexpectedTypeException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@RestControllerAdvice
@Slf4j
public class ApplicationExceptionHandler {

    @Autowired
    Gson gson;
       
      
    
    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    @ExceptionHandler(Exception.class)
    public ResponseModel handleGeneralException(Exception ex) {

        String msgEn=ex.getMessage();        
        String msgFr=null;
        String pat = ".*@##@{1}.*";
        boolean matches = Pattern.matches(pat, ex.getMessage());

        if(matches){
            msgEn = ex.getMessage().split("@##@")[0];
            msgFr = ex.getMessage().split("@##@")[1];
        }
        msgEn =  msgEn.replace("JSON parse error: ", "");
        msgEn = msgEn.replace("java.lang.NumberFormatException: For input string:","Invalid integer value");
        msgEn = msgEn.replace("java.lang.NumberFormatException: empty String","Invalid integer value");
        
        if ("Invalid integer value".equals(msgEn)) {        	
        	 msgFr = "Valeur entière non valide";
        }
        
        
        ResponseModel errorResponse=null;

        errorResponse = ResponseModel.builder()
                .messageEn(msgEn)
                .messageFr(msgFr)
                .messageTypeId(MessageTypeConst.ERROR.getMessage())
                .statusCode(HttpStatus.UNPROCESSABLE_ENTITY)
                .build();

        log.error(gson.toJson(LoggingResponseModel.builder()
                .message(msgEn)
                .messageTypeId(MessageTypeConst.ERROR)
                .statusCode(HttpStatus.UNPROCESSABLE_ENTITY)
                .build()));

        return errorResponse;
    }

    
    
    
    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    @ExceptionHandler(UnprocessableException.class)
    public ResponseModel handleUnprocessableException(UnprocessableException ex) {
        String msgEn=ex.getMessage();        
        String msgFr=null;
        String pat = ".*@##@{1}.*";
        boolean matches = Pattern.matches(pat, ex.getMessage());

        if(matches){
            msgEn= ex.getMessage().split("@##@")[0];
            msgFr= ex.getMessage().split("@##@")[1];
        }

        ResponseModel errorResponse=null;

        errorResponse = ResponseModel.builder()
                .messageEn(msgEn)
                .messageFr(msgFr)
                .messageTypeId(MessageTypeConst.ERROR.getMessage())
                .statusCode(HttpStatus.UNPROCESSABLE_ENTITY)
                .build();
        log.error(gson.toJson(LoggingResponseModel.builder()
                .message(msgEn)
                .messageTypeId(MessageTypeConst.ERROR)
                .statusCode(HttpStatus.UNPROCESSABLE_ENTITY)
                .build()));
        return errorResponse;
    }
    
    
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseModel handleInvalidConstraint(ConstraintViolationException ex) {
        List<String> errorsEn = ex.getConstraintViolations()
                .stream()
                .map(cv -> parseConstraintViolationMessage(cv.getMessage(), 0))
                .collect(Collectors.toList());

        List<String> errorsFr = ex.getConstraintViolations()
                .stream()
                .map(cv -> parseConstraintViolationMessage(cv.getMessage(), 1))
                .collect(Collectors.toList());

        String errorStrEn = String.join("@##@ ", errorsEn);
        String errorStrFr = String.join("@##@ ", errorsFr);

        String msg = ex.getMessage();

        log.error(gson.toJson(LoggingResponseModel.builder()
                .message(msg)
                .messageTypeId(MessageTypeConst.ERROR)
                .statusCode(HttpStatus.BAD_REQUEST)
                .build()));

        return ResponseModel.builder()
                .messageEn(errorStrEn)
                .messageFr(errorStrFr)
                .messageTypeId(MessageTypeConst.ERROR.getMessage())
                .statusCode(HttpStatus.BAD_REQUEST)
                .build();
    }

    private String parseConstraintViolationMessage(String message, int index) {
        String[] parts = message.split("@##@");
        return (parts.length > index) ? parts[index] : message;
    }
  

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseModel handleInvalidArgument(IllegalArgumentException ex) {
    	
    	String msgEn=ex.getMessage();    	
        String msgFr=null;
        String pat = ".*@##@{1}.*";
        boolean matches = Pattern.matches(pat, ex.getMessage());

        if(matches){
            msgEn= ex.getMessage().split("@##@")[0];
            msgFr= ex.getMessage().split("@##@")[1];
        }
                
        ResponseModel errorResponse=null;

        errorResponse = ResponseModel.builder()
                .messageEn(msgEn)
                .messageFr(msgFr)
                .messageTypeId(MessageTypeConst.ERROR.getMessage())
                .statusCode(HttpStatus.BAD_REQUEST)
                .build();

        log.error(gson.toJson(LoggingResponseModel.builder()
                .message(msgEn)
                .messageTypeId(MessageTypeConst.ERROR)
                .statusCode(HttpStatus.BAD_REQUEST)
                .build()));

        return errorResponse;
    }

    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    @ExceptionHandler(MongoQueryException.class)
    public ResponseModel handleMongoException(MongoQueryException ex) {
        String msg= ex.getMessage();
        ResponseModel errorResponse=null;

        errorResponse = ResponseModel.builder()
                .messageEn(msg)
                .messageFr(msg)
                .messageTypeId(MessageTypeConst.ERROR.getMessage())
                .statusCode(HttpStatus.UNPROCESSABLE_ENTITY)
                .build();

        log.error(gson.toJson(LoggingResponseModel.builder()
                .message(msg)
                .messageTypeId(MessageTypeConst.ERROR)
                .statusCode(HttpStatus.UNPROCESSABLE_ENTITY)
                .build()));

        return errorResponse;
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(NullPointerException.class)
    public ResponseModel handleNullException(NullPointerException ex) {
    	
    	String msgEn=ex.getMessage();
        String msgFr=null;
        String pat = ".*@##@{1}.*";
        boolean matches = Pattern.matches(pat, ex.getMessage());

        if(matches){
            msgEn= ex.getMessage().split("@##@")[0];
            msgFr= ex.getMessage().split("@##@")[1];
        }

        
        ResponseModel errorResponse=null;

        errorResponse = ResponseModel.builder()
                .messageEn(msgEn)
                .messageFr(msgFr)
                .messageTypeId(MessageTypeConst.ERROR.getMessage())
                .statusCode(HttpStatus.BAD_REQUEST)
                .build();

        log.error(gson.toJson(LoggingResponseModel.builder()
                .message(msgEn)
                .messageTypeId(MessageTypeConst.ERROR)
                .statusCode(HttpStatus.UNPROCESSABLE_ENTITY)
                .build()));

        return errorResponse;
    }

    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    @ExceptionHandler(UnexpectedTypeException.class)
    public ResponseModel handleUnExceptException(UnexpectedTypeException ex) {

        String msg= ex.getMessage();
        ResponseModel errorResponse=null;

        errorResponse = ResponseModel.builder()
                .messageEn(msg)
                .messageFr(msg)
                .messageTypeId(MessageTypeConst.ERROR.getMessage())
                .statusCode(HttpStatus.UNPROCESSABLE_ENTITY)
                .build();

        log.error(gson.toJson(LoggingResponseModel.builder()
                .message(msg)
                .messageTypeId(MessageTypeConst.ERROR)
                .statusCode(HttpStatus.UNPROCESSABLE_ENTITY)
                .build()));

        return errorResponse;
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(IllegalStateException.class)
    public ResponseModel handleInvalidIllegalState(IllegalStateException ex) {
    	
    	String msgEn=ex.getMessage();
        String msgFr=null;
        String pat = ".*@##@{1}.*";
        boolean matches = Pattern.matches(pat, ex.getMessage());

        if(matches){
            msgEn= ex.getMessage().split("@##@")[0];
            msgFr= ex.getMessage().split("@##@")[1];
        }        
        
        ResponseModel errorResponse=null;

        errorResponse = ResponseModel.builder()
                .messageEn(msgEn)
                .messageFr(msgFr)
                .messageTypeId(MessageTypeConst.ERROR.getMessage())
                .statusCode(HttpStatus.BAD_REQUEST)
                .build();

        log.error(gson.toJson(LoggingResponseModel.builder()
                .message(msgEn)
                .messageTypeId(MessageTypeConst.ERROR)
                .statusCode(HttpStatus.BAD_REQUEST)
                .build()));

        return errorResponse;
    }

    @ResponseStatus(HttpStatus.OK)
    @ExceptionHandler(CustomFoundException.class)
    public ResponseModel handleCustomFoundException(CustomFoundException ex) {

        String msgEn=ex.getMessage();
        String msgFr=null;
        String pat = ".*@##@{1}.*";
        boolean matches = Pattern.matches(pat, ex.getMessage());

        if(matches){
            msgEn= ex.getMessage().split("@##@")[0];
            msgFr= ex.getMessage().split("@##@")[1];
        }

        ResponseModel response=null;

        response = ResponseModel.builder()
                .messageEn(msgEn)
                .messageFr(msgFr)
                .messageTypeId(MessageTypeConst.SUCCESS.getMessage())
                .statusCode(HttpStatus.OK)
                .build();
        return response;
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(CustomNotFoundException.class)
    public ResponseModel handleCustomNotFoundException(CustomNotFoundException ex) {

        String msgEn=ex.getMessage();
        String msgFr=null;
        String pat = ".*@##@{1}.*";
        boolean matches = Pattern.matches(pat, ex.getMessage());

        if(matches){
            msgEn= ex.getMessage().split("@##@")[0];
            msgFr= ex.getMessage().split("@##@")[1];
        }

        ResponseModel errorResponse=null;

        errorResponse = ResponseModel.builder()
                .messageEn(msgEn)
                .messageFr(msgFr)
                .messageTypeId(MessageTypeConst.ERROR.getMessage())
                .statusCode(HttpStatus.NOT_FOUND)
                .build();

        log.error(gson.toJson(LoggingResponseModel.builder()
                .message(msgEn)
                .messageTypeId(MessageTypeConst.OTHERS)
                .statusCode(HttpStatus.NOT_FOUND)
                .build()));
        return errorResponse;

    }

    

    @ExceptionHandler(UpdateValidationException.class)
    public ResponseEntity<ResponseModel> validationErrorException(UpdateValidationException ex) {

        String msgEn=ex.getMessage();
        String msgFr=null;
        String pat = ".*@##@{1}.*";
        boolean matches = Pattern.matches(pat, ex.getMessage());

        if(matches){
            msgEn= ex.getMessage().split("@##@")[0];
            msgFr= ex.getMessage().split("@##@")[1];
        }

        ResponseModel message = new ResponseModel(
                HttpStatus.BAD_REQUEST,
                "0",
                msgEn,
                msgFr,
                0);

        return new ResponseEntity<ResponseModel>(message, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(DuplicateKeyException.class)
    public ResponseEntity<ResponseModel> validateDuplicateKey(DuplicateKeyException ex) {

        String msgEn=ex.getMessage();
        String msgFr=null;
        String pat = ".*@##@{1}.*";
        boolean matches = Pattern.matches(pat, ex.getMessage());

        if(matches){
            msgEn= ex.getMessage().split("@##@")[0];
            msgFr= ex.getMessage().split("@##@")[1];
        }

        ResponseModel message = new ResponseModel(
                HttpStatus.BAD_REQUEST,
                "0",
                msgEn,
                msgFr,
                0);

        return new ResponseEntity<ResponseModel>(message, HttpStatus.BAD_REQUEST);
    }

   

}
