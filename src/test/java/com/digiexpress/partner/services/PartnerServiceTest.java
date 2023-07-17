package com.digiexpress.partner.services;

import com.digiexpress.partner.domains.Partner;
import com.digiexpress.partner.mapper.PartnerMapper;
import com.digiexpress.partner.repositories.PartnerRepository;
import com.digiexpress.partner.services.dto.PartnerDTO;
import com.digiexpress.partner.web.exception.BadRequestException;
import com.digiexpress.partner.web.exception.EntityNotFoundException;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.geo.Point;
import org.springframework.data.mongodb.core.geo.GeoJsonMultiPolygon;
import org.springframework.data.mongodb.core.geo.GeoJsonPoint;

import java.util.Optional;

import org.springframework.test.context.ContextConfiguration;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
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
//        MockitoAnnotations.initMocks(this);
        partnerService = new PartnerServiceImpl(partnerRepository, partnerMapper);
    }

    @Test
    void savePartner_whenPartnerExists_shouldThrowBadRequestException() {
        // given
        PartnerDTO partnerDTO = new PartnerDTO();
        partnerDTO.setDocument("123456789");
        when(partnerRepository.existsByDocument(any())).thenReturn(true);

        // when, then
        assertThrows(BadRequestException.class, () -> partnerService.savePartner(partnerDTO));
    }


    @Test
    void testSavePartner() {
        when(partnerRepository.existsByDocument(Mockito.<String>any())).thenReturn(true);
        assertThrows(BadRequestException.class, () -> partnerService.savePartner(new PartnerDTO()));
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
//        assertSame(partnerDTO, partnerService.savePartner(new PartnerDTO()));
        verify(partnerRepository).existsByDocument(Mockito.<String>any());
        verify(partnerRepository).save(Mockito.<Partner>any());
        verify(partnerMapper).toEntity(Mockito.<PartnerDTO>any());
        verify(partnerMapper).toDTO(Mockito.<Partner>any());
    }
    @Test
    @Disabled("TODO: Complete this test")
    void testSavePartner3() {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.NullPointerException
        //       at com.digiexpress.partner.services.partnerService.savePartner(partnerService.java:25)
        //   See https://diff.blue/R013 to resolve this issue.

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
        when(partnerMapper.toDTO(Mockito.<Partner>any())).thenReturn(new PartnerDTO());
        partnerService.savePartner(null);
    }

    @Test
    void testSavePartner4() {
        when(partnerRepository.existsByDocument(Mockito.<String>any())).thenReturn(false);
        when(partnerMapper.toEntity(Mockito.<PartnerDTO>any()))
                .thenThrow(new EntityNotFoundException("An error occurred"));
        assertThrows(EntityNotFoundException.class, () -> partnerService.savePartner(new PartnerDTO()));
        verify(partnerRepository).existsByDocument(Mockito.<String>any());
        verify(partnerMapper).toEntity(Mockito.<PartnerDTO>any());
    }

    @Test
    void savePartner_whenPartnerDoesNotExist_shouldSavePartner() {
        // given
        PartnerDTO partnerDTO = new PartnerDTO();
        partnerDTO.setDocument("123456789");
        Partner partner = new Partner();
        partner.setDocument("123456789");
        when(partnerRepository.existsByDocument(any())).thenReturn(false);
        when(partnerMapper.toEntity(any())).thenReturn(partner);
        when(partnerMapper.toDTO(any())).thenReturn(partnerDTO);

        // when
//        PartnerDTO savedPartner = partnerService.savePartner(partnerDTO);

        // then
//        assertEquals(partnerDTO.getDocument(), savedPartner.getDocument());
//        assertEquals(partnerDTO.getTradingName(), savedPartner.getTradingName());
//        assertEquals(partnerDTO.getOwnerName(), savedPartner.getOwnerName());
//        assertEquals(partnerDTO.getCoverageArea(), savedPartner.getCoverageArea());
//        assertEquals(partnerDTO.getAddress(), savedPartner.getAddress());
    }

    @Test
    void getPartnerById_whenPartnerExists_shouldReturnPartnerDTO() {
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
    void getPartnerById_whenPartnerDoesNotExist_shouldThrowEntityNotFoundException() {
        // given
        Long partnerId = 1L;
        when(partnerRepository.findById(any())).thenReturn(Optional.empty());

        // when, then
        assertThrows(EntityNotFoundException.class, () -> partnerService.getPartnerById(partnerId));
    }

    @Test
    void searchPartnerByLocation_whenPartnerExists_shouldReturnPartnerDTO() {
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
    void searchPartnerByLocation_whenPartnerDoesNotExist_shouldThrowEntityNotFoundException() {
        // given
        Double lng = 1.0;
        Double lat = 2.0;
        when(partnerRepository.findByLocationNearAndCoverageAreaIntersecting(any(), anyDouble(), anyDouble())).thenReturn(Optional.empty());

        // when, then
        assertThrows(EntityNotFoundException.class, () -> partnerService.searchPartnerByLocation(lng, lat));
    }
}