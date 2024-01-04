package com.example.investotrack.webapp.viewmodel.convertor;

import com.example.investotrack.dataprovidercore.model.Attribution;
import com.example.investotrack.webapp.viewmodel.AttributionViewModel;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class AttributionModelConvertorTest {

    private final AttributionModelConvertor convertor = new AttributionModelConvertor();

    @Test
    void toViewModel_convertsSuccessfully() {
        Attribution attribution = new Attribution("testbrand", "http://testurl.org");

        AttributionViewModel viewModel = convertor.toViewModel(attribution);

        assertEquals(attribution.getBrand(), viewModel.getBrand());
        assertEquals(attribution.getUrl(), viewModel.getUrl());
    }

    @Test
    void toViewModel_nullUrl_convertsSuccessfully() {
        Attribution attribution = new Attribution("testbrand", null);

        AttributionViewModel viewModel = convertor.toViewModel(attribution);

        assertEquals(attribution.getBrand(), viewModel.getBrand());
        assertTrue(viewModel.getUrl().isEmpty());
    }
}
