package com.ujiuye.service.impl;

import com.ujiuye.service.PersonService;
import org.apache.dubbo.config.annotation.Service;

@Service
public class PersonServiceImpl implements PersonService {

    public String getPerson(){
        return "yes";
    }
}
