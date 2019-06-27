package com.minqing.demo.controller;

import com.minqing.demo.entity.Period;
import com.minqing.demo.service.PeriodService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController

public class PeriodController {
    private PeriodService periodService;
    @RequestMapping("/setPeriod")
    public void setPeriod(@RequestBody Map<String,Integer> m)
    {
        periodService.setPeriod(m.get("segid"));
    }
    /*@RequestMapping("/showPeriod")
    public List<Period> showPeriod(){
        List<Period> periods=new ArrayList<>();
        for(int i=0;i<4;i++)
        {
            //
        }
    }*/
}
