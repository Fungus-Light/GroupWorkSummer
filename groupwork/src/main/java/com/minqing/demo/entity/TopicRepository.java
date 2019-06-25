package com.minqing.demo.entity;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TopicRepository extends JpaRepository<Topic,Integer> {
    public List<Topic> findByState(Integer state);
}
