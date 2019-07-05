package com.minqing.demo.entity;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import javax.transaction.Transactional;
import java.util.List;

@RepositoryRestResource
@Transactional
public interface TeacherRepository extends JpaRepository<Teacher,String> {
    @Query("select  userid from Teacher where  academic=?1")
    public List findIdByAcademic(String academic);

    @Query("select name from Teacher where id=?1")
    public String findNameByTeacherId(String teacherid);
}
