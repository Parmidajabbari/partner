package com.digiexpress.partner.services.dto;


import com.digiexpress.partner.serializer.GeoJsonMultiPolygonSerializer;
import com.digiexpress.partner.serializer.PointSerializer;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.*;
import org.springframework.data.mongodb.core.geo.GeoJsonMultiPolygon;
import org.springframework.data.mongodb.core.geo.GeoJsonPoint;

import javax.validation.constraints.NotEmpty;

@Data
@RequiredArgsConstructor
@NoArgsConstructor
public class PartnerDTO {
    private Long id;
    @NotEmpty
    @NonNull
    private String tradingName;
    @NotEmpty
    @NonNull
    private String ownerName;
    @NotEmpty
    @NonNull
    private String document;
    @NonNull
    @JsonProperty("coverageArea")
    @JsonSerialize(using = GeoJsonMultiPolygonSerializer.class)
    private GeoJsonMultiPolygon coverageArea;
    @NonNull
    @JsonProperty("address")
    @JsonSerialize(using = PointSerializer.class)
    private GeoJsonPoint address;
}
