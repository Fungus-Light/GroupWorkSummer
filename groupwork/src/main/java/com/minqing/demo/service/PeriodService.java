package com.minqing.demo.service;

import com.minqing.demo.entity.Period;
import com.minqing.demo.entity.PeriodRepository;
import org.springframework.stereotype.Service;

@Service
public class PeriodService {
    private PeriodRepository periodRepository;

    public void setPeriod(Integer status) {
        Period p=new Period();
        p.setStatus(status);
        periodRepository.save(p);
    }
    public Period findById(Integer status)
    {
        return periodRepository.findById(status).get();
    }
}
