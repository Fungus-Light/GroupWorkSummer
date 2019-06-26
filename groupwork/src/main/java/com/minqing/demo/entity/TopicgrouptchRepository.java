package com.minqing.demo.entity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import javax.transaction.Transactional;

@RepositoryRestResource
@Transactional
public interface TopicgrouptchRepository extends JpaRepository<Topicgrouptch,String> {
}
