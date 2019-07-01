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
    @Query(value = "select o.topicid from Student s,Teacher t,Topic o where s.academic=t.academic " +
            "and t.userid=o.userid and s.academic=?1",nativeQuery = true)
    List<Integer> findTopicidByAcademic(String academic);
}
