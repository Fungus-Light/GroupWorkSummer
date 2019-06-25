package com.minqing.demo.service;

import com.minqing.demo.entity.Manager;
import com.minqing.demo.entity.ManagerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ManagerService {
    @Autowired
    private ManagerRepository managerRepository;

    public void addManager(String userid,String name,String tel){
        Manager manager = new Manager();
        manager.setUserid(userid);
        manager.setName(name);
        manager.setTel(tel);
        managerRepository.save(manager);
    }

    public void deleteManager(String userid){
        managerRepository.deleteById(userid);
    }

    public List<Manager> findManagers(){
        return managerRepository.findAll();
    }

    public Manager findManager(String userid){
        return managerRepository.findById(userid).get();
    }
}
