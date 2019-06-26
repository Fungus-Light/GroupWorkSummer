package com.example.demo.entity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.*;
@RepositoryRestResource
@Transactional
public interface userRepository extends JpaRepository <user,String>{

}
