package com.digiexpress.partner.domains;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import org.springframework.data.mongodb.core.geo.GeoJsonMultiPolygon;
import org.springframework.data.mongodb.core.geo.GeoJsonPoint;

class PartnerTest {
    @Test
    void testCanEqual() {
        assertFalse((new Partner()).canEqual("Other"));
    }

    @Test
    void testCanEqual2() {
        Partner partner = new Partner();
        partner.setAddress(new GeoJsonPoint(2.0d, 3.0d));
        partner.setCoverageArea(new GeoJsonMultiPolygon(new ArrayList<>()));
        partner.setDocument("Document");
        partner.setId(1L);
        partner.setOwnerName("Owner Name");
        partner.setTradingName("Trading Name");

        Partner partner2 = new Partner();
        partner2.setAddress(new GeoJsonPoint(2.0d, 3.0d));
        partner2.setCoverageArea(new GeoJsonMultiPolygon(new ArrayList<>()));
        partner2.setDocument("Document");
        partner2.setId(1L);
        partner2.setOwnerName("Owner Name");
        partner2.setTradingName("Trading Name");
        assertTrue(partner.canEqual(partner2));
    }

    @Test
    void testConstructor() {
        Partner actualPartner = new Partner();
        GeoJsonPoint address = new GeoJsonPoint(2.0d, 3.0d);

        actualPartner.setAddress(address);
        GeoJsonMultiPolygon coverageArea = new GeoJsonMultiPolygon(new ArrayList<>());
        actualPartner.setCoverageArea(coverageArea);
        actualPartner.setDocument("Document");
        actualPartner.setId(1L);
        actualPartner.setOwnerName("Owner Name");
        actualPartner.setTradingName("Trading Name");
        String actualToStringResult = actualPartner.toString();
        assertSame(address, actualPartner.getAddress());
        assertSame(coverageArea, actualPartner.getCoverageArea());
        assertEquals("Document", actualPartner.getDocument());
        assertEquals(1L, actualPartner.getId().longValue());
        assertEquals("Owner Name", actualPartner.getOwnerName());
        assertEquals("Trading Name", actualPartner.getTradingName());
        assertEquals("Partner(id=1, tradingName=Trading Name, ownerName=Owner Name, document=Document, coverageArea=org"
                        + ".springframework.data.mongodb.core.geo.GeoJsonMultiPolygon@1, address=Point [x=2.000000, y=3.000000]" + ")",
                actualToStringResult);
    }


    @Test
    void testEquals() {
        Partner partner = new Partner();
        partner.setAddress(new GeoJsonPoint(2.0d, 3.0d));
        partner.setCoverageArea(new GeoJsonMultiPolygon(new ArrayList<>()));
        partner.setDocument("Document");
        partner.setId(1L);
        partner.setOwnerName("Owner Name");
        partner.setTradingName("Trading Name");
        assertNotEquals(partner, "Different type to Partner");
    }

    @Test
    void testEquals2() {
        Partner partner = new Partner();
        partner.setAddress(new GeoJsonPoint(2.0d, 3.0d));
        partner.setCoverageArea(new GeoJsonMultiPolygon(new ArrayList<>()));
        partner.setDocument("Document");
        partner.setId(1L);
        partner.setOwnerName("Owner Name");
        partner.setTradingName("Trading Name");

        Partner partner2 = new Partner();
        partner2.setAddress(new GeoJsonPoint(2.0d, 3.0d));
        partner2.setCoverageArea(new GeoJsonMultiPolygon(new ArrayList<>()));
        partner2.setDocument("Document");
        partner2.setId(1L);
        partner2.setOwnerName("Owner Name");
        partner2.setTradingName("Trading Name");
        assertEquals(partner, partner2);
        int expectedHashCodeResult = partner.hashCode();
        assertEquals(expectedHashCodeResult, partner2.hashCode());
    }

    @Test
    void testEquals3() {
        Partner partner = new Partner();
        partner.setAddress(null);
        partner.setCoverageArea(new GeoJsonMultiPolygon(new ArrayList<>()));
        partner.setDocument("Document");
        partner.setId(1L);
        partner.setOwnerName("Owner Name");
        partner.setTradingName("Trading Name");

        Partner partner2 = new Partner();
        partner2.setAddress(new GeoJsonPoint(2.0d, 3.0d));
        partner2.setCoverageArea(new GeoJsonMultiPolygon(new ArrayList<>()));
        partner2.setDocument("Document");
        partner2.setId(1L);
        partner2.setOwnerName("Owner Name");
        partner2.setTradingName("Trading Name");
        assertNotEquals(partner, partner2);
    }

    @Test
    void testEquals4() {
        Partner partner = new Partner();
        partner.setAddress(new GeoJsonPoint(2.0d, 3.0d));
        partner.setCoverageArea(new GeoJsonMultiPolygon(new ArrayList<>()));
        partner.setDocument(null);
        partner.setId(1L);
        partner.setOwnerName("Owner Name");
        partner.setTradingName("Trading Name");

        Partner partner2 = new Partner();
        partner2.setAddress(new GeoJsonPoint(2.0d, 3.0d));
        partner2.setCoverageArea(new GeoJsonMultiPolygon(new ArrayList<>()));
        partner2.setDocument("Document");
        partner2.setId(1L);
        partner2.setOwnerName("Owner Name");
        partner2.setTradingName("Trading Name");
        assertNotEquals(partner, partner2);
    }


    @Test
    void testEquals5() {
        Partner partner = new Partner();
        partner.setAddress(new GeoJsonPoint(2.0d, 3.0d));
        partner.setCoverageArea(new GeoJsonMultiPolygon(new ArrayList<>()));
        partner.setDocument("Document");
        partner.setId(null);
        partner.setOwnerName("Owner Name");
        partner.setTradingName("Trading Name");

        Partner partner2 = new Partner();
        partner2.setAddress(new GeoJsonPoint(2.0d, 3.0d));
        partner2.setCoverageArea(new GeoJsonMultiPolygon(new ArrayList<>()));
        partner2.setDocument("Document");
        partner2.setId(1L);
        partner2.setOwnerName("Owner Name");
        partner2.setTradingName("Trading Name");
        assertNotEquals(partner, partner2);
    }

    @Test
    void testEquals6() {
        Partner partner = new Partner();
        partner.setAddress(new GeoJsonPoint(2.0d, 3.0d));
        partner.setCoverageArea(new GeoJsonMultiPolygon(new ArrayList<>()));
        partner.setDocument("Document");
        partner.setId(1L);
        partner.setOwnerName("Trading Name");
        partner.setTradingName("Trading Name");

        Partner partner2 = new Partner();
        partner2.setAddress(new GeoJsonPoint(2.0d, 3.0d));
        partner2.setCoverageArea(new GeoJsonMultiPolygon(new ArrayList<>()));
        partner2.setDocument("Document");
        partner2.setId(1L);
        partner2.setOwnerName("Owner Name");
        partner2.setTradingName("Trading Name");
        assertNotEquals(partner, partner2);
    }


    @Test
    void testEquals7() {
        Partner partner = new Partner();
        partner.setAddress(null);
        partner.setCoverageArea(new GeoJsonMultiPolygon(new ArrayList<>()));
        partner.setDocument("Document");
        partner.setId(1L);
        partner.setOwnerName("Owner Name");
        partner.setTradingName("Trading Name");

        Partner partner2 = new Partner();
        partner2.setAddress(null);
        partner2.setCoverageArea(new GeoJsonMultiPolygon(new ArrayList<>()));
        partner2.setDocument("Document");
        partner2.setId(1L);
        partner2.setOwnerName("Owner Name");
        partner2.setTradingName("Trading Name");
        assertEquals(partner, partner2);
        int expectedHashCodeResult = partner.hashCode();
        assertEquals(expectedHashCodeResult, partner2.hashCode());
    }
}

