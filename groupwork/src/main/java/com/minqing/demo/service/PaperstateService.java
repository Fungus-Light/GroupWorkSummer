package com.minqing.demo.service;


import com.minqing.demo.entity.Paperstate;
import com.minqing.demo.entity.PaperstateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PaperstateService {
    @Autowired
    private PaperstateRepository paperstateRepository;
    public int haspassed(String studentid)
    {
        return paperstateRepository.findById(studentid).get().getState()==1?1:0;
    }
    public void initPaper(String studentid)
    {
        Paperstate paperstate=new Paperstate();
        paperstate.setState(0);
        paperstate.setStudentid(studentid);
        paperstateRepository.save(paperstate);

    }
    public void passPaperstate(String studentid)
    {
        Paperstate paperstate=new Paperstate();
        paperstate.setState(1);
        paperstate.setStudentid(studentid);
        paperstateRepository.save(paperstate);
    }
    public void refusePaperstate(String studentid)
    {
        Paperstate paperstate=new Paperstate();
        paperstate.setState(2);
        paperstate.setStudentid(studentid);
        paperstateRepository.save(paperstate);
    }
    public int hasUploaded(String studentid)
    {
        return  paperstateRepository.findById(studentid).isPresent()?1:0;
    }

    public int findStudentState(String userid){
        return paperstateRepository.findById(userid).get().getState();
    }
}
