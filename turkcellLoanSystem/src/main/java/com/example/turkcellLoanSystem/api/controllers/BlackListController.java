package com.example.turkcellLoanSystem.api.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.turkcellLoanSystem.business.abstracts.IBlackListService;
import com.example.turkcellLoanSystem.entities.concretes.BlackList;


@RestController
@RequestMapping("/api/v2")
public class BlackListController {
	
	@Autowired
	IBlackListService blackListService;

	@PostMapping("/blackLists")
	public String add (@RequestBody BlackList blackList) {
		System.out.println(blackList);
		return  blackListService.add(blackList);
	}
	
	@DeleteMapping("blackLists/{id}")
	public String delete(@PathVariable(value="id") String tcNo) {
		return blackListService.delete(tcNo);
		  }

	
}
