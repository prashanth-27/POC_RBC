package com.xoriant.mapper.repo;

import java.util.List;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.jpa.repository.JpaRepository;

import com.xoriant.mapper.entity.SourceXpathMappingEntity;

import jakarta.persistence.Id;

public interface SourceXpathMappingRepo extends JpaRepository<SourceXpathMappingEntity, Id> {
	

}
