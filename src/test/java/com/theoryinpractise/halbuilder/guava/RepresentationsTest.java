package com.theoryinpractise.halbuilder.guava;

import static org.fest.assertions.api.Assertions.assertThat;

import com.theoryinpractise.halbuilder.api.ReadableRepresentation;
import com.theoryinpractise.halbuilder.api.RepresentationFactory;
import com.theoryinpractise.halbuilder.xml.XmlRepresentationFactory;
import java.io.InputStreamReader;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class RepresentationsTest {

  private RepresentationFactory representationFactory;
  private ReadableRepresentation representation;

  @BeforeClass
  public void init() {
    representationFactory = new XmlRepresentationFactory();
    representation =
        representationFactory.readRepresentation(
            RepresentationFactory.HAL_XML, new InputStreamReader(InterfaceSatisfactionTest.class.getResourceAsStream("/example.xml")));
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
