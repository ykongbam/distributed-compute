package com.ykongbam.task.mapReduce;

import com.ykongbam.task.PartialTask;
import com.ykongbam.task.Tuple;
import lombok.AllArgsConstructor;
import lombok.ToString;
import org.apache.commons.lang3.tuple.Pair;

import java.util.Collection;

@AllArgsConstructor
@ToString
public class MapReducePartialTask<K, V> implements PartialTask<Pair<K, V>> {
    Collection<Tuple<Pair<K, V>>> tuples;

    @Override
    public Collection<Tuple<Pair<K ,V>>> getTuples() {
        return tuples;
    }
}
