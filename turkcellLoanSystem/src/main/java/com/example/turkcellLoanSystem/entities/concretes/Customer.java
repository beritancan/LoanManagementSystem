package com.example.turkcellLoanSystem.entities.concretes;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.example.turkcellLoanSystem.entities.abstracts.IEntity;

import lombok.Data;

@Data
@Entity
@Table(name="turkcell_customers")
public class Customer implements IEntity{
	
	@Id
	@Column(name="tc_No")
	private String tcNo;
	@Column(name="name")
	private String name;
	@Column(name="last_name")
	private String lastName;
	@Column(name="msisdn")
	private String msisdn;
	@Column(name="address")
	private String address;
	@Column(name="date_of_birth")
	private Date dateOfBirth;
	@Column(name="type_of_customer")
	private String typeOfCustomer;
	@Column(name="date_of_subscription")
	private String dateOfSubscription; //bura stringdi değiştirdin

	
}