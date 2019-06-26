package com.example.demo.controller;

import com.example.demo.entity.Teacher;
import com.example.demo.service.Adminservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.ListIterator;
import java.util.Map;

@RestController
public class AdminController {
    @Autowired
    private Adminservice as;
    @RequestMapping("/UpdateTeacher")
   public int UpdateTeacher(@RequestBody Map<Object,String> map)
   {
       System.out.println("1111111111111111");
       Teacher temp=as.findTeacherById(map.get("userid"));
       Teacher newt=new Teacher();
       if(map.get("Tel").isEmpty())
           newt.setTel(temp.getTel());
       else
           newt.setTel(map.get("Tel"));
       if(map.get("name").isEmpty())
           newt.setName(temp.getName());
       else
           newt.setName(map.get("name"));
       if(map.get("academic").isEmpty())
           newt.setAcademic(temp.getAcademic());
       else
           newt.setAcademic(map.get("academic"));
       newt.setUserid(map.get("userid"));
       as.UpdateTeacherInf(newt);
       return 0;
   }
    @RequestMapping("/findall")
    public List<Teacher> FindAllTeachers()
    {
        return as.findAllTeacher();
    }
}
