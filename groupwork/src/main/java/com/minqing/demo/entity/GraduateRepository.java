package com.minqing.demo.entity;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import javax.persistence.Entity;
import javax.transaction.Transactional;
import java.util.List;

@RepositoryRestResource
@Transactional
public interface GraduateRepository extends JpaRepository<Graduate,String> {
    @Query("select teacherpass from Graduate where studentid=?1")
    public Integer findTeacherpassById(String id);
    @Query("select managerpass from Graduate where studentid=?1")
    public Integer findManagerpassById(String id);
    @Modifying
    @Query("UPDATE Graduate set teacherpass = 1 where studentid =?1")
    public void TeacherAgreed(String studentid);
    @Modifying
    @Query("UPDATE Graduate set managerpass = 1 where studentid =?1")
    public void ManagerAgreed(String studentid);

    @Query("select studentid from Graduate where teachername =?1")
    public List findStudentidsByTeacherName(String teachername);

    @Query("select studentid from Graduate g,Manager m,Student s where s.userid=g.studentid and" +
            " m.academic=s.academic and m.academic=?1 ")
    public List findStudentidsByManagerAcademic(String academic);
}
