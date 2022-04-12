package com.javaproject.coronavirustracker.controllers;


import com.javaproject.coronavirustracker.models.LocationStats;
import com.javaproject.coronavirustracker.services.CoronaVirusDataServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class HomeController {

    @Autowired
    CoronaVirusDataServices coronaVirusDataServices;

    @GetMapping("/")
    public String home(Model model)
    {
        List<LocationStats> allStats = coronaVirusDataServices.getAllStats();
        int totalReportedCases = allStats.stream().mapToInt(stat -> stat.getLatestTotalCases()).sum();
        int totalNewCases = allStats.stream().mapToInt(stat -> stat.getDiffFromPreviousDay()).sum();
        model.addAttribute("totalNewCases" , totalNewCases );
        model.addAttribute("locationStats" , allStats );
        model.addAttribute("totalReportedCases" , totalReportedCases );
        return "home";
    }
}
