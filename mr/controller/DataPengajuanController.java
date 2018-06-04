package com.example.mr.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.example.mr.model.DataLogin;
import com.example.mr.model.DataPengajuan;
import com.example.mr.model.Karyawan;
import com.example.mr.model.Ruangan;
import com.example.mr.services.DataPengajuanServices;
import com.example.mr.services.KaryawanServices;
import com.example.mr.services.RuanganServices;

@Controller
@SessionAttributes("penggunaaktif")
public class DataPengajuanController {

	@Autowired
	DataPengajuanServices dataPengajuanServices;
	@Autowired
	RuanganServices ruanganServices;
	@Autowired
	KaryawanServices karyawanServices;
	
	@RequestMapping(value="/formpengajuan",method=RequestMethod.GET)
	public ModelAndView keFormPengajuan(@ModelAttribute("ruangan") Ruangan r, @ModelAttribute("karyawan") Karyawan k) {
		ModelAndView mav = new ModelAndView();
		List<Karyawan> peserta = karyawanServices.getAll();
		List<Ruangan> ruangan = ruanganServices.getTampilRuangan();
		mav.addObject("listpeserta",peserta);
		mav.addObject("listruangan",ruangan);
		return mav;
	}
	
	@RequestMapping(value="/formpengajuan",method=RequestMethod.POST)
	public ModelAndView insertPengajuan(@ModelAttribute("datapengajuan") DataPengajuan dp, @ModelAttribute("ruangan") Ruangan r, @ModelAttribute("penggunaaktif") DataLogin dl, @ModelAttribute("karyawan") Karyawan k) {
		dp.setCreatedBy(dl.getKaryawan().getNama());
		dp.setKaryawan(dl.getKaryawan());
		dataPengajuanServices.SaveOrUpdate(dp);
		return new ModelAndView("jadwalruangan","listpengajuan",dataPengajuanServices.getAll());
	}
	
	@RequestMapping("/daftarpengajuan")
	public String keDaftarPengajuan() {
		return "daftarpengajuan";
	}
	
	@RequestMapping(value="daftarpengajuan",method=RequestMethod.GET)
	public ModelAndView tampilDaftarPengajuan(@ModelAttribute("daftarpengajuan") DataPengajuan dp) {
		return new ModelAndView("daftarpengajuan","listpengajuan",dataPengajuanServices.getPengajuanPending());
	}
	
	@RequestMapping(value="historipemakaian",method=RequestMethod.GET)
	public ModelAndView tampilHistoriPemakaian(@ModelAttribute("daftarpengajuan") DataPengajuan dp, @ModelAttribute("ruangan") Ruangan r) {
		ModelAndView mav = new ModelAndView();
		List<DataPengajuan> ldp = dataPengajuanServices.getPengajuanDiterima();
		List<Ruangan> ruangan = ruanganServices.getTampilRuangan();
		mav.addObject("listdatapengajuan", ldp);
		mav.addObject("listruangan", ruangan);
		return mav;
	}
	
	@RequestMapping(value ="updatepengajuan",method=RequestMethod.GET)
    public ModelAndView formUpdatePengajuan(@RequestParam("id") int id, @ModelAttribute("ruangan") Ruangan r, Model model){
		List<Ruangan> ruangan = ruanganServices.getTampilRuangan();
		model.addAttribute("listruangan", ruangan);
        return new ModelAndView("updatepengajuan","datapengajuan",dataPengajuanServices.getById(id));
    }
	
	@RequestMapping(value ="updatepengajuan",method=RequestMethod.POST)
    public String updatePengajuan(@ModelAttribute("datapengajuan") DataPengajuan dp, @ModelAttribute("penggunaaktif") DataLogin dl){
		Date d = new Date();
		dp.setUpdatedBy(dl.getKaryawan().getNama());
		dp.setCreateDate(d);
		dp.setKaryawan(dl.getKaryawan());
		dataPengajuanServices.SaveOrUpdate(dp);
        return "redirect:daftarpengajuan";
    }
	
}
