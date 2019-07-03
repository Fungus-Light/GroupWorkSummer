package com.minqing.demo.entity;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import javax.transaction.Transactional;
import java.util.List;

@RepositoryRestResource
@Transactional
public interface RecordRepository extends JpaRepository<Record,Integer> {
    public List findRecordsByStudentidAndTeacherid(String studentid,String teacherid);
}
