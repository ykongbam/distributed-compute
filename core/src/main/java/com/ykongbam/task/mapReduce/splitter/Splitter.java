package com.ykongbam.task.mapReduce.splitter;

import com.ykongbam.task.PartialTask;
import com.ykongbam.task.Tuple;
import org.apache.commons.lang3.tuple.Pair;

import java.util.List;

public interface Splitter<K, V> {
    List<PartialTask> splitInput(List<Tuple<Pair<K, V>>> inputTuples);
}
