package com.ykongbam.task.mapReduce.reduce;

import com.ykongbam.task.PartialTaskResult;
import com.ykongbam.task.Tuple;
import lombok.AllArgsConstructor;
import org.apache.commons.lang3.tuple.Pair;

import java.util.Collection;

@AllArgsConstructor
public class ReduceResponse implements PartialTaskResult<Pair<String, String>> {
    Collection<Tuple<Pair<String, String>>> tuples;
    @Override
    public Collection<Tuple<Pair<String, String>>> getTuples() {
        return tuples;
    }
}
