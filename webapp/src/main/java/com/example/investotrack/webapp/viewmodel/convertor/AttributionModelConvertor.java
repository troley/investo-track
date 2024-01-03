package com.example.investotrack.webapp.viewmodel.convertor;

import com.example.investotrack.dataprovidercore.model.Attribution;
import com.example.investotrack.webapp.viewmodel.AttributionViewModel;
import org.springframework.stereotype.Component;

@Component
public class AttributionModelConvertor {

    public AttributionViewModel toViewModel(Attribution attribution) {
        return new AttributionViewModel(attribution.getBrand(), attribution.getUrl().orElse(null));
    }
}
