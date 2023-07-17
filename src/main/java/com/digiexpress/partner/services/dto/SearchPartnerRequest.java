package com.digiexpress.partner.services.dto;

import com.digiexpress.partner.web.validation.LatValidation;
import com.digiexpress.partner.web.validation.LngValidation;
import lombok.Data;

@Data
public class SearchPartnerRequest {
    @LngValidation
    private Double lng;
    @LatValidation
    private Double lat;
}
