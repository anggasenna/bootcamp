package com.example.mr.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
@SessionAttributes("penggunaaktif")
public class MappingController {
	
	@RequestMapping("/indexuser")
	public String keIndexUser() {
		return "indexuser";
	}
	
	@RequestMapping("/jadwalruangan")
	public String keJadwalRuangan() {
		return "jadwalruangan";
	}
	
	@RequestMapping("/jadwalbulan")
	public String keJadwalBulan() {
		return "jadwalbulan";
	}
	
	@RequestMapping("/jadwalbulanuser")
	public String keJadwalBulanUser() {
		return "jadwalbulanuser";
	}
	
	@RequestMapping("/jadwalminggu")
	public String keJadwalMinggu() {
		return "jadwalminggu";
	}
	
	@RequestMapping("/jadwalmingguuser")
	public String keJadwalMingguUser() {
		return "jadwalmingguuser";
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

}
