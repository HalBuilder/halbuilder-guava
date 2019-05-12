package com.theoryinpractise.halbuilder.guava;

import static com.theoryinpractise.halbuilder.guava.Representations.ifSatisfiedBy;
import static org.fest.assertions.api.Assertions.assertThat;

import com.theoryinpractise.halbuilder.api.ReadableRepresentation;
import com.theoryinpractise.halbuilder.api.RepresentationFactory;
import com.theoryinpractise.halbuilder.xml.XmlRepresentationFactory;
import java.io.InputStreamReader;
import org.testng.annotations.Test;

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
            RepresentationFactory.HAL_XML,
            new InputStreamReader(
                InterfaceSatisfactionTest.class.getResourceAsStream("/example.xml")));

    String name = ifSatisfiedBy(representation, IPerson.class, iPerson -> iPerson.getName()).get();

    assertThat(name).isEqualTo("Example Resource");
  }
}
