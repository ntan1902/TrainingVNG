package com.vng.ewallet;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@SpringBootApplication
@Controller
@RequestMapping
public class EWalletApplication {

	public static void main(String[] args) {
		SpringApplication.run(EWalletApplication.class, args);
	}

	@ResponseBody
	@GetMapping("/")
	public String hello() {
		return "Hello Spring";
	}
}
