package com.digiexpress.partner.mapper;

import com.digiexpress.partner.domains.Partner;
import com.digiexpress.partner.services.dto.PartnerDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PartnerMapper {
    PartnerDTO toDTO(Partner partner);
    Partner toEntity(PartnerDTO partnerDTO);
}
