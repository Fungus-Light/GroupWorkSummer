package com.minqing.demo.service;


import com.minqing.demo.entity.Record;
import com.minqing.demo.entity.RecordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RecodeService {
    @Autowired
    private RecordRepository recordRepository;

    public void addRecode(String studentid,String teacherid,String content){
        Record record = new Record();
        record.setStudentid(studentid);
        record.setStudentid(teacherid);
        record.setContent(content);
        recordRepository.save(record);
    }

    public void delete(int recordid){
        recordRepository.deleteById(recordid);
    }
    
}
