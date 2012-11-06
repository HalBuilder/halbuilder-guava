package com.theoryinpractise.halbuilder.guava;

import com.google.common.base.Function;
import com.google.common.base.Optional;
import com.google.common.base.Predicate;
import com.google.common.base.Predicates;
import com.theoryinpractise.halbuilder.api.Link;
import com.theoryinpractise.halbuilder.api.ReadableRepresentation;
import com.theoryinpractise.halbuilder.api.Representation;
import com.theoryinpractise.halbuilder.api.RepresentationException;
import com.theoryinpractise.halbuilder.impl.bytecode.InterfaceContract;
import com.theoryinpractise.halbuilder.impl.bytecode.InterfaceRenderer;

import java.net.URI;
import java.util.List;

public class Representations {
    /**
     * Add a link to this resource
     *
     * @param rel
     * @param href The target href for the link, relative to the href of this resource.
     */
    public static void withLink(Representation representation, String rel, String href, Predicate<ReadableRepresentation> predicate) {
        withLink(representation, rel, href,
                 Optional.of(predicate),
                 Optional.<String>absent(),
                 Optional.<String>absent(),
                 Optional.<String>absent(),
                 Optional.<String>absent());
    }

    /**
     * Add a link to this resource
     *
     * @param rel
     * @param uri The target URI for the link, possibly relative to the href of this resource.
     */
    public static void withLink(Representation representation, String rel, URI uri, Predicate<ReadableRepresentation> predicate) {
        withLink(representation, rel, uri.toASCIIString(), predicate);
    }

    /**
     * Add a link to this resource
     *
     * @param rel
     * @param href The target href for the link, relative to the href of this resource.
     */
    public static void withLink(Representation representation, String rel, String href, Optional<Predicate<ReadableRepresentation>> predicate, Optional<String> name, Optional<String> title, Optional<String> hreflang, Optional<String> profile) {
        if (predicate.or(Predicates.<ReadableRepresentation>alwaysTrue()).apply(representation)) {
            representation.withLink(rel, href, name.orNull(), title.orNull(), hreflang.orNull(), profile.orNull());
        }
    }

    /**
     * Add a link to this resource
     *
     * @param rel
     * @param uri The target URI for the link, possibly relative to the href of this resource.
     */
    public static void withLink(Representation representation, String rel, URI uri, Optional<Predicate<ReadableRepresentation>> predicate, Optional<String> name, Optional<String> title, Optional<String> hreflang, Optional<String> profile) {
        withLink(representation, rel, uri.toASCIIString(), predicate, name, title, hreflang, profile);
    }

    public static Optional<Link> tryResourceLink(ReadableRepresentation representation) {
        return Optional.fromNullable(representation.getResourceLink());
    }

    public static Optional<Link> tryLinkByRel(ReadableRepresentation representation, String rel) {
        return Optional.fromNullable(representation.getLinkByRel(rel));
    }

    /**
     * Returns all embedded resources from the Representation that match the predicate
     *
     * @param predicate The predicate to check against in the embedded resources
     * @return A List of matching objects (properties, links, resource)
     */
    List<? extends ReadableRepresentation> getResources(ReadableRepresentation representation,
                                                        Predicate<ReadableRepresentation> predicate) {
        throw new UnsupportedOperationException("not implemented yet");
    }

    /**
     * Returns a property from the Representation.
     *
     * @param name The property to return
     * @return A Guava Optional for the property
     */
    public static Optional<Object> tryValue(ReadableRepresentation representation, String name) {
        try {
            return Optional.fromNullable(representation.getValue(name));
        } catch (RepresentationException e) {
            return Optional.absent();
        }
    }

    /**
     * Returns an optional result of applying the provided function if the resource
     * is satisified by the provided interface.
     * <p/>
     * Interface satisfaction is determined by all interface methods being resolvable to
     * properties on the resource.
     *
     * @param anInterface The interface to check
     * @param function    The function to apply
     * @param <T>         The resource "interface"
     * @param <V>         A return value
     * @return A Guava Optional of the functions return value.
     */
    public static <T, V> Optional<V> ifSatisfiedBy(ReadableRepresentation representation, Class<T> anInterface, Function<T, V> function) {
        if (InterfaceContract.newInterfaceContract(anInterface).isSatisfiedBy(representation)) {
            T proxy = InterfaceRenderer.newInterfaceRenderer(anInterface).render(representation);
            return Optional.of(function.apply(proxy));
        }
        return Optional.absent();
    }


}
