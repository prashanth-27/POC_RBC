package com.xoriant.mapper.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.xoriant.mapper.entity.SourceXpathMappingEntity;
import com.xoriant.mapper.model.FileData;
import com.xoriant.mapper.pojo.SourceMappingPojo;
import com.xoriant.mapper.service.CreateXSDService;
import com.xoriant.mapper.service.FileDataService;
import com.xoriant.mapper.service.SourceFieldsService;
import com.xoriant.mapper.service.SourceXpathMappingService;
import com.xoriant.mapper.service.XpathMasterService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController()
public class MappingController {

	@Autowired
	private SourceFieldsService sourceFieldsService;

	@Autowired
	private XpathMasterService xpathMasterService;

	@Autowired
	private SourceXpathMappingService sourceXpathMappingService;

	@Autowired
	CreateXSDService createXSDService;
	
	@Autowired
	FileDataService fileDataService;

	@Autowired
	ObjectMapper objectMapper;

	@GetMapping("/getSourceCol")
	public List<String> getSourceCol() {
		log.info("Mapping Controller: inside getSourceCol");
		return sourceFieldsService.getSourceFields();
	}

	@GetMapping("/getXpath")
	public List<String> getXpath() {
		log.info("Mapping Controller: inside getXpath");
		return xpathMasterService.getXpath();
	}

	@PostMapping("/saveMapping")
	public List<SourceXpathMappingEntity> saveMapping(@RequestBody List<SourceMappingPojo> sourceMappingPojo) {
		log.info("Mapping Controller: inside saveMapping");
		log.info(sourceMappingPojo.toString());
		return sourceXpathMappingService.saveMapping(sourceMappingPojo);
	}

	@PutMapping("/updateMapping")
	public List<SourceXpathMappingEntity> updateMapping(@RequestBody List<SourceMappingPojo> sourceMappingPojo) {
		log.info("Mapping Controller: inside updateMapping");
		log.info(sourceMappingPojo.toString());
		return sourceXpathMappingService.updateMapping(sourceMappingPojo);
	}

	@GetMapping("/getMapping")
	public List<SourceXpathMappingEntity> getMapping() {
		log.info("Mapping Controller: inside getMapping");
		return sourceXpathMappingService.getMapping();
	}

	@PostMapping("/loadData")
	public ResponseEntity<String> loadFileData(@RequestBody String jsonString) {
		try {
			ObjectMapper objectMapper = new ObjectMapper();
			JsonNode node = objectMapper.readTree(jsonString);
			JsonNode fileDataNode = node.get("fileData");

			JsonParser jsonParser = objectMapper.treeAsTokens(fileDataNode);
			FileData fileData = objectMapper.readValue(jsonParser, FileData.class);
			fileDataService.insertFileData(fileData);
			log.debug(" insertFileData");
			return ResponseEntity.ok("File data processed successfully.");
		} catch (IOException e) {
			return ResponseEntity.badRequest().body("Failed to parse JSON: " + e.getMessage());
		}
	}

	@GetMapping("/test")
	public void test() {
		createXSDService.generateXsd();
	}
}
