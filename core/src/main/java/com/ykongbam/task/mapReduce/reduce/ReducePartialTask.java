package com.ykongbam.task.mapReduce.reduce;

import com.ykongbam.task.PartialTask;
import com.ykongbam.task.Tuple;
import lombok.AllArgsConstructor;
import lombok.ToString;
import org.apache.commons.lang3.tuple.Pair;

import java.util.Collection;

@AllArgsConstructor
@ToString
public class ReducePartialTask implements PartialTask<Pair<String, Collection<String>>> {
    Collection<Tuple<Pair<String, Collection<String>>>> tuples;

    @Override
    public Collection<Tuple<Pair<String, Collection<String>>>> getTuples() {
        return tuples;
    }
}
