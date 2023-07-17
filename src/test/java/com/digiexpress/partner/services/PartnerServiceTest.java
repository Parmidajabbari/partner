package com.digiexpress.partner.services;

import com.digiexpress.partner.domains.Partner;
import com.digiexpress.partner.mapper.PartnerMapper;
import com.digiexpress.partner.repositories.PartnerRepository;
import com.digiexpress.partner.services.dto.PartnerDTO;
import com.digiexpress.partner.web.exception.BadRequestException;
import com.digiexpress.partner.web.exception.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.geo.Point;
import org.springframework.data.mongodb.core.geo.GeoJsonMultiPolygon;
import org.springframework.data.mongodb.core.geo.GeoJsonPoint;
import org.springframework.test.context.ContextConfiguration;

import java.util.ArrayList;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyDouble;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ContextConfiguration(classes = {PartnerService.class})
@ExtendWith(MockitoExtension.class)
class PartnerServiceTest {

    private PartnerService partnerService;

    @Mock
    private PartnerRepository partnerRepository;

    @Mock
    private PartnerMapper partnerMapper;

    @BeforeEach
    void setUp() {
        partnerService = new PartnerServiceImpl(partnerRepository, partnerMapper);
    }

    @Test
    void savePartnerWhenExists() {
        // given
        PartnerDTO partnerDTO = new PartnerDTO();
        partnerDTO.setDocument("123456789");

        //when
        when(partnerRepository.existsByDocument(any())).thenReturn(true);

        //then
        assertThrows(BadRequestException.class, () -> partnerService.savePartner(partnerDTO));
    }


    @Test
    void testSavePartner() {
        // when
        when(partnerRepository.existsByDocument(Mockito.<String>any())).thenReturn(true);
        assertThrows(BadRequestException.class, () -> partnerService.savePartner(new PartnerDTO()));
        //then
        verify(partnerRepository).existsByDocument(Mockito.<String>any());
    }

    @Test
    void testSavePartner2() {
        Partner partner = new Partner();
        partner.setAddress(new GeoJsonPoint(2.0d, 3.0d));
        partner.setCoverageArea(new GeoJsonMultiPolygon(new ArrayList<>()));
        partner.setDocument("Document");
        partner.setId(1L);
        partner.setOwnerName("Owner Name");
        partner.setTradingName("Trading Name");
        when(partnerRepository.existsByDocument(Mockito.<String>any())).thenReturn(false);
        when(partnerRepository.save(Mockito.<Partner>any())).thenReturn(partner);

        Partner partner2 = new Partner();
        partner2.setAddress(new GeoJsonPoint(2.0d, 3.0d));
        partner2.setCoverageArea(new GeoJsonMultiPolygon(new ArrayList<>()));
        partner2.setDocument("Document");
        partner2.setId(1L);
        partner2.setOwnerName("Owner Name");
        partner2.setTradingName("Trading Name");
        when(partnerMapper.toEntity(Mockito.<PartnerDTO>any())).thenReturn(partner2);
        PartnerDTO partnerDTO = new PartnerDTO();
        when(partnerMapper.toDTO(Mockito.<Partner>any())).thenReturn(partnerDTO);
        assertSame(partnerDTO, partnerService.savePartner(new PartnerDTO()));
        verify(partnerRepository).existsByDocument(Mockito.<String>any());
        verify(partnerRepository).save(Mockito.<Partner>any());
        verify(partnerMapper).toEntity(Mockito.<PartnerDTO>any());
        verify(partnerMapper).toDTO(Mockito.<Partner>any());
    }

    @Test
    void testSavePartner3() {
        when(partnerRepository.existsByDocument(Mockito.<String>any())).thenReturn(false);
        when(partnerMapper.toEntity(Mockito.<PartnerDTO>any()))
                .thenThrow(new EntityNotFoundException("An error occurred"));
        assertThrows(EntityNotFoundException.class, () -> partnerService.savePartner(new PartnerDTO()));
        verify(partnerRepository).existsByDocument(Mockito.<String>any());
        verify(partnerMapper).toEntity(Mockito.<PartnerDTO>any());
    }

    //check if partner gets saved
    @Test
    void savePartner() {
        // given
        PartnerDTO partnerDTO = new PartnerDTO();
        partnerDTO.setDocument("123456789");
        Partner partner = new Partner();
        partner.setDocument("123456789");
        when(partnerRepository.existsByDocument(any())).thenReturn(false);
        when(partnerMapper.toEntity(any())).thenReturn(partner);
        when(partnerMapper.toDTO(any())).thenReturn(partnerDTO);

        // when
        PartnerDTO savedPartner = partnerService.savePartner(partnerDTO);

        // then
        assertEquals(partnerDTO.getDocument(), savedPartner.getDocument());
        assertEquals(partnerDTO.getTradingName(), savedPartner.getTradingName());
        assertEquals(partnerDTO.getOwnerName(), savedPartner.getOwnerName());
        assertEquals(partnerDTO.getCoverageArea(), savedPartner.getCoverageArea());
        assertEquals(partnerDTO.getAddress(), savedPartner.getAddress());
    }

    @Test
    void getPartnerByIdWhenExists() {
        // given
        Long partnerId = 1L;
        Partner partner = new Partner();
        partner.setId(partnerId);
        PartnerDTO partnerDTO = new PartnerDTO();
        partnerDTO.setId(partnerId);
        when(partnerRepository.findById(any())).thenReturn(Optional.of(partner));
        when(partnerMapper.toDTO(any())).thenReturn(partnerDTO);

        // when
        PartnerDTO result = partnerService.getPartnerById(partnerId);

        // then
        assertEquals(partnerDTO, result);
    }

    @Test
    void testGetPartnerById() {
        Partner partner = new Partner();
        partner.setAddress(new GeoJsonPoint(2.0d, 3.0d));
        partner.setCoverageArea(new GeoJsonMultiPolygon(new ArrayList<>()));
        partner.setDocument("Document");
        partner.setId(1L);
        partner.setOwnerName("Owner Name");
        partner.setTradingName("Trading Name");
        Optional<Partner> ofResult = Optional.of(partner);
        when(partnerRepository.findById(Mockito.<Long>any())).thenReturn(ofResult);
        PartnerDTO partnerDTO = new PartnerDTO();
        when(partnerMapper.toDTO(Mockito.<Partner>any())).thenReturn(partnerDTO);
        assertSame(partnerDTO, partnerService.getPartnerById(1L));
        verify(partnerRepository).findById(Mockito.<Long>any());
        verify(partnerMapper).toDTO(Mockito.<Partner>any());
    }

    @Test
    void testGetPartnerById2() {
        Partner partner = new Partner();
        partner.setAddress(new GeoJsonPoint(2.0d, 3.0d));
        partner.setCoverageArea(new GeoJsonMultiPolygon(new ArrayList<>()));
        partner.setDocument("Document");
        partner.setId(1L);
        partner.setOwnerName("Owner Name");
        partner.setTradingName("Trading Name");
        Optional<Partner> ofResult = Optional.of(partner);
        when(partnerRepository.findById(Mockito.<Long>any())).thenReturn(ofResult);
        when(partnerMapper.toDTO(Mockito.<Partner>any())).thenThrow(new BadRequestException("An error occurred"));
        assertThrows(BadRequestException.class, () -> partnerService.getPartnerById(1L));
        verify(partnerRepository).findById(Mockito.<Long>any());
        verify(partnerMapper).toDTO(Mockito.<Partner>any());
    }

    @Test
    void testGetPartnerById3() {
        when(partnerRepository.findById(Mockito.<Long>any())).thenReturn(Optional.empty());
        assertThrows(EntityNotFoundException.class, () -> partnerService.getPartnerById(1L));
        verify(partnerRepository).findById(Mockito.<Long>any());
    }

    @Test
    void getPartnerByIdWhenNotExists() {
        // given
        Long partnerId = 1L;

        //when
        when(partnerRepository.findById(any())).thenReturn(Optional.empty());

        // then
        assertThrows(EntityNotFoundException.class, () -> partnerService.getPartnerById(partnerId));
    }

    @Test
    void searchPartnerByLocationWhenExists() {
        // given
        Double lng = 1.0;
        Double lat = 2.0;
        GeoJsonPoint location = new GeoJsonPoint(new Point(lng, lat));
        Partner partner = new Partner();
        PartnerDTO partnerDTO = new PartnerDTO();
        when(partnerRepository.findByLocationNearAndCoverageAreaIntersecting(any(), anyDouble(), anyDouble())).thenReturn(Optional.of(partner));
        when(partnerMapper.toDTO(any())).thenReturn(partnerDTO);

        // when
        PartnerDTO result = partnerService.searchPartnerByLocation(lng, lat);

        // then
        assertEquals(partnerDTO, result);
    }


    @Test
    void testSearchPartnerByLocation() {
        Partner partner = new Partner();
        partner.setAddress(new GeoJsonPoint(2.0d, 3.0d));
        partner.setCoverageArea(new GeoJsonMultiPolygon(new ArrayList<>()));
        partner.setDocument("Document");
        partner.setId(1L);
        partner.setOwnerName("Owner Name");
        partner.setTradingName("Trading Name");
        Optional<Partner> ofResult = Optional.of(partner);
        when(partnerRepository.findByLocationNearAndCoverageAreaIntersecting(Mockito.<String>any(), Mockito.<Double>any(),
                Mockito.<Double>any())).thenReturn(ofResult);
        PartnerDTO partnerDTO = new PartnerDTO();
        when(partnerMapper.toDTO(Mockito.<Partner>any())).thenReturn(partnerDTO);
        assertSame(partnerDTO, partnerService.searchPartnerByLocation(10.0d, 10.0d));
        verify(partnerRepository).findByLocationNearAndCoverageAreaIntersecting(Mockito.<String>any(),
                Mockito.<Double>any(), Mockito.<Double>any());
        verify(partnerMapper).toDTO(Mockito.<Partner>any());
    }


    @Test
    void testSearchPartnerByLocation2() {
        Partner partner = new Partner();
        partner.setAddress(new GeoJsonPoint(2.0d, 3.0d));
        partner.setCoverageArea(new GeoJsonMultiPolygon(new ArrayList<>()));
        partner.setDocument("Document");
        partner.setId(1L);
        partner.setOwnerName("Owner Name");
        partner.setTradingName("Trading Name");
        Optional<Partner> ofResult = Optional.of(partner);
        when(partnerRepository.findByLocationNearAndCoverageAreaIntersecting(Mockito.<String>any(), Mockito.<Double>any(),
                Mockito.<Double>any())).thenReturn(ofResult);
        when(partnerMapper.toDTO(Mockito.<Partner>any())).thenThrow(new BadRequestException("An error occurred"));
        assertThrows(BadRequestException.class, () -> partnerService.searchPartnerByLocation(10.0d, 10.0d));
        verify(partnerRepository).findByLocationNearAndCoverageAreaIntersecting(Mockito.<String>any(),
                Mockito.<Double>any(), Mockito.<Double>any());
        verify(partnerMapper).toDTO(Mockito.<Partner>any());
    }

    @Test
    void testSearchPartnerByLocation3() {
        when(partnerRepository.findByLocationNearAndCoverageAreaIntersecting(Mockito.<String>any(), Mockito.<Double>any(),
                Mockito.<Double>any())).thenReturn(Optional.empty());
        assertThrows(EntityNotFoundException.class, () -> partnerService.searchPartnerByLocation(10.0d, 10.0d));
        verify(partnerRepository).findByLocationNearAndCoverageAreaIntersecting(Mockito.<String>any(),
                Mockito.<Double>any(), Mockito.<Double>any());
    }

    @Test
    void searchPartnerByLocationWhenNotExists() {
        // given
        Double lng = 1.0;
        Double lat = 2.0;

        //when
        when(partnerRepository.findByLocationNearAndCoverageAreaIntersecting(any(), anyDouble(), anyDouble())).thenReturn(Optional.empty());

        // then
        assertThrows(EntityNotFoundException.class, () -> partnerService.searchPartnerByLocation(lng, lat));
    }
}