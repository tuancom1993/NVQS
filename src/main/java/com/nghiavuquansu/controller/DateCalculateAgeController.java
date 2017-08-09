package com.nghiavuquansu.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.nghiavuquansu.common.DateUtils;

@Controller
public class DateCalculateAgeController {
    
    @PostMapping(value ="/setdatecalculate")
    public @ResponseBody String setDateToCalculateAge(@RequestBody String strDate){
        try {
            DateUtils.setDateCalculateAge(DateUtils.getDateFromString(strDate));
            return "OK";
        } catch (Exception e) {
            e.printStackTrace();
            return "NOK";
        }
    }
}
