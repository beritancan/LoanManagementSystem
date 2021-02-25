package com.example.turkcellLoanSystem.entities.concretes;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.example.turkcellLoanSystem.entities.abstracts.IEntity;

import lombok.Data;

@Data
@Entity
@Table(name="black_list")
public class BlackList implements IEntity {
	
	@Id
	@Column(name="tc_No")
	private String tcNo;
	
	

}
