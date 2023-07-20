package com.xoriant.mapper.repo;

import java.util.List;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.xoriant.mapper.entity.SourceFieldsEntity;

@Repository
public interface SourceFieldsRepo extends JpaRepository<SourceFieldsEntity, Integer> {

}
