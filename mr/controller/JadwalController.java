package com.example.mr.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.example.mr.model.Ruangan;
import com.example.mr.services.RuanganServices;

@Controller
//@SessionAttributes("penggunaaktif")
public class JadwalController {
	
	@Autowired
	RuanganServices ruanganServices;

	@RequestMapping("/jadwalruangan")
	public String keJadwalRuangan() {
		return "jadwalruangan";
	}
	
	@RequestMapping("/jadwalbulan")
	public ModelAndView keJadwalRuangan(@ModelAttribute("ruangan") Ruangan r) {
		return new ModelAndView("jadwalbulan","listruangan",ruanganServices.getTampilRuangan());
	}
	
	@RequestMapping("/bulan")
	public ModelAndView keJadwalRuanganUser(@ModelAttribute("ruangan") Ruangan r) {
		return new ModelAndView("jadwalbulanuser","listruangan",ruanganServices.getTampilRuangan());
	}
	
	@RequestMapping("/jadwalminggu")
	public ModelAndView keJadwalMinggu() {
		return new ModelAndView("jadwalminggu","listruangan",ruanganServices.getTampilRuangan());
	}
	
	@RequestMapping("/minggu")
	public ModelAndView keJadwalMingguUser() {
		return new ModelAndView("jadwalmingguuser","listruangan",ruanganServices.getTampilRuangan());
	}
	
}
