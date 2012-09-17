package com.theoryinpractise.halbuilder.java;

import com.theoryinpractise.halbuilder.DefaultRepresentationFactory;
import com.theoryinpractise.halbuilder.api.ReadableRepresentation;
import com.theoryinpractise.halbuilder.api.RepresentationFactory;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.InputStreamReader;

import static org.fest.assertions.api.Assertions.assertThat;

public class RepresentationsTest {

    private RepresentationFactory representationFactory;
    private ReadableRepresentation representation;

    @BeforeClass
    public void init() {
        representationFactory = new DefaultRepresentationFactory();
        representation = representationFactory.readRepresentation(new InputStreamReader(InterfaceSatisfactionTest.class.getResourceAsStream("/example.xml")));
    }

    @Test
    public void testTryValue() {
        assertThat(Representations.tryValue(representation, "foo").isPresent()).isFalse();
        assertThat(Representations.tryValue(representation, "name").isPresent()).isTrue();
    }

    @Test
    public void testResourceLink() {
        assertThat(Representations.tryResourceLink(representation).isPresent()).isTrue();
        assertThat(Representations.tryResourceLink(representationFactory.newRepresentation()).isPresent()).isFalse();
    }

}
