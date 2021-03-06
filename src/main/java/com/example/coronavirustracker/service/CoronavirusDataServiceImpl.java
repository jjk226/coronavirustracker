package com.example.coronavirustracker.service;

import com.example.coronavirustracker.model.LocationStat;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.io.StringReader;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("SpellCheckingInspection")
@Service
public class CoronavirusDataServiceImpl implements CoronavirusDataService {

    private List<LocationStat> locationStats = new ArrayList<>();

    @Override
    @PostConstruct
    @Scheduled(cron="* * 1 * * *")
    public void fetchData() throws IOException, InterruptedException {
        String VIRUS_DATA_URL = "https://raw.githubusercontent.com/CSSEGISandData/COVID-19/master/csse_covid_19_data/csse_covid_19_time_series/time_series_covid19_confirmed_global.csv";

        List<LocationStat> newLocationStats = new ArrayList<>();

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder().uri(URI.create(VIRUS_DATA_URL)).build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        StringReader csvBodyReader = new StringReader(response.body());
        Iterable<CSVRecord> records = CSVFormat.DEFAULT.withFirstRecordAsHeader().parse(csvBodyReader);

        for (CSVRecord record : records) {

            LocationStat tempLocationStat = new LocationStat();

            tempLocationStat.setState(record.get("Province/State"));
            tempLocationStat.setCountry(record.get("Country/Region"));

            int latest = Integer.valueOf(record.get(record.size() - 1));
            tempLocationStat.setLatest(latest);

            int previous = Integer.valueOf(record.get(record.size() - 2));
            tempLocationStat.setPrevious(previous);

            tempLocationStat.setChange(latest - previous);
            newLocationStats.add(tempLocationStat);
        }

        this.locationStats = newLocationStats;

        for (LocationStat location: this.locationStats) {
            System.out.println(location);
        }
    }

    @Override
    public List<LocationStat> getLocationStats() {
        return this.locationStats;
    }

    @Override
    public int getTotalCases() {

        return this.locationStats.stream().mapToInt(location -> location.getLatest()).sum();
    }

    @Override
    public int getTotalChange() {

        return this.locationStats.stream().mapToInt(location -> location.getChange()).sum();
    }

    @Override
    public LocationStat getByCountry(String country) {
        for (LocationStat location: this.locationStats) {
            if (location.getCountry().equals(country)) {
                return location;
            }
        }

        return null;
    }

}
