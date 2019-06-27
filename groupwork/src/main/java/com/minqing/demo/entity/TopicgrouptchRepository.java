package com.minqing.demo.entity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import javax.transaction.Transactional;
import java.util.List;

@RepositoryRestResource
@Transactional
public interface TopicgrouptchRepository extends JpaRepository<Topicgrouptch,String> {
    @Query("select distinct groupid from topicgroupstu ")
    public List<String> findAllGroupid();
    public List<Topicgrouptch> findByGroupid(String groupid);
}
