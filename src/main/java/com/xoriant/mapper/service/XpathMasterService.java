package com.xoriant.mapper.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xoriant.mapper.repo.XpathMasterRepo;

@Service
public class XpathMasterService {
	
	@Autowired
	private XpathMasterRepo xpathMasterRepo;

	public List<String> getXpath(){
		return xpathMasterRepo.findAll().stream().map(x->x.getXpath()).toList();
		
	}
}
