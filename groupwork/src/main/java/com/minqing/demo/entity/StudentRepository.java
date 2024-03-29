package com.minqing.demo.entity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import javax.transaction.Transactional;

@RepositoryRestResource
@Transactional
public interface StudentRepository extends JpaRepository<Student,String> {
//    @Modifying
//    @Query("update Student set hastopic=1 where userid=?1")
//    public void SetStudentHastopic(String studentid);
}
