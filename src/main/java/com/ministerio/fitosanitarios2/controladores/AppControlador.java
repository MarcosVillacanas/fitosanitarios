package com.ministerio.fitosanitarios2.controladores;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AppControlador {
	
	@GetMapping("/")
	public String getView() {
		return "index";
	} 
}
