package dev.masterflomaster1.jwa.model;

import static dev.masterflomaster1.jwa.model.Revisions.RvProp;

import java.util.Set;
import java.util.stream.Collectors;

public class UrlHelper {

    public static String concat(Set<RvProp> set) {
        return set.stream()
                .map(RvProp::value)
                .collect(Collectors.joining("%7C"));
    }

}
