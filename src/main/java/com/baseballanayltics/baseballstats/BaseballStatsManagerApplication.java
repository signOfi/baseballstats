package com.baseballanayltics.baseballstats;

import com.baseballanayltics.baseballstats.service.CSVService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BaseballStatsManagerApplication implements CommandLineRunner {

	private final CSVService csvService;

	@Autowired
	public BaseballStatsManagerApplication(CSVService csvService) {
		this.csvService = csvService;
	}

	public static void main(String[] args) {
		SpringApplication.run(BaseballStatsManagerApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		csvService.getCsvData("static/mlb-player-stats-Batters.csv");
	}
}
