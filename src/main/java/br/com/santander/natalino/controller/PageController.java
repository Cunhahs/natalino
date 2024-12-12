package br.com.santander.natalino.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class PageController {

	@GetMapping("/")
	@ResponseBody
    public String index() {
    	
        return "Hello World!";
    }
	
//	@GetMapping("/")
//    public String index() {
//    	
//        return "index";
//    }
}