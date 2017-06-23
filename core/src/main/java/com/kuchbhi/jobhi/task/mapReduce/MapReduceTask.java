package com.kuchbhi.jobhi.task.mapReduce;

import com.kuchbhi.jobhi.node.NodeManager;
import com.kuchbhi.jobhi.task.Task;
import com.kuchbhi.jobhi.task.Tuple;
import lombok.AllArgsConstructor;
import org.apache.commons.lang3.tuple.Pair;

import java.util.Set;
import java.util.concurrent.Future;

/**
 * Created with IntelliJ IDEA.
 * User: yoihenba.k (yoihenba.k@flipkart.com)
 * Date: 23/06/17
 */

@AllArgsConstructor
public class MapReduceTask implements Task {
    NodeManager nodeManager;
    private Set<Tuple<String>> inputTuples;

    @Override
    public Future<MapReduceTaskResponse> process() {
        Set<Tuple<Pair<String, String>>> intermediateOutput = map();
        Set<Tuple<Pair<String, Set<String>>>> shuffleOutput = shuffle(intermediateOutput);
        Set<Tuple<Pair<String, String>>> response = reduce(shuffleOutput);
        return null;
    }

    private Set<Tuple<Pair<String, String>>> map() {
        return null;
    }

    private Set<Tuple<Pair<String, Set<String>>>> shuffle(Set<Tuple<Pair<String, String>>> intermediateOutput) {
        return null;
    }

    private Set<Tuple<Pair<String,String>>> reduce(Set<Tuple<Pair<String, Set<String>>>> shuffleOutput) {
        return null;
    }
}
