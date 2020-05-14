package com.ykongbam.task.mapReduce.reduce;

import com.google.common.collect.ImmutableSet;
import com.ykongbam.node.NodeManager;
import com.ykongbam.task.PartialTask;
import com.ykongbam.task.PartialTaskResult;
import com.ykongbam.task.Tuple;
import com.ykongbam.task.mapReduce.executors.wordCount.ReduceExecutor;
import lombok.AllArgsConstructor;
import org.apache.commons.lang3.tuple.Pair;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

@AllArgsConstructor
public class ReduceStage {
    private NodeManager nodeManager;
    private ReduceExecutor reduceExecutor;

    public List<Tuple<Pair<String,String>>> reduce(List<Tuple<Pair<String, Collection<String>>>> shuffleOutput) {
        List<Tuple<Pair<String, String>>> response = new ArrayList<>();
        PartialTask partialTask = new ReducePartialTask(shuffleOutput);
        Collection<Future<PartialTaskResult>> reduceResponseFuture = nodeManager.submit(ImmutableSet.of(partialTask), reduceExecutor);
        for (Future<PartialTaskResult> partialTaskResultFuture : reduceResponseFuture) {
            try {
                ReduceResponse partialTaskResult = (ReduceResponse) partialTaskResultFuture.get();
                response.addAll(partialTaskResult.getTuples());
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        }
        return response;
    }
}
