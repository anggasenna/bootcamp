package com.example.mr.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.mr.model.DataKerusakan;

@Repository
public interface DataKerusakanRepository extends CrudRepository<DataKerusakan, Long> {
	
	@Query
	(value="SELECT * FROM datakerusakan dk ORDER BY dk.status_perbaikan ASC", nativeQuery=true)
	List<DataKerusakan> sortByStatusPerbaikanAsc();
	
}
