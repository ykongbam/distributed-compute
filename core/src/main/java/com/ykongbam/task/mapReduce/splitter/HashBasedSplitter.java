package com.ykongbam.task.mapReduce.splitter;

import com.google.common.collect.LinkedListMultimap;
import com.google.common.collect.Multimap;
import com.ykongbam.task.PartialTask;
import com.ykongbam.task.Tuple;
import com.ykongbam.task.mapReduce.MapReducePartialTask;
import lombok.AllArgsConstructor;
import org.apache.commons.lang3.tuple.Pair;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
public class HashBasedSplitter<K, V> implements Splitter<K, V> {
    private int partitionCount;

    @Override
    public List<PartialTask> splitInput(List<Tuple<Pair<K, V>>> inputTuples) {
        Multimap<Integer, Tuple<Pair<K, V>>> buckets = LinkedListMultimap.create();
        for (Tuple<Pair<K, V>> tuple : inputTuples) {
            buckets.put(tuple.get().getKey().hashCode() % partitionCount, tuple);
        }
        return buckets.asMap().values().stream()
                .map(MapReducePartialTask::new)
                .collect(Collectors.toList());
    }
}
