package com.minqing.demo.service;

import com.minqing.demo.entity.Period;
import com.minqing.demo.entity.PeriodRepository;
import org.springframework.stereotype.Service;

@Service
public class PeriodService {
    private PeriodRepository periodRepository;

    public void setPeriod(Integer segid)
    {
        Period p=new Period();
        p.setStatus(1);
        p.setSegid(segid);
        periodRepository.save(p);
    }
    public Period findById(Integer id)
    {
        return periodRepository.findById(id).get();
    }
}
