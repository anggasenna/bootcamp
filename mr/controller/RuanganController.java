package com.example.mr.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.example.mr.model.DataLogin;
import com.example.mr.model.Ruangan;
import com.example.mr.services.RuanganServices;

@Controller
@SessionAttributes("penggunaaktif")
public class RuanganController {

	@Autowired
	private RuanganServices ruanganServices;
	
	@RequestMapping("/formruangan")
	public String keInputRuangan() {
		return "formruangan";
	}
	
	@RequestMapping(value="formruangan",method=RequestMethod.POST)
	public ModelAndView goRuangan(@ModelAttribute("formruangan") Ruangan ruangan, @ModelAttribute("penggunaaktif") DataLogin dl) {
		ruangan.setCreatedBy(dl.getKaryawan().getNama());
		ruanganServices.SaveOrUpdate(ruangan);
		return new ModelAndView("formruangan","listruangan",ruanganServices.getAll());
	}
	
	@RequestMapping(value="/dataruangan")
	public ModelAndView keDataRuangan(@ModelAttribute("penggunaaktif") DataLogin dl, @ModelAttribute("dataruangan") Ruangan r) {
		ModelAndView mav = new ModelAndView("dataruangan","listruangan",ruanganServices.getAll());
		mav.addObject("ruangan", new Ruangan()); 
		return mav;
	}
	
	@RequestMapping(value="dataruangan",method=RequestMethod.GET)
	public ModelAndView goRuanganafterUpdate(@ModelAttribute("dataruangan") Ruangan ruangan) {
		return new ModelAndView("dataruangan","listruangan",ruanganServices.getAll());
	}
	
	@RequestMapping(value ="updateruangan",method=RequestMethod.GET)
    public ModelAndView formUpdateRuangan(@RequestParam("id") int id){
        return new ModelAndView("updateruangan","ruangan",ruanganServices.getById(id));
    }
	
	@RequestMapping(value ="updateruangan",method=RequestMethod.POST)
    public String updateRuangan(@ModelAttribute("Ruangan") Ruangan ruangan, @ModelAttribute("penggunaaktif") DataLogin dl){
		ruangan.setUpdatedBy(dl.getKaryawan().getNama());
		ruanganServices.SaveOrUpdate(ruangan);
        return "redirect:dataruangan";
    }

}
