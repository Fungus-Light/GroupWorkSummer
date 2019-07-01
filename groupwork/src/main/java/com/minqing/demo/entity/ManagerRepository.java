package com.minqing.demo.entity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import javax.transaction.Transactional;

@RepositoryRestResource
@Transactional
public interface ManagerRepository extends JpaRepository<Manager,String> {
    @Modifying
    @Query("select academic from Manager where userid=?1")
    public String findAcademicByid(String userid);
}
