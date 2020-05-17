package com.ykongbam.task.mapReduce.shuffleSort;

import com.ykongbam.node.NodeManager;
import com.ykongbam.task.PartialTask;
import com.ykongbam.task.PartialTaskResult;
import com.ykongbam.task.Tuple;
import com.ykongbam.task.mapReduce.splitter.Splitter;
import lombok.AllArgsConstructor;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;

import java.util.*;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;


@AllArgsConstructor
public class ShuffleStage {
    private NodeManager nodeManager;
    private ShuffleExecutor shuffleExecutor;
    private Splitter<String, String> splitter;

    public List<Tuple<Pair<String, Collection<String>>>> shuffle(List<Tuple<Pair<String, String>>> intermediateOutput) {
        List<Tuple<Pair<String, Collection<String>>>> response = new ArrayList<>();
        List<PartialTask> partialTasks = splitter.splitInput(intermediateOutput);
        List<ShuffleResponse> partialTaskResults = new ArrayList<>();

        Collection<Future<PartialTaskResult>> shuffleResponseFuture = nodeManager.submit(partialTasks, shuffleExecutor);
        for (Future<PartialTaskResult> partialTaskResultFuture : shuffleResponseFuture) {
            try {
                partialTaskResults.add((ShuffleResponse) partialTaskResultFuture.get());
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        }

        PriorityQueue<Pair<Tuple<Pair<String, String>>, PriorityQueue<Tuple<Pair<String, String>>>>> combined = new PriorityQueue<>(
                1,
                Comparator.comparing(t -> t.getKey().get().getKey()));

        partialTaskResults.stream()
                .filter(shuffleResponse -> !shuffleResponse.tuples.isEmpty())
                .map(partialTask -> ImmutablePair.of(partialTask.getTuples().poll(), partialTask.getTuples()))
                .forEach(combined::add);

        Map<String, List<String>> treeMap = new TreeMap<>();
        while (!combined.isEmpty()) {
            Pair<Tuple<Pair<String, String>>, PriorityQueue<Tuple<Pair<String, String>>>> poll = combined.poll();
            Tuple<Pair<String, String>> tuple = poll.getKey();
            List<String> valueList = treeMap.getOrDefault(tuple.get().getKey(), new ArrayList<>());
            valueList.add(tuple.get().getValue());
            treeMap.put(tuple.get().getKey(), valueList);
            if (!poll.getValue().isEmpty()) {
                combined.add(ImmutablePair.of(poll.getValue().poll(), poll.getValue()));
            }
        }
        treeMap.entrySet().stream()
                .map(entry -> new Tuple<Pair<String, Collection<String>>>(ImmutablePair.of(entry.getKey(), entry.getValue())))
                .forEach(response::add);
        return response;
    }

}
