package com.example.investotrack.webapp;

import com.example.investotrack.webapp.service.CurrencyDataProviderService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@EnableCaching
@SpringBootApplication
public class WebappApplication implements CommandLineRunner {

	private final CurrencyDataProviderService currencyDataProviderService;

	public WebappApplication(CurrencyDataProviderService currencyDataProviderService) {
		this.currencyDataProviderService = currencyDataProviderService;
	}

	public static void main(String[] args) {
		SpringApplication.run(WebappApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		// Do a pre-fetch, so that the next client that requests
		// the currencies will get them (fast) from the cache.
		currencyDataProviderService.listAllCurrencies();
	}
}
