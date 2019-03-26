module com.theoryinpractise.halbuilder.guava {
    exports com.theoryinpractise.halbuilder.guava;
    requires jsr305; // FIXME filename-based module
    requires transitive com.theoryinpractise.halbuilder.api;
    requires transitive com.theoryinpractise.halbuilder.core;
}
