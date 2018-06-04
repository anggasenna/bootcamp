package com.example.mr.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.example.mr.model.DataLogin;
import com.example.mr.services.DataLoginServices;

@Controller
@SessionAttributes("penggunaaktif")
public class LoginController {
	
	@Autowired
	DataLoginServices service;
	
	@RequestMapping("/")
	public String keLogin() {
		return "login";
	}
	
	@RequestMapping("/index")
	public String keIndex() {
		return "index";
	}
	
	@RequestMapping("/login")
	public ModelAndView getLogin(@ModelAttribute("DataLogin") DataLogin dl) {
		ModelAndView mav = new ModelAndView();
		DataLogin penggunaaktif = service.getLogin(dl.getUsername(), dl.getPassword());
		mav.addObject("penggunaaktif", penggunaaktif);
		if (penggunaaktif.getLoginRole().getId()==1) {
			mav.setViewName("/index");
		} else {
			mav.setViewName("/indexuser");
		}
		return mav;
	}
	
}
