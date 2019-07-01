package com.minqing.demo.entity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import javax.transaction.Transactional;
import java.util.List;

@RepositoryRestResource
@Transactional
public interface TopicRepository extends JpaRepository<Topic,Integer> {
    List<Topic> findByUserid(String userid);
    List<Topic> findByState(Integer state);
    @Modifying
    @Query("select topicid from student,teacher,topic where student.academic=teacher.academic " +
            "and teacher.userid=topic.userid and student.academic=?1")
    List findTopicidByAcademic(String academic);
}
