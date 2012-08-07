package com.theoryinpractise.halbuilder.java.impl;

import com.google.common.base.Function;
import com.google.common.base.Optional;
import com.google.common.base.Predicate;
import com.theoryinpractise.halbuilder.java.api.ReadableRepresentation;
import com.theoryinpractise.halbuilder.java.api.Representation;
import com.theoryinpractise.halbuilder.spi.Serializable;

import java.net.URI;

public class MutableRepresentation extends BaseRepresentation implements Representation {

    private com.theoryinpractise.halbuilder.spi.Representation __;

    public MutableRepresentation(com.theoryinpractise.halbuilder.spi.Representation __) {
        super(__);
        this.__ = __;
    }

    public Representation withLink(String rel, String href) {
        __.withLink(rel, href);
        return this;
    }

    public Representation withLink(String rel, URI uri) {
        __.withLink(rel, uri);
        return this;
    }

    public Representation withLink(String rel, String href, final Predicate<ReadableRepresentation> predicate) {
        __.withLink(rel, href, makeWrappingPredicate(predicate));
        return this;
    }

    public Representation withLink(String rel, URI uri, Predicate<ReadableRepresentation> predicate) {
        __.withLink(rel, uri, makeWrappingPredicate(predicate));
        return this;
    }

    public Representation withLink(String rel, String href, Optional<Predicate<ReadableRepresentation>> predicate, Optional<String> name, Optional<String> title, Optional<String> hreflang) {
        __.withLink(rel, href, makeWrappingPredicate(predicate), name, title, hreflang);
        return this;
    }

    public Representation withLink(String rel, URI uri, Optional<Predicate<ReadableRepresentation>> predicate, Optional<String> name, Optional<String> title, Optional<String> hreflang) {
        __.withLink(rel, uri, makeWrappingPredicate(predicate), name, title, hreflang);
        return this;
    }


    public Representation withProperty(String name, Object value) {
        __.withProperty(name, value);
        return this;
    }

    public Representation withBean(Object o) {
        __.withBean(o);
        return this;
    }

    public Representation withFields(Object o) {
        __.withFields(o);
        return this;
    }

    public Representation withSerializable(Serializable serializable) {
        __.withSerializable(serializable);
        return this;
    }

    public Representation withFieldBasedRepresentation(String rel, String href, Object o) {
        __.withFieldBasedRepresentation(rel, href, o);
        return this;
    }

    public Representation withBeanBasedRepresentation(String rel, String href, Object o) {
        __.withBeanBasedRepresentation(rel, href, o);
        return this;
    }

    public Representation withNamespace(String namespace, String href) {
        __.withNamespace(namespace, href);
        return this;
    }

    public Representation withRepresentation(String rel, Representation representation) {
        com.theoryinpractise.halbuilder.spi.ReadableRepresentation readableRepresentation = ((BaseRepresentation) representation).get_();
        __.withRepresentation(rel, readableRepresentation);
        return this;
    }

    public Optional<Object> get(String name) {
        return __.get(name);
    }

    public <T> Optional<T> resolveClass(Function<ReadableRepresentation, Optional<T>> readableRepresentationOptionalFunction) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }
}
