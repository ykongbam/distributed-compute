package com.ykongbam.task;

import nl.jqno.equalsverifier.EqualsVerifier;
import nl.jqno.equalsverifier.Warning;
import org.junit.Test;

public class TupleTest {

    @Test
    public void testEquals() {
        EqualsVerifier.configure().suppress(Warning.STRICT_INHERITANCE).forClass(Tuple.class).verify();
    }
}