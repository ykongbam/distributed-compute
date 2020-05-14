package com.ykongbam;

import com.google.common.collect.ImmutableList;
import com.ykongbam.node.LocalNodeManager;
import com.ykongbam.node.LocalThreadNode;
import com.ykongbam.node.NodeManager;
import com.ykongbam.task.TaskResponse;
import com.ykongbam.task.Tuple;
import com.ykongbam.task.mapReduce.MapReduceTask;
import com.ykongbam.task.mapReduce.executors.wordCount.ReduceExecutor;
import com.ykongbam.task.mapReduce.executors.wordCount.WordCountMapper;
import com.ykongbam.task.mapReduce.map.MapStage;
import com.ykongbam.task.mapReduce.map.MapperExecutor;
import com.ykongbam.task.mapReduce.reduce.ReduceStage;
import com.ykongbam.task.mapReduce.shuffleSort.ShuffleExecutor;
import com.ykongbam.task.mapReduce.shuffleSort.ShuffleStage;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/**
 * Created with IntelliJ IDEA.
 * User: ykongbam (ykongbam@gmail.com)
 * Date: 23/06/17
 */

public class MapReduceExample {

    public static void main(String[] args) {
        NodeManager nodeManager = new LocalNodeManager(ImmutableList.of(new LocalThreadNode("device1"), new LocalThreadNode("device2")));
        TaskManager taskManager = new TaskManager();

        ImmutableList<Tuple<Pair<String, String>>> tuples = ImmutableList.of(
                new Tuple<>(ImmutablePair.of("file", "mary")),
                new Tuple<>(ImmutablePair.of("file", "mary")),
                new Tuple<>(ImmutablePair.of("file", "little")),
                new Tuple<>(ImmutablePair.of("file", "little")),
                new Tuple<>(ImmutablePair.of("file", "mary")),
                new Tuple<>(ImmutablePair.of("file", "lamb")),
                new Tuple<>(ImmutablePair.of("file", "mary had a little lamb"))
        );
        Future<? extends TaskResponse> taskResponseFuture = taskManager.submit(
                new MapReduceTask(
                        new MapStage(nodeManager, new MapperExecutor(new WordCountMapper())),
                        new ShuffleStage(nodeManager, new ShuffleExecutor()),
                        new ReduceStage(nodeManager, new ReduceExecutor()),
                        tuples));
        TaskResponse taskResponse = null;
        try {
            taskResponse = taskResponseFuture.get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        System.out.println(taskResponse);
    }
}
