package com.example.mr.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.multipart.MultipartException;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.mr.model.DataKerusakan;
import com.example.mr.model.DataLogin;
import com.example.mr.model.Karyawan;
import com.example.mr.model.Ruangan;
import com.example.mr.repository.DataPengajuanRepository;
import com.example.mr.services.DataKerusakanServices;
import com.example.mr.services.RuanganServices;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;


@Controller
//@SessionAttributes("penggunaaktif")
public class DataKerusakanController {

	@Autowired
	RuanganServices ruanganServices;
	
	@Autowired
	DataKerusakanServices dataKerusakanServices;
	
	@RequestMapping(value="/formkerusakan",method=RequestMethod.GET)
	public ModelAndView keFormKerusakan(@ModelAttribute("ruangan") Ruangan r) {
		ModelAndView mav = new ModelAndView();
		List<Ruangan> ruangan = ruanganServices.getTampilRuangan();
		mav.addObject("listruangan",ruangan);
		return mav;
	}
	
	@RequestMapping(value="/formkerusakan",method=RequestMethod.POST)
	public ModelAndView insertKerusakan(@ModelAttribute("datakerusakan") DataKerusakan dk, @ModelAttribute("ruangan") Ruangan r, @ModelAttribute("penggunaaktif") DataLogin dl, @ModelAttribute("karyawan") Karyawan k) {
		dk.setKaryawan(dl.getKaryawan());
		dataKerusakanServices.SaveOrUpdate(dk);
		return new ModelAndView("redirect:formkerusakan","listkerusakan",dataKerusakanServices.getAll());
	}
	
	@RequestMapping(value="/historikerusakan",method=RequestMethod.GET)
	public ModelAndView keHistoriKerusakan(@ModelAttribute("datakerusakan") DataKerusakan dk, @ModelAttribute("ruangan") Ruangan r) {
		ModelAndView mav = new ModelAndView();
		List<DataKerusakan> ldk = dataKerusakanServices.getStatusPerbaikan();
		List<Ruangan> ruangan = ruanganServices.getTampilRuangan();
		mav.addObject("listdatakerusakan", ldk);
		mav.addObject("listruangan", ruangan);
		return mav;
	}

}

//	 //Save the uploaded file to this folder
//    private static String uploadedFolder = "C:/Users/RyanMahardika/Documents/workspace-sts-3.9.4.RELEASE/MeetingRoom/src/main/resources/static/img_kerusakan/";
//
//    @RequestMapping(value ="formkerusakan",method=RequestMethod.POST)
//    public String singleFileUpload(@RequestParam("attachmentFoto") MultipartFile attachmentFoto,
//                                   RedirectAttributes redirectAttributes) {
//
//        if (attachmentFoto.isEmpty()) {
//            redirectAttributes.addFlashAttribute("message", "Please select a file to upload");
//            return "redirect:formkerusakan";
//        }
//
//        try {
//
//            // Get the file and save it somewhere
//            byte[] bytes = attachmentFoto.getBytes();
//            Path path = Paths.get(uploadedFolder + attachmentFoto.getOriginalFilename());
//            Files.write(path, bytes);
//
//            redirectAttributes.addFlashAttribute("message",
//                    "You successfully uploaded '" + attachmentFoto.getOriginalFilename() + "'");
//
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//        return "redirect:formkerusakan";
//    }