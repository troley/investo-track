package com.example.investotrack.webapp.service;

import com.example.investotrack.dataprovidercore.AttributionDataProvider;
import com.example.investotrack.dataprovidercore.model.Attribution;
import com.example.investotrack.webapp.viewmodel.AttributionViewModel;
import com.example.investotrack.webapp.viewmodel.convertor.AttributionModelConvertor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AttributionDataProviderServiceImpl implements AttributionDataProviderService {

    private static final Logger log = LoggerFactory.getLogger(AttributionDataProviderServiceImpl.class);

    private final AttributionDataProvider attributionDataProvider;
    private final AttributionModelConvertor attributionModelConvertor;

    public AttributionDataProviderServiceImpl(
            @Autowired(required = false) @Nullable AttributionDataProvider attributionDataProvider,
            AttributionModelConvertor attributionModelConvertor) {
        this.attributionDataProvider = attributionDataProvider;
        this.attributionModelConvertor = attributionModelConvertor;
    }

    @Override
    public Optional<AttributionViewModel> getAttribution() {
        if (attributionDataProvider == null) {
            return Optional.empty();
        }
        Attribution attribution = attributionDataProvider.getAttribution();

        if (attribution.getBrand() == null) {
            log.warn("The 'brand' property of the attribution is not set.");
            return Optional.empty();
        }

        return Optional.of(attributionModelConvertor.toViewModel(attribution));
    }
}
