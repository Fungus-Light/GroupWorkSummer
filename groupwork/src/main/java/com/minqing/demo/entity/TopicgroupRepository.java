package com.minqing.demo.entity;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@RepositoryRestResource
@Transactional
public interface TopicgroupRepository extends JpaRepository<Topicgroup,String> {
    @Query("select groupid from Topicgroup where userid=?1")
    public String findGropuidByUserid(String userid);
    public List findTopicgroupByGroupid(String groupid);
}
