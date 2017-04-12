package com.nghiavuquansu.controller;

import java.util.Date;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.nghiavuquansu.model.AgeUtil;

@Controller
public class DateCalculateAgeController {
    
    @PostMapping(value ="/setdatecalculate")
    public @ResponseBody String setDateToCalculateAge(@RequestBody String strDate){
        try {
            AgeUtil.setDateCalculateAge(AgeUtil.getDateFromString(strDate));
            return "OK";
        } catch (Exception e) {
            e.printStackTrace();
            return "NOK";
        }
    }
}
