package com.example.mr.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.mr.model.Peserta;
import com.example.mr.repository.PesertaRepository;

@Service
public class PesertaServices {
	
	@Autowired
	PesertaRepository pesertaRepository;
	
	public Peserta getById(Long id) {
		return pesertaRepository.findById(id).get();
	}
	
	public void insertSemuaPeserta(List<Peserta> lp) {
		pesertaRepository.saveAll(lp);
	}

}
