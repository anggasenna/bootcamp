package com.example.mr.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.mr.dao.DataPengajuanDAO;
import com.example.mr.model.DataPengajuan;
import com.example.mr.model.Karyawan;
import com.example.mr.model.Peserta;
import com.example.mr.repository.DataPengajuanRepository;

@Service
public class DataPengajuanServices implements DataPengajuanDAO {
	
	@Autowired
	DataPengajuanRepository dataPengajuanRepository;
	@Autowired
	KaryawanServices karyawanServices;
	@Autowired
	PesertaServices pesertaServices;

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
	
	public List<DataPengajuan> getPengajuanSelesai(String statusPengajuan,Long id){
		List<DataPengajuan> ldp = new ArrayList<>();
		dataPengajuanRepository.getByStatusPengajuanById("Selesai",id).forEach(ldp::add);
		return ldp;
	}
	
	public List<DataPengajuan> getPengajuanUser(Long id){
		List<DataPengajuan> ldp = new ArrayList<>();
		dataPengajuanRepository.getByStatusPengajuanById("Pending", id).forEach(ldp::add);
		dataPengajuanRepository.getByStatusPengajuanById("Diterima", id).forEach(ldp::add);
		return ldp;
	}

	public void SaveOrUpdate(DataPengajuan dp) {
		dataPengajuanRepository.save(dp);
	}
	
	public void savePengajuan(DataPengajuan dp,List<Long> ids) {
		List<Peserta> lp = new ArrayList<>();
		dataPengajuanRepository.save(dp);
		for(int a = 0;a<ids.size();a++) {
			Karyawan karyawanPeserta = karyawanServices.getById(ids.get(a));
			Peserta p = new Peserta(ids.get(a),dp.getId()); 
			p.setKaryawan(karyawanPeserta);
			p.setPengajuan(dp);
			lp.add(p);
		}
		pesertaServices.insertSemuaPeserta(lp);
	}
	
}
