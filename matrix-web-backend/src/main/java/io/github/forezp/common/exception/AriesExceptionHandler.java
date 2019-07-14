package io.github.forezp.common.exception;


import io.github.forezp.common.dto.RespDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;


@ControllerAdvice
@ResponseBody
public class AriesExceptionHandler {

    private final Logger logger= LoggerFactory.getLogger(AriesExceptionHandler.class);

    /**
     * 200 - taichi exception
     */
    @ExceptionHandler(AriesException.class)
    public ResponseEntity<RespDTO> handleException(Exception e) {
        RespDTO resp = new RespDTO();
        AriesException taiChiException = (AriesException) e;
        resp.code = taiChiException.getCode();
        resp.message = e.getMessage();
        //logger.info("user:{} | result: {} ", UserUtils.getCurrentPrinciple(), resp.toString());
        return new ResponseEntity(resp, HttpStatus.OK);
    }


    /**
     * 400 - Bad Request
     */
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<RespDTO> handleHttpMessageNotReadableException() {

        HttpStatus status = HttpStatus.BAD_REQUEST;
        return new ResponseEntity(getRespDTOByException(status), status);
    }

    /**
     * 405 - Method Not Allowed
     */
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResponseEntity<RespDTO> handleHttpRequestMethodNotSupportedException() {

        HttpStatus status = HttpStatus.METHOD_NOT_ALLOWED;
        return new ResponseEntity(getRespDTOByException(status), status);
    }

    /**
     * 415 - Unsupported Media Type
     */
    @ExceptionHandler(HttpMediaTypeNotSupportedException.class)
    public ResponseEntity<RespDTO> handleHttpMediaTypeNotSupportedException() {

        HttpStatus status = HttpStatus.UNSUPPORTED_MEDIA_TYPE;
        return new ResponseEntity(getRespDTOByException(status), status);
    }

    /**
     * 500 - Internal Server Error
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<RespDTO> exception(Exception e) {
        e.printStackTrace();
        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
        return new ResponseEntity(getRespDTOByException(status), status);
    }

    private RespDTO getRespDTOByException(HttpStatus status) {

        RespDTO resp = new RespDTO();
        resp.code = status.value();
        resp.message = status.getReasonPhrase();
        return resp;
    }
}
