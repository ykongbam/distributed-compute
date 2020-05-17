package com.ykongbam.task.mapReduce.map;

import nl.jqno.equalsverifier.EqualsVerifier;
import nl.jqno.equalsverifier.Warning;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;
import org.junit.Test;

public class MapperResponseTest {

    @Test
    public void testEquals() {
        EqualsVerifier.configure()
                .withPrefabValues(
                        Pair.class,
                        ImmutablePair.of("lol", "yeah"),
                        ImmutablePair.of("lol", "nope")
                        )
                .suppress(Warning.STRICT_INHERITANCE).forClass(MapperResponse.class).verify();
    }
}