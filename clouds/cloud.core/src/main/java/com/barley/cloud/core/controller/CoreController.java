package com.barley.cloud.core.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class CoreController {
	@Value("${info.foo}")
	String name = "World";
	String test = "World";
	@RequestMapping("/")
	public String home() {
		System.out.println(test);
		return "Hello World!" + name;
	}
}
