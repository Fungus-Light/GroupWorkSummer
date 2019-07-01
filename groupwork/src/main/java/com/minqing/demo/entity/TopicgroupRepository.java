package com.minqing.demo.entity;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@RepositoryRestResource
@Transactional
public interface TopicgroupRepository extends JpaRepository<Topicgroup,String> {
}
