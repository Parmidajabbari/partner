package com.digiexpress.partner.services.dto;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;

import org.junit.jupiter.api.Disabled;

import org.junit.jupiter.api.Test;
import org.springframework.data.mongodb.core.geo.GeoJsonMultiPolygon;
import org.springframework.data.mongodb.core.geo.GeoJsonPoint;

class PartnerDTOTest {

    @Test
    void testCanEqual() {
        assertFalse((new PartnerDTO()).canEqual("Other"));
    }

    @Test
    void testCanEqual2() {
        PartnerDTO partnerDTO = new PartnerDTO();
        assertTrue(partnerDTO.canEqual(new PartnerDTO()));
    }

    @Test
    void testConstructor() {
        GeoJsonMultiPolygon coverageArea = new GeoJsonMultiPolygon(new ArrayList<>());
        GeoJsonPoint address = new GeoJsonPoint(2.0d, 3.0d);

        PartnerDTO actualPartnerDTO = new PartnerDTO("Trading Name", "Owner Name", "Document", coverageArea, address);

        assertSame(address, actualPartnerDTO.getAddress());
        assertEquals("Trading Name", actualPartnerDTO.getTradingName());
        assertSame(coverageArea, actualPartnerDTO.getCoverageArea());
        assertEquals("Owner Name", actualPartnerDTO.getOwnerName());
        assertEquals("Document", actualPartnerDTO.getDocument());
    }

    @Test
    void testEquals() {
        assertNotEquals(new PartnerDTO(), null);
        assertNotEquals(new PartnerDTO(), "Different type to PartnerDTO");
    }

    @Test
    void testEquals1() {
        PartnerDTO partnerDTO = new PartnerDTO();
        PartnerDTO partnerDTO2 = new PartnerDTO();
        assertEquals(partnerDTO, partnerDTO2);
        int expectedHashCodeResult = partnerDTO.hashCode();
        assertEquals(expectedHashCodeResult, partnerDTO2.hashCode());
    }

    @Test
    void testEquals2() {
        GeoJsonMultiPolygon coverageArea = new GeoJsonMultiPolygon(new ArrayList<>());
        PartnerDTO partnerDTO = new PartnerDTO("Trading Name", "Owner Name", "Document", coverageArea,
                new GeoJsonPoint(2.0d, 3.0d));
        assertNotEquals(partnerDTO, new PartnerDTO());
    }

    @Test
    void testEquals3() {
        PartnerDTO partnerDTO = new PartnerDTO();
        partnerDTO.setId(1L);
        assertNotEquals(partnerDTO, new PartnerDTO());
    }

    @Test
    void testEquals4() {
        GeoJsonMultiPolygon coverageArea = new GeoJsonMultiPolygon(new ArrayList<>());
        PartnerDTO partnerDTO = new PartnerDTO("Trading Name", "Owner Name", "Document", coverageArea,
                new GeoJsonPoint(2.0d, 3.0d));
        GeoJsonMultiPolygon coverageArea2 = new GeoJsonMultiPolygon(new ArrayList<>());
        PartnerDTO partnerDTO2 = new PartnerDTO("Trading Name", "Owner Name", "Document", coverageArea2,
                new GeoJsonPoint(2.0d, 3.0d));

        assertEquals(partnerDTO, partnerDTO2);
        int expectedHashCodeResult = partnerDTO.hashCode();
        assertEquals(expectedHashCodeResult, partnerDTO2.hashCode());
    }

    @Test
    void testEquals5() {
        PartnerDTO partnerDTO = new PartnerDTO();

        PartnerDTO partnerDTO2 = new PartnerDTO();
        partnerDTO2.setOwnerName("Owner Name");
        partnerDTO2.setDocument("Document");
        partnerDTO2.setCoverageArea(new GeoJsonMultiPolygon(new ArrayList<>()));
        partnerDTO2.setAddress(new GeoJsonPoint(2.0d, 3.0d));

        assertNotEquals(partnerDTO, partnerDTO2);
    }

    @Test
    void testEquals6() {
        PartnerDTO partnerDTO = new PartnerDTO();
        partnerDTO.setId(1L);

        PartnerDTO partnerDTO2 = new PartnerDTO();
        partnerDTO2.setId(1L);
        assertEquals(partnerDTO, partnerDTO2);
        int expectedHashCodeResult = partnerDTO.hashCode();
        assertEquals(expectedHashCodeResult, partnerDTO2.hashCode());
    }

    @Test
    void testSetAddress() {
        PartnerDTO partnerDTO = new PartnerDTO();
        GeoJsonPoint address = new GeoJsonPoint(2.0d, 3.0d);

        partnerDTO.setAddress(address);
        assertSame(address, partnerDTO.getAddress());
    }

    @Test
    void testSetCoverageArea() {
        PartnerDTO partnerDTO = new PartnerDTO();
        GeoJsonMultiPolygon coverageArea = new GeoJsonMultiPolygon(new ArrayList<>());
        partnerDTO.setCoverageArea(coverageArea);
        assertSame(coverageArea, partnerDTO.getCoverageArea());
    }

    @Test
    void testSetDocument() {
        PartnerDTO partnerDTO = new PartnerDTO();
        partnerDTO.setDocument("Document");
        assertEquals("Document", partnerDTO.getDocument());
    }

    @Test
    void testSetOwnerName() {
        PartnerDTO partnerDTO = new PartnerDTO();
        partnerDTO.setOwnerName("Owner Name");
        assertEquals("Owner Name", partnerDTO.getOwnerName());
    }

    @Test
    void testSetTradingName() {
        PartnerDTO partnerDTO = new PartnerDTO();
        partnerDTO.setTradingName("Trading Name");
        assertEquals("Trading Name", partnerDTO.getTradingName());
    }

}

