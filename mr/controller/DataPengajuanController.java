package com.example.mr.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.example.mr.model.DataLogin;
import com.example.mr.model.DataPengajuan;
import com.example.mr.model.Karyawan;
import com.example.mr.model.Peserta;
import com.example.mr.model.Ruangan;
import com.example.mr.services.DataPengajuanServices;
import com.example.mr.services.KaryawanServices;
import com.example.mr.services.PesertaServices;
import com.example.mr.services.RuanganServices;

@Controller
//@SessionAttributes("penggunaaktif")
public class DataPengajuanController {

	@Autowired
	DataPengajuanServices dataPengajuanServices;
	@Autowired
	RuanganServices ruanganServices;
	@Autowired
	KaryawanServices karyawanServices;
	@Autowired
	PesertaServices pesertaServices;
	
	// --------------------------- ADMIN ----------------------------------
	
	@RequestMapping(value="/formpengajuan",method=RequestMethod.GET)
	public ModelAndView keFormPengajuan(@ModelAttribute("ruangan") Ruangan r, @ModelAttribute("karyawan") Karyawan k) {
		ModelAndView mav = new ModelAndView();
		List<Karyawan> peserta = karyawanServices.getAll();
		List<Ruangan> ruangan = ruanganServices.getTampilRuangan();
		mav.addObject("listpeserta",peserta);
		mav.addObject("listruangan",ruangan);
		mav.addObject("datapengajuan",new DataPengajuan());
		return mav;
	}
	
	@RequestMapping(value="/formpengajuan",method=RequestMethod.POST)
	public ModelAndView insertPengajuan(@RequestParam("peserta") List<Long> ids,@ModelAttribute("datapengajuan") DataPengajuan dp, @ModelAttribute("ruangan") Ruangan r, @ModelAttribute("penggunaaktif") DataLogin dl, @ModelAttribute("karyawan") Karyawan k) {
		dp.setCreatedBy(dl.getKaryawan().getNama());
		dp.setKaryawan(dl.getKaryawan());
		dataPengajuanServices.savePengajuan(dp, ids);
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
	
	// ---------------------------- USER ----------------------------------
	
	@RequestMapping(value="/pengajuan",method=RequestMethod.GET)
	public ModelAndView keFormPengajuanUser(@ModelAttribute("ruangan") Ruangan r, @ModelAttribute("karyawan") Karyawan k) {
		ModelAndView mav = new ModelAndView();
		List<Karyawan> peserta = karyawanServices.getAll();
		List<Ruangan> ruangan = ruanganServices.getTampilRuangan();
		mav.addObject("listpeserta",peserta);
		mav.addObject("listruangan",ruangan);
		mav.addObject("datapengajuan",new DataPengajuan());
		mav.setViewName("formpengajuanuser");
		return mav;
	}
	
	@RequestMapping(value="/pengajuan",method=RequestMethod.POST)
	public ModelAndView insertPengajuanUser(@RequestParam("peserta") List<Long> ids,@ModelAttribute("datapengajuan") DataPengajuan dp, @ModelAttribute("ruangan") Ruangan r, @ModelAttribute("penggunaaktif") DataLogin dl, @ModelAttribute("karyawan") Karyawan k) {
		dp.setCreatedBy(dl.getKaryawan().getNama());
		dp.setKaryawan(dl.getKaryawan());
		dataPengajuanServices.savePengajuan(dp, ids);
		return new ModelAndView("redirect:formpengajuanuser","listpengajuan",dataPengajuanServices.getAll());
	}
	
	@RequestMapping(value="/histori",method=RequestMethod.GET)
	public ModelAndView tampilHistoriPemakaianUser(@ModelAttribute("daftarpengajuan") DataPengajuan dp,@ModelAttribute("penggunaaktif") DataLogin dl) {
		ModelAndView mav = new ModelAndView();
		List<DataPengajuan> ldp = dataPengajuanServices.getPengajuanSelesai("Selesai",2L);
		mav.addObject("listdatapengajuan", ldp);
		mav.setViewName("historipemakaianuser");
		return mav;
	}
	
	@RequestMapping(value="/daftar",method=RequestMethod.GET)
	public ModelAndView tampilPengajuanUser(@ModelAttribute("daftarpengajuan") DataPengajuan dp,@ModelAttribute("penggunaaktif") DataLogin dl) {
		ModelAndView mav = new ModelAndView();
		List<DataPengajuan> ldp = dataPengajuanServices.getPengajuanUser(2L);
		mav.addObject("listdatapengajuan", ldp);
		mav.setViewName("daftarpengajuanuser");
		return mav;
	}
	
	@RequestMapping(value ="updatepengajuan",method=RequestMethod.GET)
    public ModelAndView formUpdatePengajuan(@RequestParam("id") int id){
        return new ModelAndView("updatepengajuanuser","datapengajuan",dataPengajuanServices.getById(id));
    }
	
	@RequestMapping(value ="updatepengajuan",method=RequestMethod.POST)
    public String updatePengajuan(@ModelAttribute("Ruangan") Ruangan ruangan, @ModelAttribute("penggunaaktif") DataLogin dl){
		ruangan.setUpdatedBy(dl.getKaryawan().getNama());
		ruanganServices.SaveOrUpdate(ruangan);
        return "redirect:updatepengajuan";
    }
	
	
}
