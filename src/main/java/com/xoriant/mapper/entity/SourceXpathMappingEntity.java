package com.xoriant.mapper.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name="source_xpath_mapping")
public class SourceXpathMappingEntity {

	@Id
	//@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	@Column(name = "source_colname")
	private String srcColName;

	private String xpath;
	
	public SourceXpathMappingEntity(String srcColName,String xpath){
		this.srcColName=srcColName;
		this.xpath=xpath;
	}
}
