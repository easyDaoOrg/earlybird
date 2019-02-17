package com.easydao.earlybird.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FlightController {
    private final static Logger logger = LoggerFactory.getLogger(FlightController.class);

    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    public Object createInstance() {
        logger.error("hello {}", System.currentTimeMillis());
        return "hello";
    }
}