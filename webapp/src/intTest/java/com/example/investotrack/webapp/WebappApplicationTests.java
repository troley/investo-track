package com.example.investotrack.webapp;

import com.example.investotrack.dataprovidercore.CurrencyDataProvider;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

@SpringBootTest
class WebappApplicationTests {

	@MockBean
	private CurrencyDataProvider currencyDataProvider;

	@Test
	void contextLoads() {
	}

}
