package com.theoryinpractise.halbuilder.java.impl;

import com.google.common.base.Function;
import com.google.common.base.Optional;
import com.google.common.base.Predicate;
import com.google.common.collect.FluentIterable;
import com.google.common.collect.Multimap;
import com.theoryinpractise.halbuilder.java.api.ReadableRepresentation;
import com.theoryinpractise.halbuilder.spi.Contract;
import com.theoryinpractise.halbuilder.spi.Link;

import javax.annotation.Nullable;
import java.util.List;
import java.util.Map;

public class BaseRepresentation implements ReadableRepresentation {

    private com.theoryinpractise.halbuilder.spi.ReadableRepresentation _;

    private static final Function<com.theoryinpractise.halbuilder.spi.ReadableRepresentation,ReadableRepresentation> convertRepresentations = new Function<com.theoryinpractise.halbuilder.spi.ReadableRepresentation, ReadableRepresentation>() {
        @Override
        public ReadableRepresentation apply(com.theoryinpractise.halbuilder.spi.ReadableRepresentation _) {
            return new BaseRepresentation(_);
        }
    };

    public BaseRepresentation(com.theoryinpractise.halbuilder.spi.ReadableRepresentation _) {
        this._ = _;
    }

    public com.theoryinpractise.halbuilder.spi.ReadableRepresentation get_() {
        return _;
    }

    protected Predicate<com.theoryinpractise.halbuilder.spi.ReadableRepresentation> makeWrappingPredicate(final Predicate<ReadableRepresentation> predicate) {
        return new Predicate<com.theoryinpractise.halbuilder.spi.ReadableRepresentation>() {
            @Override
            public boolean apply(@Nullable com.theoryinpractise.halbuilder.spi.ReadableRepresentation readableRepresentation) {
                return predicate.apply(new BaseRepresentation(readableRepresentation));
            }
        };
    }

    protected Optional<Predicate<com.theoryinpractise.halbuilder.spi.ReadableRepresentation>> makeWrappingPredicate(final Optional<Predicate<ReadableRepresentation>> predicate) {
        if (predicate.isPresent()) {
            return Optional.of(makeWrappingPredicate(predicate.get()));
        } else {
            return Optional.absent();
        }
    }

    @Override
    public Optional<Link> getResourceLink() {
        return _.getResourceLink();
    }

    @Override
    public Map<String, String> getNamespaces() {
        return _.getNamespaces();
    }

    @Override
    public List<Link> getCanonicalLinks() {
        return _.getCanonicalLinks();
    }

    @Override
    public List<Link> getLinks() {
        return _.getLinks();
    }

    @Override
    public Optional<Link> getLinkByRel(String rel) {
        return _.getLinkByRel(rel);
    }

    @Override
    public List<Link> getLinksByRel(String rel) {
        return _.getLinksByRel(rel);
    }

    @Override
    public List<? extends ReadableRepresentation> getResourcesByRel(String rel) {
        return FluentIterable.from(_.getResourcesByRel(rel)).transform(convertRepresentations).toImmutableList();
    };

    @Override
    public List<? extends ReadableRepresentation> getResources(Predicate<ReadableRepresentation> predicate) {
        return FluentIterable.from(_.getResources(makeWrappingPredicate(predicate))).transform(convertRepresentations).toImmutableList();
    }

    @Override
    public Optional<Object> tryGet(String name) {
        return _.get(name);
    }

    @Override
    public Object get(String name) {
        return _.get(name).get();
    }

    @Override
    public Object getValue(String name) {
        return _.getValue(name);
    }

    @Override
    public Object getValue(String name, Object defaultValue) {
        return _.getValue(name, defaultValue);
    }

    @Override
    public Map<String, Optional<Object>> getProperties() {
        return _.getProperties();
    }

    @Override
    public boolean hasNullProperties() {
        return _.hasNullProperties();
    }

    @Override
    public Multimap<String, ReadableRepresentation> getResources() {
//        return _.getResources();
        return null;
    }

    @Override
    public boolean isSatisfiedBy(Contract contract) {
        return _.isSatisfiedBy(contract);
    }

    @Override
    public <T, V> Optional<V> ifSatisfiedBy(Class<T> anInterface, Function<T, V> function) {
        return _.ifSatisfiedBy(anInterface, function);
    }

    @Override
    public <T> Optional<T> renderClass(Class<T> anInterface) {
        return _.renderClass(anInterface);
    }

    @Override
    public String renderContent(String contentType) {
        return _.renderContent(contentType);
    }

    @Override
    public <T> Optional<T> resolveClass(final Function<ReadableRepresentation, Optional<T>> resolver) {
        return _.resolveClass(new Function<com.theoryinpractise.halbuilder.spi.ReadableRepresentation, Optional<T>>() {
            @Override
            public Optional<T> apply(@Nullable com.theoryinpractise.halbuilder.spi.ReadableRepresentation readableRepresentation) {
                return resolver.apply(new BaseRepresentation(readableRepresentation));
            }
        });
    }
}
