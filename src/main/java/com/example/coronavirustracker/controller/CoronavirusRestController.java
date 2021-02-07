package com.example.coronavirustracker.controller;

import com.example.coronavirustracker.model.LocationStat;
import com.example.coronavirustracker.service.CoronavirusDataService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rest")
public class CoronavirusRestController {

    private CoronavirusDataService dataService;

    public CoronavirusRestController(CoronavirusDataService dataService) {
        this.dataService = dataService;
    }

    @GetMapping("/all")
    public List<LocationStat> all() {
        return this.dataService.getLocationStats();
    }

    @GetMapping("/{country}")
    public LocationStat country(@PathVariable String country) {
        return dataService.getByCountry(country);
    }
}
