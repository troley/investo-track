package com.example.investotrack.webapp.controller;

import com.example.investotrack.webapp.service.CurrencyDataProviderService;
import com.example.investotrack.webapp.validation.ValidApiVersion;
import com.example.investotrack.webapp.viewmodel.CurrencyViewModel;
import jakarta.validation.constraints.NotNull;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@Validated
@RestController
@RequestMapping("/api/v{version}/currency")
public class CurrencyApiController {

    @NotNull
    private final CurrencyDataProviderService currencyDataProviderService;

    public CurrencyApiController(CurrencyDataProviderService currencyDataProviderService) {
        this.currencyDataProviderService = currencyDataProviderService;
    }

    @GetMapping("/list_all")
    public Collection<CurrencyViewModel> listAll(@PathVariable @ValidApiVersion String version) {
        return currencyDataProviderService.listAllCurrencies();
    }

    @GetMapping("/find_by")
    public Collection<CurrencyViewModel> findBy(@RequestParam("q") String query,
                                                @PathVariable @ValidApiVersion String version) {
        return currencyDataProviderService.findCaseInsensitiveCurrenciesBy(query);
    }
}
