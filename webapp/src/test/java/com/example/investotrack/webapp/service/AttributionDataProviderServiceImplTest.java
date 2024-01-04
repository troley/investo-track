package com.example.investotrack.webapp.service;

import com.example.investotrack.dataprovidercore.AttributionDataProvider;
import com.example.investotrack.dataprovidercore.model.Attribution;
import com.example.investotrack.webapp.viewmodel.AttributionViewModel;
import com.example.investotrack.webapp.viewmodel.convertor.AttributionModelConvertor;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AttributionDataProviderServiceImplTest {

    @Mock
    private AttributionDataProvider attributionDataProvider;

    @Mock
    private AttributionModelConvertor attributionModelConvertor;

    @InjectMocks
    private AttributionDataProviderServiceImpl attributionDataProviderService;

    @Test
    void getAttribution_returnsAttribution() {
        // Arrange
        Attribution attribution = new Attribution("testbrand", "http://testurl.org");
        AttributionViewModel viewModel = new AttributionViewModel(attribution.getBrand(), attribution.getUrl().get());

        when(attributionDataProvider.getAttribution()).thenReturn(attribution);
        when(attributionModelConvertor.toViewModel(any(Attribution.class))).thenReturn(viewModel);

        // Act
        AttributionViewModel result = attributionDataProviderService.getAttribution().get();

        // Assert
        assertEquals(viewModel, result);
    }

    @Test
    void getAttribution_nullDataProvider_returnsEmptyOptional() {
        // Arrange
        attributionDataProviderService = new AttributionDataProviderServiceImpl(null, attributionModelConvertor);

        // Act
        Optional<AttributionViewModel> result = attributionDataProviderService.getAttribution();

        // Assert
        assertTrue(result.isEmpty());
    }

    @Test
    void getAttribution_nullAttributionBrand_returnsEmptyOptional() {
        // Arrange
        Attribution attribution = new Attribution(null, "http://testurl.org");

        when(attributionDataProvider.getAttribution()).thenReturn(attribution);

        // Act
        Optional<AttributionViewModel> result = attributionDataProviderService.getAttribution();

        // Assert
        assertTrue(result.isEmpty());
    }

    @Test
    void getAttribution_nullAttributionUrl_returnsAttribution() {
        // Arrange
        Attribution attribution = new Attribution("testbrand", null);
        AttributionViewModel viewModel = new AttributionViewModel(attribution.getBrand(), null);

        when(attributionDataProvider.getAttribution()).thenReturn(attribution);
        when(attributionModelConvertor.toViewModel(any(Attribution.class))).thenReturn(viewModel);

        // Act
        AttributionViewModel result = attributionDataProviderService.getAttribution().get();

        // Assert
        assertEquals(viewModel, result);
    }
}
