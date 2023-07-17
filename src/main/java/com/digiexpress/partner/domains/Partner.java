package com.digiexpress.partner.domains;

import com.digiexpress.partner.serializer.GeoJsonMultiPolygonSerializer;
import com.digiexpress.partner.serializer.PointSerializer;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.geo.GeoJsonMultiPolygon;
import org.springframework.data.mongodb.core.geo.GeoJsonPoint;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Document
@JsonIgnoreProperties(ignoreUnknown = true)
public class Partner {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "partner_generator")
    @SequenceGenerator(name = "partner_generator", sequenceName = "partner_seq")
    @Column(name = "id", updatable = false, nullable = false)
    private Long id;
    @Column(name = "trading_name")
    private String tradingName;
    @Column(name = "owner_name")
    private String ownerName;
    @Indexed(unique = true)
    private String document;
    @JsonProperty("coverageArea")
    @JsonSerialize(using = GeoJsonMultiPolygonSerializer.class)
    private GeoJsonMultiPolygon coverageArea;
    @JsonProperty("address")
    @JsonSerialize(using = PointSerializer.class)
    private GeoJsonPoint address;
}

