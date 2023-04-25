package com.example.demo.repository;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Flag;

@Repository
public interface FlagRepository extends JpaRepository<Flag,Integer>{
	Optional<Flag> findById(int id);

}
	
	//Optional<Flag>  findById(Integer id) ;