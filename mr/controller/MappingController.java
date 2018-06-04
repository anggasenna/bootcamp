package com.example.mr.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
//@SessionAttributes("penggunaaktif")
public class MappingController {
	
	@RequestMapping("/indexuser")
	public String keIndexUser() {
		return "indexuser";
	}

	@RequestMapping("/historikerusakan")
	public String keKerusakan() {
		return "historikerusakan";
	}
	
	@RequestMapping("/historipemakaian")
	public String kePemakaian() {
		return "historipemakaian";
	}
	
	@RequestMapping("/formpengajuan")
	public String keInputPengajuan() {
		return "formpengajuan";
	}
		
	@RequestMapping("/jadwal")
	public String keJadwalRuanganUser() {
		return "jadwalruanganuser";
	}
	
	
	@RequestMapping("/daftar")
	public String keDaftarPengajuan() {
		return "daftarpengajuanuser";
	}

}
