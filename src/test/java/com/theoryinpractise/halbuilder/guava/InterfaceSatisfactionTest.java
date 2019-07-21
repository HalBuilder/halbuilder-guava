package com.theoryinpractise.halbuilder.guava;

import com.theoryinpractise.halbuilder.api.ReadableRepresentation;
import com.theoryinpractise.halbuilder.api.RepresentationFactory;
import com.theoryinpractise.halbuilder.xml.XmlRepresentationFactory;
import org.testng.annotations.Test;

import java.io.InputStreamReader;

import static com.google.common.truth.Truth.assertThat;
import static com.theoryinpractise.halbuilder.guava.Representations.ifSatisfiedBy;

public class InterfaceSatisfactionTest {

  private RepresentationFactory representationFactory = new XmlRepresentationFactory();

  public interface IPerson {
    Integer getAge();

    Boolean getExpired();

    Integer getId();

    String getName();
  }

  public interface INamed {
    String name();
  }

  public interface IJob {
    Integer getJobId();
  }

  public interface ISimpleJob {
    Integer jobId();
  }

  public interface INullprop {
    String nullprop();
  }

  @Test
  public void testFunctionalInterfaceSatisfaction() {

    ReadableRepresentation representation =
        representationFactory.readRepresentation(
            RepresentationFactory.HAL_XML, new InputStreamReader(InterfaceSatisfactionTest.class.getResourceAsStream("/example.xml")));

    String name = ifSatisfiedBy(representation, IPerson.class, iPerson -> iPerson.getName()).get();

    assertThat(name).isEqualTo("Example Resource");
  }
}
