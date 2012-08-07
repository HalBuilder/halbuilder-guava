package com.theoryinpractise.halbuilder.java;

import com.theoryinpractise.halbuilder.java.api.Representation;
import com.theoryinpractise.halbuilder.java.impl.MutableRepresentation;

import java.net.URI;

public class RepresentationFactory {

    private com.theoryinpractise.halbuilder.RepresentationFactory _;

    public RepresentationFactory() {
        this("http://localhost");
    }

    public RepresentationFactory(URI baseUri) {
        this(baseUri.toASCIIString());
    }

    public RepresentationFactory(String baseHref) {
        _ = new com.theoryinpractise.halbuilder.RepresentationFactory(baseHref);
    }

    public String getBaseHref() {
        return _.getBaseHref();
    }

    public RepresentationFactory withNamespace(String namespace, String url) {
        _.withNamespace(namespace, url);
        return this;
    }

    public RepresentationFactory withLink(String url, String rel) {
        _.withLink(url, rel);
        return this;
    }

    public Representation newRepresentation(String href) {
        return new MutableRepresentation(_.newRepresentation(href));
    }

}
