package com.myprojects.coronavirustracker.services;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.myprojects.coronavirustracker.models.CoronaVirusTrackerEntity;

@Service
public class CoronaVirusTrackerService {

	private static final String COVID_DATA_URL_PREFIX = "https://raw.githubusercontent.com/CSSEGISandData/COVID-19/master/csse_covid_19_data/csse_covid_19_time_series/";

	private static final String COVID_CONFIRMED_CASES_URL = COVID_DATA_URL_PREFIX
			+ "time_series_covid19_confirmed_global.csv";

	private static final String COVID_DEATHS_URL = COVID_DATA_URL_PREFIX + "time_series_covid19_deaths_global.csv";

	private static final String COVID_RECOVERED_URL = COVID_DATA_URL_PREFIX
			+ "time_series_covid19_recovered_global.csv";

	private List<CoronaVirusTrackerEntity> covidLocationStats = new ArrayList<>();
	
	public void setCovidLocationStats(List<CoronaVirusTrackerEntity> covidLocationStats) {
		this.covidLocationStats = covidLocationStats;
	}

	@PostConstruct
	@Scheduled(cron = "0 0 1 * * *") // Gets updated every 1st hr of the day i.e. @ 1 am
	public void readDataFromUrl() throws IOException {
		
		List<CoronaVirusTrackerEntity> locationStats = new ArrayList<>();
		
		URL confirmed_cases_url = new URL(COVID_CONFIRMED_CASES_URL);
		
		CSVFormat csvFormat = CSVFormat.DEFAULT.withFirstRecordAsHeader().withIgnoreHeaderCase();
		
		System.out.println("Loading covid data from URL: " + confirmed_cases_url.toString());
		try(CSVParser csvParser = CSVParser.parse(confirmed_cases_url, StandardCharsets.UTF_8, csvFormat)) {
            for(CSVRecord csvRecord : csvParser) {
            	CoronaVirusTrackerEntity covidLocationStat = new CoronaVirusTrackerEntity();
            	covidLocationStat.setState(csvRecord.get("Province/State"));
                covidLocationStat.setCountry(csvRecord.get("Country/Region"));
                covidLocationStat.setLatestCasesConfirmed(Integer.parseInt(csvRecord.get(csvRecord.size() - 1)));
                locationStats.add(covidLocationStat);
            }
            this.covidLocationStats = locationStats;
            System.out.println("Loaded " + covidLocationStats.size() + " records from URL - " + confirmed_cases_url.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
		
	}
}
