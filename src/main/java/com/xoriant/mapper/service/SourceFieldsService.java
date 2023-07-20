package com.xoriant.mapper.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xoriant.mapper.entity.SourceFieldsEntity;
import com.xoriant.mapper.repo.SourceFieldsRepo;
import com.xoriant.mapper.repo.XpathMasterRepo;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class SourceFieldsService {

	@Autowired
	private SourceFieldsRepo sourceFieldsRepo;

	@Autowired
	private XpathMasterRepo xpathMasterRepo;
	
	public List<String> getSourceFields() {
		
		return  sourceFieldsRepo.findAll().stream().map(x->x.getSourceColName()).toList();
		
	}

}
