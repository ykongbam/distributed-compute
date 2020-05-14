package com.ykongbam.task.mapReduce.map;

import com.google.common.collect.Iterables;
import com.ykongbam.node.NodeManager;
import com.ykongbam.task.PartialTask;
import com.ykongbam.task.PartialTaskResult;
import com.ykongbam.task.Tuple;
import lombok.AllArgsConstructor;
import org.apache.commons.lang3.tuple.Pair;

import java.util.*;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

@AllArgsConstructor
public class MapStage {
    private NodeManager nodeManager;
    private MapperExecutor mapperExecutor;

    public List<Tuple<Pair<String, String>>> map(List<Tuple<Pair<String, String>>> inputTuples) {
        List<Tuple<Pair<String, String>>> response = new ArrayList<>();
        List<PartialTask> partialTasks = splitInput(inputTuples);
        Collection<Future<PartialTaskResult>> mapperResponseFutures = nodeManager.submit(partialTasks, mapperExecutor);
        for (Future<PartialTaskResult> partialTaskResultFuture : mapperResponseFutures) {
            try {
                MapperResponse partialTaskResult = (MapperResponse) partialTaskResultFuture.get();
                for (Tuple<Pair<String, String>> tuple : partialTaskResult.getTuples()) {
                    response.add(tuple);
                }
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        }
        return response;
    }

    private List<PartialTask> splitInput(List<Tuple<Pair<String, String>>> inputTuples) {
        int activeNodeCount = nodeManager.getActiveNodeCount();
        int partitionSize = inputTuples.size() / activeNodeCount;
        List<PartialTask> partialTasks = new ArrayList<>();
        for (List<Tuple<Pair<String, String>>> partialTuples : Iterables.partition(inputTuples, partitionSize)) {
            partialTasks.add(new MapPartialTask(partialTuples));
        }
        return partialTasks;
    }

}
