package com.example.mr.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.mr.model.DataPengajuan;
import com.example.mr.model.Ruangan;

@Repository
public interface DataPengajuanRepository extends CrudRepository<DataPengajuan, Long> {

	List<DataPengajuan> findByRuanganOrderByCreateDateAsc(Ruangan ruangan);
	List<DataPengajuan> findByTanggalPemakaianOrderByCreateDateAsc(Date tanggalPemakaian);
	List<DataPengajuan> getByStatusPengajuan(String statusPengajuan);
	
}
