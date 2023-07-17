package com.digiexpress.partner.services.dto;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class SearchPartnerRequestTest {

    @Test
    void testCanEqual() {
        assertFalse((new SearchPartnerRequest()).canEqual("Other"));
    }

    @Test
    void testCanEqual2() {
        SearchPartnerRequest searchPartnerRequest = new SearchPartnerRequest();
        searchPartnerRequest.setLat(10.0d);
        searchPartnerRequest.setLng(10.0d);

        SearchPartnerRequest searchPartnerRequest2 = new SearchPartnerRequest();
        searchPartnerRequest2.setLat(10.0d);
        searchPartnerRequest2.setLng(10.0d);
        assertTrue(searchPartnerRequest.canEqual(searchPartnerRequest2));
    }

    @Test
    void testConstructor() {
        SearchPartnerRequest actualSearchPartnerRequest = new SearchPartnerRequest();
        actualSearchPartnerRequest.setLat(10.0d);
        actualSearchPartnerRequest.setLng(10.0d);
        String actualToStringResult = actualSearchPartnerRequest.toString();
        assertEquals(10.0d, actualSearchPartnerRequest.getLat().doubleValue());
        assertEquals(10.0d, actualSearchPartnerRequest.getLng().doubleValue());
        assertEquals("SearchPartnerRequest(lng=10.0, lat=10.0)", actualToStringResult);
    }

    @Test
    void testEquals() {
        SearchPartnerRequest searchPartnerRequest = new SearchPartnerRequest();
        searchPartnerRequest.setLat(10.0d);
        searchPartnerRequest.setLng(10.0d);
        assertNotEquals(searchPartnerRequest, null);
    }

    @Test
    void testEquals2() {
        SearchPartnerRequest searchPartnerRequest = new SearchPartnerRequest();
        searchPartnerRequest.setLat(10.0d);
        searchPartnerRequest.setLng(10.0d);
        assertNotEquals(searchPartnerRequest, "Different type to SearchPartnerRequest");
    }

    @Test
    void testEquals3() {
        SearchPartnerRequest searchPartnerRequest = new SearchPartnerRequest();
        searchPartnerRequest.setLat(10.0d);
        searchPartnerRequest.setLng(10.0d);
        assertEquals(searchPartnerRequest, searchPartnerRequest);
        int expectedHashCodeResult = searchPartnerRequest.hashCode();
        assertEquals(expectedHashCodeResult, searchPartnerRequest.hashCode());
    }

    @Test
    void testEquals4() {
        SearchPartnerRequest searchPartnerRequest = new SearchPartnerRequest();
        searchPartnerRequest.setLat(10.0d);
        searchPartnerRequest.setLng(10.0d);

        SearchPartnerRequest searchPartnerRequest2 = new SearchPartnerRequest();
        searchPartnerRequest2.setLat(10.0d);
        searchPartnerRequest2.setLng(10.0d);
        assertEquals(searchPartnerRequest, searchPartnerRequest2);
        int expectedHashCodeResult = searchPartnerRequest.hashCode();
        assertEquals(expectedHashCodeResult, searchPartnerRequest2.hashCode());
    }

    @Test
    void testEquals5() {
        SearchPartnerRequest searchPartnerRequest = new SearchPartnerRequest();
        searchPartnerRequest.setLat(null);
        searchPartnerRequest.setLng(10.0d);

        SearchPartnerRequest searchPartnerRequest2 = new SearchPartnerRequest();
        searchPartnerRequest2.setLat(10.0d);
        searchPartnerRequest2.setLng(10.0d);
        assertNotEquals(searchPartnerRequest, searchPartnerRequest2);
    }

    @Test
    void testEquals6() {
        SearchPartnerRequest searchPartnerRequest = new SearchPartnerRequest();
        searchPartnerRequest.setLat(0.5d);
        searchPartnerRequest.setLng(10.0d);

        SearchPartnerRequest searchPartnerRequest2 = new SearchPartnerRequest();
        searchPartnerRequest2.setLat(10.0d);
        searchPartnerRequest2.setLng(10.0d);
        assertNotEquals(searchPartnerRequest, searchPartnerRequest2);
    }

    @Test
    void testEquals7() {
        SearchPartnerRequest searchPartnerRequest = new SearchPartnerRequest();
        searchPartnerRequest.setLat(10.0d);
        searchPartnerRequest.setLng(null);

        SearchPartnerRequest searchPartnerRequest2 = new SearchPartnerRequest();
        searchPartnerRequest2.setLat(10.0d);
        searchPartnerRequest2.setLng(10.0d);
        assertNotEquals(searchPartnerRequest, searchPartnerRequest2);
    }

    @Test
    void testEquals8() {
        SearchPartnerRequest searchPartnerRequest = new SearchPartnerRequest();
        searchPartnerRequest.setLat(10.0d);
        searchPartnerRequest.setLng(0.5d);

        SearchPartnerRequest searchPartnerRequest2 = new SearchPartnerRequest();
        searchPartnerRequest2.setLat(10.0d);
        searchPartnerRequest2.setLng(10.0d);
        assertNotEquals(searchPartnerRequest, searchPartnerRequest2);
    }

    @Test
    void testEquals9() {
        SearchPartnerRequest searchPartnerRequest = new SearchPartnerRequest();
        searchPartnerRequest.setLat(null);
        searchPartnerRequest.setLng(10.0d);

        SearchPartnerRequest searchPartnerRequest2 = new SearchPartnerRequest();
        searchPartnerRequest2.setLat(null);
        searchPartnerRequest2.setLng(10.0d);
        assertEquals(searchPartnerRequest, searchPartnerRequest2);
        int expectedHashCodeResult = searchPartnerRequest.hashCode();
        assertEquals(expectedHashCodeResult, searchPartnerRequest2.hashCode());
    }


    @Test
    void testEquals10() {
        SearchPartnerRequest searchPartnerRequest = new SearchPartnerRequest();
        searchPartnerRequest.setLat(10.0d);
        searchPartnerRequest.setLng(null);

        SearchPartnerRequest searchPartnerRequest2 = new SearchPartnerRequest();
        searchPartnerRequest2.setLat(10.0d);
        searchPartnerRequest2.setLng(null);
        assertEquals(searchPartnerRequest, searchPartnerRequest2);
        int expectedHashCodeResult = searchPartnerRequest.hashCode();
        assertEquals(expectedHashCodeResult, searchPartnerRequest2.hashCode());
    }
}

