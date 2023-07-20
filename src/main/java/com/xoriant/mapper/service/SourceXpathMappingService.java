package com.xoriant.mapper.service;

import java.util.List;

import javax.xml.parsers.ParserConfigurationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xoriant.mapper.entity.SourceXpathMappingEntity;
import com.xoriant.mapper.pojo.SourceMappingPojo;
import com.xoriant.mapper.repo.SourceXpathMappingRepo;

import jakarta.persistence.spi.TransformerException;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class SourceXpathMappingService {

	@Autowired
	private SourceXpathMappingRepo sourceXpathMappingRepo;

	@Autowired
	private CreateXSDService createXSDService;

	public List<SourceXpathMappingEntity> saveMapping(List<SourceMappingPojo> sourceMappingPojo) {

		/*
		 * SourceXpathMappingEntity sourceXpathMappingEntity =
		 * (SourceXpathMappingEntity) sourceMappingPojo.stream() .map(data -> new
		 * SourceXpathMappingEntity(data.getSourceCol(), data.getXpath()));
		 */

		/*
		 * sourceXpathMappingRepo.save((SourceXpathMappingEntity)
		 * sourceMappingPojo.stream() .map(data -> new
		 * SourceXpathMappingEntity(data.getSourceCol(), data.getXpath())));
		 * 
		 */

		int i = 1;

		for (SourceMappingPojo s : sourceMappingPojo) {
			log.info(s.toString());
			SourceXpathMappingEntity sourceXpathMappingEntity = new SourceXpathMappingEntity(i++, s.getSourceCol(),
					s.getXpath());
			sourceXpathMappingRepo.save(sourceXpathMappingEntity);
		}

		
			createXSDService.generateXsd();
		

		return sourceXpathMappingRepo.findAll();

	}

	public List<SourceXpathMappingEntity> updateMapping(List<SourceMappingPojo> sourceMappingPojo) {
		int i = 1;

		sourceXpathMappingRepo.deleteAll();

		log.info("inside delete", sourceXpathMappingRepo.findAll().toString());

		for (SourceMappingPojo s : sourceMappingPojo) {
			log.info(s.toString());
			SourceXpathMappingEntity sourceXpathMappingEntity = new SourceXpathMappingEntity(i++, s.getSourceCol(),
					s.getXpath());
			sourceXpathMappingRepo.save(sourceXpathMappingEntity);
		}

		return sourceXpathMappingRepo.findAll();
	}
	
	public List<SourceXpathMappingEntity> getMapping(){
		return sourceXpathMappingRepo.findAll();
	}
}
