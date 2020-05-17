package com.ykongbam.task.mapReduce.splitter;

import com.google.common.collect.Iterables;
import com.ykongbam.task.PartialTask;
import com.ykongbam.task.Tuple;
import com.ykongbam.task.mapReduce.MapReducePartialTask;
import lombok.AllArgsConstructor;
import org.apache.commons.lang3.tuple.Pair;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
public class FixedPartitionSplitter<K, V> implements Splitter<K, V> {
    private int partitionCount;

    @Override
    public List<PartialTask> splitInput(List<Tuple<Pair<K, V>>> inputTuples) {
        int partitionSize = (int) Math.ceil(inputTuples.size() / (double) partitionCount);
        List<PartialTask> partialTasks = new ArrayList<>();
        for (List<Tuple<Pair<K, V>>> partialTuples : Iterables.partition(inputTuples, partitionSize)) {
            MapReducePartialTask<K, V> partialTask = new MapReducePartialTask<>(partialTuples);
            partialTasks.add(partialTask);
        }
        return partialTasks;
    }
}
