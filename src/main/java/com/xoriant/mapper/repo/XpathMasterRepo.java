package com.xoriant.mapper.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.xoriant.mapper.entity.XpathMasterEntity;


@Repository
public interface XpathMasterRepo extends JpaRepository<XpathMasterEntity, String> {

}
