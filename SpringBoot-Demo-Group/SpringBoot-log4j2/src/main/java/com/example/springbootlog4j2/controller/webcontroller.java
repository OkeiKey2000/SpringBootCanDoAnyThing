package com.example.springbootlog4j2.controller;

import lombok.extern.log4j.Log4j2;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: LengShui on 2022-08-22 16:46
 **/
@RestController

public class webcontroller {

	private static final Logger log = LoggerFactory.getLogger(webcontroller.class);

	@RequestMapping("/test")
	public String test() {
		log.error("error");
		log.warn("warn");
		log.info("info");
		return "OK";
	}
}
