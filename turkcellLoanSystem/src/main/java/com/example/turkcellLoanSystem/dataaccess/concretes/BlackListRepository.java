package com.example.turkcellLoanSystem.dataaccess.concretes;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.turkcellLoanSystem.entities.concretes.BlackList;


public interface BlackListRepository extends JpaRepository<BlackList, String> {

	@Query(value = "SELECT * FROM black_list where tc_No = :tcNo" , nativeQuery = true)     
	  public List<BlackList> isBlackList(@Param("tcNo") String tcNo);
}
