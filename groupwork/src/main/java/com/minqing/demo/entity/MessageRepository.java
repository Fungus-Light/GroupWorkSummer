package com.minqing.demo.entity;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import javax.transaction.Transactional;
import java.util.List;

@RepositoryRestResource
@Transactional
public interface MessageRepository extends JpaRepository<Message,Integer> {
    List<Message> findByUseridOrderByTime(String userid);
//    @Query("select * from message order by time")
//    public List<>
}
