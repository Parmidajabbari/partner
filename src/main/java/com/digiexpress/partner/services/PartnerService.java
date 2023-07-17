package com.digiexpress.partner.services;

import com.digiexpress.partner.services.dto.PartnerDTO;

public interface PartnerService {
    PartnerDTO savePartner(PartnerDTO partnerDTO);
    PartnerDTO getPartnerById(Long id);
    PartnerDTO searchPartnerByLocation(Double lng, Double lat);

}
