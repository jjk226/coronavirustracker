package com.example.coronavirustracker.controller;

import com.example.coronavirustracker.service.CoronavirusDataService;
import org.springframework.beans.factory.annotation.Autowired;


public class RestController {

    @Autowired
    private CoronavirusDataService dataService;


}
