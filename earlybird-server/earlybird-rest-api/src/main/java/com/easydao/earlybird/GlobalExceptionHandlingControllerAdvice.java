package com.easydao.earlybird;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandlingControllerAdvice {
    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandlingControllerAdvice.class);

    @ResponseBody
    @ExceptionHandler(Throwable.class)
    public Object handleException(HttpServletRequest req, Throwable t, HttpServletResponse resp) throws Throwable {
        if (AnnotationUtils.findAnnotation(t.getClass(), ResponseStatus.class) != null) {
            throw t;
        }
        logger.error("Request: " + req.getRequestURI() + " raised ", t);
        Map<String, String> res = new HashMap<>();
        res.put("message", t.getMessage());
        resp.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
        return res;
    }
}