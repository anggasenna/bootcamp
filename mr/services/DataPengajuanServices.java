package com.example.mr.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.mr.dao.DataPengajuanDAO;
import com.example.mr.model.DataPengajuan;
import com.example.mr.repository.DataPengajuanRepository;

@Service
public class DataPengajuanServices implements DataPengajuanDAO {
	
	@Autowired
	DataPengajuanRepository dataPengajuanRepository;

	@Override
	public DataPengajuan getById(long id) {
		DataPengajuan dp = dataPengajuanRepository.findById(id).get();
		return dp;
	}

	@Override
	public List<DataPengajuan> getAll() {
		List<DataPengajuan> ldp = new ArrayList<>();
		dataPengajuanRepository.findAll().forEach(ldp::add);
		return ldp;
	}

	public List<DataPengajuan> getPengajuanPending() {
		List<DataPengajuan> ldp = new ArrayList<>();
		dataPengajuanRepository.getByStatusPengajuan("Pending").forEach(ldp::add);
		return ldp;
	}
	
	public List<DataPengajuan> getPengajuanDiterima(){
		List<DataPengajuan> ldp = new ArrayList<>();
		dataPengajuanRepository.getByStatusPengajuan("Diterima").forEach(ldp::add);
		return ldp;
	}
	
	public List<DataPengajuan> getPengajuanDitolak(){
		List<DataPengajuan> ldp = new ArrayList<>();
		dataPengajuanRepository.getByStatusPengajuan("Ditolak").forEach(ldp::add);
		return ldp;
	}

	public void SaveOrUpdate(DataPengajuan dp) {
		dataPengajuanRepository.save(dp);
	}

}
