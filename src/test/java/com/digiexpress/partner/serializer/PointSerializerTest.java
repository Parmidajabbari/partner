package com.digiexpress.partner.serializer;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.filter.FilteringGeneratorDelegate;
import com.fasterxml.jackson.core.io.IOContext;
import com.fasterxml.jackson.core.json.UTF8JsonGenerator;
import com.fasterxml.jackson.core.util.BufferRecycler;
import com.fasterxml.jackson.core.util.JsonGeneratorDelegate;
import com.fasterxml.jackson.core.util.JsonParserDelegate;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.node.MissingNode;
import com.fasterxml.jackson.databind.node.TreeTraversingParser;
import com.fasterxml.jackson.databind.ser.DefaultSerializerProvider;
import com.fasterxml.jackson.databind.util.TokenBuffer;

import java.io.ByteArrayOutputStream;

import java.io.IOException;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.data.mongodb.core.geo.GeoJsonPoint;

class PointSerializerTest {
    @Test
    void testSerialize1() throws IOException {
        PointSerializer pointSerializer = new PointSerializer();
        GeoJsonPoint value = new GeoJsonPoint(2.0d, 3.0d);

        IOContext ctxt = new IOContext(new BufferRecycler(), "Source Ref", true);

        ObjectMapper codec = new ObjectMapper();
        JsonGeneratorDelegate gen = new JsonGeneratorDelegate(
                new UTF8JsonGenerator(ctxt, 1, codec, new ByteArrayOutputStream(1)), true);

        pointSerializer.serialize(value, gen, new DefaultSerializerProvider.Impl());
        JsonGenerator delegateResult = gen.delegate();
        assertNull(delegateResult.getCurrentValue());
        assertEquals(2, delegateResult.getOutputBuffered());
    }

    @Test
    void testSerialize2() throws IOException {
        PointSerializer pointSerializer = new PointSerializer();
        GeoJsonPoint value = new GeoJsonPoint(2.0d, 3.0d);

        JsonGeneratorDelegate gen = new JsonGeneratorDelegate(
                new TokenBuffer(new JsonParserDelegate(new TreeTraversingParser(MissingNode.getInstance()))), true);

        pointSerializer.serialize(value, gen, new DefaultSerializerProvider.Impl());
        assertEquals(0, gen.getOutputContext().getCurrentIndex());
        assertNull(gen.delegate().getCurrentValue());
    }
}

