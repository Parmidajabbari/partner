package com.digiexpress.partner.serializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.filter.FilteringGeneratorDelegate;
import com.fasterxml.jackson.core.io.IOContext;
import com.fasterxml.jackson.core.json.UTF8JsonGenerator;
import com.fasterxml.jackson.core.util.BufferRecycler;
import com.fasterxml.jackson.core.util.JsonGeneratorDelegate;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ser.DefaultSerializerProvider;
import org.junit.jupiter.api.Test;
import org.springframework.data.mongodb.core.geo.GeoJsonMultiPolygon;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class GeoJsonMultiPolygonSerializerTest {

    @Test
    void testSerialize() throws IOException {
        GeoJsonMultiPolygonSerializer geoJsonMultiPolygonSerializer = new GeoJsonMultiPolygonSerializer();
        GeoJsonMultiPolygon value = new GeoJsonMultiPolygon(new ArrayList<>());
        JsonGeneratorDelegate gen = new JsonGeneratorDelegate(new FilteringGeneratorDelegate(
                new JsonGeneratorDelegate(new JsonGeneratorDelegate(null), true), null, true, true), true);

        geoJsonMultiPolygonSerializer.serialize(value, gen, new DefaultSerializerProvider.Impl());
        assertSame(gen.getOutputContext(), ((FilteringGeneratorDelegate) gen.delegate()).getFilterContext());
    }

    @Test
    void testSerialize1() throws IOException {
        GeoJsonMultiPolygonSerializer geoJsonMultiPolygonSerializer = new GeoJsonMultiPolygonSerializer();
        GeoJsonMultiPolygon value = new GeoJsonMultiPolygon(new ArrayList<>());
        IOContext ctxt = new IOContext(new BufferRecycler(), "Source Ref", true);

        ObjectMapper codec = new ObjectMapper();
        JsonGeneratorDelegate gen = new JsonGeneratorDelegate(
                new UTF8JsonGenerator(ctxt, 1, codec, new ByteArrayOutputStream(1)), true);

        geoJsonMultiPolygonSerializer.serialize(value, gen, new DefaultSerializerProvider.Impl());
        JsonGenerator delegateResult = gen.delegate();
        assertNull(delegateResult.getCurrentValue());
        assertEquals(36, delegateResult.getOutputBuffered());
    }
}

