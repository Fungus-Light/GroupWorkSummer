package com.minqing.demo.service;


import com.minqing.demo.entity.Record;
import com.minqing.demo.entity.RecordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RecordService {
    @Autowired
    private RecordRepository recordRepository;

    public void addRecord(String studentid,String teacherid,String content){
        Record record = new Record();
        record.setStudentid(studentid);
        record.setTeacherid(teacherid);
        record.setContent(content);
        recordRepository.save(record);
    }

    public List findRecord(String studentid,String teacherid){
        List<Record> list= recordRepository.findRecordsByStudentidAndTeacherid(studentid,teacherid);
        List<String> newlist = new ArrayList<>();
        for(int i=0;i<list.size();i++){
            newlist.add(list.get(i).getContent());
        }
        return newlist;
    }

    public List findRecordByStudent(String studentid){
        return recordRepository.findRecordsByStudentid(studentid);
    }
}
