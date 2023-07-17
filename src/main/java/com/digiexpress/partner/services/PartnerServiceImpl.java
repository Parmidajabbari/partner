package com.digiexpress.partner.services;

import com.digiexpress.partner.domains.Partner;
import com.digiexpress.partner.mapper.PartnerMapper;
import com.digiexpress.partner.repositories.PartnerRepository;
import com.digiexpress.partner.services.dto.PartnerDTO;
import com.digiexpress.partner.web.exception.BadRequestException;
import com.digiexpress.partner.web.exception.EntityNotFoundException;
import org.springframework.data.geo.Point;
import org.springframework.data.mongodb.core.geo.GeoJsonPoint;
import org.springframework.stereotype.Service;

@Service
public class PartnerServiceImpl implements PartnerService {
    private final PartnerRepository partnerRepository;
    private final PartnerMapper partnerMapper;

    public PartnerServiceImpl(PartnerRepository partnerRepository, PartnerMapper partnerMapper) {
        this.partnerRepository = partnerRepository;
        this.partnerMapper = partnerMapper;
    }

    @Override
    public PartnerDTO savePartner(PartnerDTO partnerDTO) {
        if (partnerRepository.existsByDocument(partnerDTO.getDocument())) {
            throw new BadRequestException("Entity with this document already exists");
        }
        Partner partner = partnerMapper.toEntity(partnerDTO);
        return partnerMapper.toDTO(partnerRepository.save(partner));
    }

    @Override
    public PartnerDTO getPartnerById(Long id) {
       Partner partner = partnerRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Partner not found."));
       return partnerMapper.toDTO(partner);
    }

    @Override
    public PartnerDTO searchPartnerByLocation(Double lng, Double lat) {
        GeoJsonPoint location = new GeoJsonPoint(new Point(lng, lat));
        Partner partner =  partnerRepository.findByLocationNearAndCoverageAreaIntersecting("Multipolygon", location.getX(), location.getY())
                .orElseThrow(() -> new EntityNotFoundException("No such a partner found in this area"));
        return partnerMapper.toDTO(partner);
    }
}
