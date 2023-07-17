package com.digiexpress.partner.web.controller;

import com.digiexpress.partner.domains.Partner;
import com.digiexpress.partner.mapper.PartnerMapperImpl;
import com.digiexpress.partner.repositories.PartnerRepository;
import com.digiexpress.partner.services.PartnerService;
import com.digiexpress.partner.services.PartnerServiceImpl;
import com.digiexpress.partner.services.dto.PartnerDTO;
import com.digiexpress.partner.web.controllers.PartnerController;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.mongodb.core.geo.GeoJsonMultiPolygon;
import org.springframework.data.mongodb.core.geo.GeoJsonPoint;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ContextConfiguration(classes = {PartnerController.class})
@ExtendWith(SpringExtension.class)
class PartnerControllerTest {
    @Autowired
    private PartnerController partnerController;

    @Mock
    private PartnerRepository partnerRepository;

    @MockBean
    private PartnerService partnerService;

    @Test
    void testSavePartner() {

        Partner partner = new Partner();
        partner.setAddress(new GeoJsonPoint(2.0d, 3.0d));
        partner.setCoverageArea(new GeoJsonMultiPolygon(new ArrayList<>()));
        partner.setDocument("Document222");
        when(partnerRepository.existsByDocument(any())).thenReturn(true);
        partner.setId(1L);
        partner.setOwnerName("Owner Name");
        partner.setTradingName("Trading Name");
        PartnerRepository partnerRepository = mock(PartnerRepository.class);
        when(partnerRepository.existsByDocument(Mockito.<String>any())).thenReturn(true);
        when(partnerRepository.save(Mockito.<Partner>any())).thenReturn(partner);
        PartnerController partnerController = new PartnerController(
                new PartnerServiceImpl(partnerRepository, new PartnerMapperImpl()));
        partnerController.savePartner(new PartnerDTO());
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
        PartnerRepository partnerRepository = mock(PartnerRepository.class);
        when(partnerRepository.existsByDocument(Mockito.<String>any())).thenReturn(false);
        when(partnerRepository.save(Mockito.<Partner>any())).thenReturn(partner);
        PartnerController partnerController = new PartnerController(
                new PartnerServiceImpl(partnerRepository, new PartnerMapperImpl()));
        partnerController.savePartner(new PartnerDTO());
        verify(partnerRepository).existsByDocument(Mockito.<String>any());
        verify(partnerRepository).save(Mockito.<Partner>any());
    }

    @Test
    void testGetPartnerById() throws Exception {
        when(partnerService.getPartnerById(Mockito.<Long>any())).thenReturn(new PartnerDTO());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/partners/{id}", 1L);
        MockMvcBuilders.standaloneSetup(partnerController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"id\":null,\"tradingName\":null,\"ownerName\":null,\"document\":null,\"coverageArea\":null,\"address\":null}"));
    }

    @Test
    void testSearchPartner() throws Exception {
        when(partnerService.searchPartnerByLocation(Mockito.<Double>any(), Mockito.<Double>any()))
                .thenReturn(new PartnerDTO());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/partners/search");
        MockMvcBuilders.standaloneSetup(partnerController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"id\":null,\"tradingName\":null,\"ownerName\":null,\"document\":null,\"coverageArea\":null,\"address\":null}"));
    }
}

