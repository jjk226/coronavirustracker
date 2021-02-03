package com.example.coronavirustracker.service;

import com.example.coronavirustracker.model.LocationStat;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.xml.stream.Location;
import java.io.IOException;
import java.io.StringReader;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

@Service
public interface CoronavirusDataService {

    public void fetchData() throws IOException, InterruptedException;

    public List<LocationStat> getLocationStats();

    public int getTotalCases();

    public int getTotalChange();

    public LocationStat getByCountry(String country);

}
