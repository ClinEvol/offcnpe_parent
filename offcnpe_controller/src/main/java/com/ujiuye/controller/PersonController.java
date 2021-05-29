package com.ujiuye.controller;

import com.ujiuye.service.PersonService;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/person")
public class PersonController {
    @Reference
    private PersonService personService;

    @RequestMapping("/get")
    public String getPerson(){
        return personService.getPerson();
    }
}
