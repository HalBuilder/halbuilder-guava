package com.theoryinpractise;

import com.theoryinpractise.halbuilder.java.RepresentationFactory;
import org.testng.annotations.Test;

public class JavaHalRendererTest {

    @Test
    public void testJavaHal() {

        RepresentationFactory representationFactory = new RepresentationFactory("http://localhost/api");
        String xml = representationFactory
                .newRepresentation("/testing")
                .withProperty("name", "Mark")
                .withProperty("age", "REDACTED")
                .withRepresentation("test", representationFactory.newRepresentation("/test2").withProperty("why", "now"))
                .renderContent("application/hal+xml");

        System.out.println("");
        System.out.println(xml);

    }


}
