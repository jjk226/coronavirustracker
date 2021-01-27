package com.example.coronavirustracker.controller;

import com.example.coronavirustracker.model.LocationStat;
import com.example.coronavirustracker.service.CoronavirusDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class HomeController {

    @Autowired
    private CoronavirusDataService dataService;

    @GetMapping("/")
    public String home(Model model) {
        List<LocationStat> locations = this.dataService.getLocationStats();
        int totalCases = this.dataService.getTotalCases();
        int totalChange = this.dataService.getTotalChange();
        model.addAttribute("totalCases", totalCases);
        model.addAttribute("totalChange", totalChange);
        model.addAttribute("locations", locations);

        return "home";
    }
}
