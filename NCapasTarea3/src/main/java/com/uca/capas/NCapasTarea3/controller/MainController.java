package com.uca.capas.NCapasTarea3.controller;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class MainController {
	
	@RequestMapping("/ingresar")
	public String index() {
		return "login";
	}
	
	@RequestMapping("/data")
	public ModelAndView parametros(@RequestParam String nombres, @RequestParam String apellidos, 
			@RequestParam String paramfecha, @RequestParam String lugarNac, @RequestParam String colegio, 
			@RequestParam String tel, @RequestParam String cel) throws ParseException {
		
		ModelAndView mav = new ModelAndView();
		List<String> errorList = new ArrayList<>();
		
		
		if(nombres.length() < 1 || nombres.length() > 25) {
			errorList.add("Nombres mínimo 1 carácter y máximo 25 caracteres");
		}
		if(apellidos.length() < 1 || apellidos.length() > 25) {
			errorList.add("Apellidos mínimo 1 carácter y máximo 25 caracteres");
		}
		if(paramfecha.equals("")) {
			errorList.add("Fecha de Nacimiento no puede ser menor al 1 de enero de 2003");
		}else {
			DateFormat date = new SimpleDateFormat("yyyy-MM-dd");
			Date fecha = date.parse(paramfecha);
			Date minFecha = date.parse("2003-01-01");
			if(fecha.before(minFecha)) {
				errorList.add("Fecha de Nacimiento no puede ser menor al 1 de enero de 2003");
			}
		}
		
		if(lugarNac.length() < 1 || lugarNac.length() > 25) {
			errorList.add("Lugar de Nacimiento mínimo 1 carácter y máximo 25 caracteres");
		}
		if(colegio.length() < 1 || colegio.length() > 100) {
			errorList.add("Instituto o Colegio de procedencia mínimo 1 carácter y máximo 100 caracteres");
		}
		if(tel.length() != 8) {
			errorList.add("Teléfono fijo 8 números exactamente");
		}
		if(cel.length() != 8) {
			errorList.add("Teléfono móvil 8 números exactamente");
		}

		
		if( errorList.size() != 0 ) {
			mav.addObject("errorlist", errorList);
			mav.setViewName("/incorrect");
			return mav;
		}
		
		mav.setViewName("/correct");
		
		return mav;
	}
}
