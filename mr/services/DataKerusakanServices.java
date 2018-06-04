package com.example.mr.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.mr.dao.DataKerusakanDAO;
import com.example.mr.model.DataKerusakan;
import com.example.mr.repository.DataKerusakanRepository;

@Service
public class DataKerusakanServices implements DataKerusakanDAO {

	@Autowired
	DataKerusakanRepository dataKerusakanRepository;

	@Override
	public DataKerusakan getById(long id) {
		DataKerusakan dk = dataKerusakanRepository.findById(id).get();
		return dk;
	}

	@Override
	public List<DataKerusakan> getAll() {
		List<DataKerusakan> ldk = new ArrayList<>();
		dataKerusakanRepository.findAll().forEach(ldk::add);
		return ldk;
	}
	
	public void SaveOrUpdate(DataKerusakan dk) {
		dataKerusakanRepository.save(dk);
	}

	@Override
	public List<DataKerusakan> getStatusPerbaikan() {
		List<DataKerusakan> ldk = new ArrayList<>();
		dataKerusakanRepository.sortByStatusPerbaikanAsc().forEach(ldk::add);
		return ldk;
	}
	
}
