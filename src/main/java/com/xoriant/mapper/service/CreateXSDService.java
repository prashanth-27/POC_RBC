package com.xoriant.mapper.service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xoriant.mapper.repo.SourceXpathMappingRepo;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class CreateXSDService {

	@Autowired
	private SourceXpathMappingRepo sourceXpathMappingRepo;
	
	@Autowired
	EntityManager entityManager;

	public void generateXsd() {
		
		StringBuilder selectQuery = new StringBuilder();
		selectQuery.append(
				"Select source_colname,xpath from source_xpath_mapping");
		List<Object[]> res = getResult(selectQuery);

		Map<String, String> piuidMappingMap = res.stream().collect(
				Collectors.toMap(obj -> String.valueOf(obj[0]), obj -> String.valueOf(obj[1]), (a, b) -> a));
		
		}
	
	private List<Object[]> getResult(StringBuilder updatequery) {
		Query query = null;
		List<Object[]> resList = null;
		try {
			query = entityManager.createNativeQuery(updatequery.toString());
			resList = query.getResultList();
		} catch (Exception ex) {
			log.info("Exception " + updatequery.toString());
		}

		return resList;
	}
	}
			