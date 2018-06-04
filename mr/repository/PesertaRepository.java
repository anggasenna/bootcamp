package com.example.mr.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.mr.model.Peserta;

@Repository
public interface PesertaRepository extends CrudRepository<Peserta, Long>{

}
