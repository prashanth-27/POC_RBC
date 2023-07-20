package com.xoriant.mapper.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.xoriant.mapper.model.FileData;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class FileDataService {
	
    private final JdbcTemplate jdbcTemplate;

    public FileDataService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
         } 
    public void insertFileData(FileData fileData) {
        List<String> headers = fileData.getHeader();
        List<Object> data = fileData.getData();
        
        List<String> modifiedHeaders = new ArrayList<>();
        for (String header : headers) {
            String modifiedHeader = header.replace('-', '_');
            modifiedHeaders.add(modifiedHeader);
        }
        String tableName = "stg_xorpay_84_fmw_ons";
        StringBuilder sql = new StringBuilder("INSERT INTO ")
                .append(tableName)
                .append(" (internalid,")
                .append(String.join(",", modifiedHeaders))
                .append(") VALUES ( 1,");

        List<String> placeholders = new ArrayList<>();
        for (int i = 0; i < headers.size(); i++) {
            placeholders.add("?");
        }
        sql.append(String.join(",", placeholders))
                .append(")");
		Object[] row = new Object[headers.size()];
		for (int i = 0; i < headers.size(); i++) {
			if(data.size()>i) {
				row[i] = data.get(i);
			}	
		}
		try {
                jdbcTemplate.update(sql.toString(), row);
                log.info("Inserted data for row {}", sql.toString());
            } catch (Exception e) {
                log.error("Failed to insert data for row {}: {}", sql.toString(), e.getMessage());
            }
        }
    
 
}


  
    



