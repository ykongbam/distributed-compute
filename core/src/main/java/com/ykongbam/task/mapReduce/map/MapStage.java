package com.ykongbam.task.mapReduce.map;

import com.ykongbam.node.NodeManager;
import com.ykongbam.task.PartialTask;
import com.ykongbam.task.PartialTaskResult;
import com.ykongbam.task.Tuple;
import com.ykongbam.task.mapReduce.splitter.Splitter;
import lombok.AllArgsConstructor;
import org.apache.commons.lang3.tuple.Pair;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

@AllArgsConstructor
public class MapStage {
    private NodeManager nodeManager;
    private MapperExecutor mapperExecutor;
    private Splitter<String, String> splitter;

    public List<Tuple<Pair<String, String>>> map(List<Tuple<Pair<String, String>>> inputTuples) {
        List<Tuple<Pair<String, String>>> response = new ArrayList<>();
        List<PartialTask> partialTasks = splitter.splitInput(inputTuples);
        Collection<Future<PartialTaskResult>> mapperResponseFutures = nodeManager.submit(partialTasks, mapperExecutor);
        for (Future<PartialTaskResult> partialTaskResultFuture : mapperResponseFutures) {
            try {
                MapperResponse partialTaskResult = (MapperResponse) partialTaskResultFuture.get();
                response.addAll(partialTaskResult.getTuples());
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        }
        return response;
    }

}
