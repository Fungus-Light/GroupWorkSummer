package com.minqing.demo.controller;

import com.minqing.demo.entity.Period;
import com.minqing.demo.service.PeriodService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController

public class PeriodController {
    private PeriodService periodService;
    @RequestMapping("/setPeriod")
    public void setPeriod(@RequestBody Map<String,Integer> m)
    {
        periodService.setPeriod(m.get("segid"));
    }
    @RequestMapping("/showPeriod")
    public Period showPeriod(){
        return periodService.findById();
    }
}
