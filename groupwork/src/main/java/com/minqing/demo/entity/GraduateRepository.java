package com.minqing.demo.entity;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import javax.persistence.Entity;
import javax.transaction.Transactional;

@RepositoryRestResource
@Transactional
public interface GraduateRepository extends JpaRepository<Graduate,String> {
    @Query("select teacherpass from Graduate where studentid=?1")
    public Integer findTeacherpassById(String id);
    @Query("select managerpass from Graduate where studentid=?1")
    public Integer findManagerpassById(String id);
}
