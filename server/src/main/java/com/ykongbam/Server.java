package com.ykongbam;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSet;
import com.ykongbam.node.LocalNodeManager;
import com.ykongbam.node.LocalThreadNode;
import com.ykongbam.node.NodeManager;
import com.ykongbam.task.TaskResponse;
import com.ykongbam.task.Tuple;
import com.ykongbam.task.mapReduce.MapReduceTask;
import com.ykongbam.task.mapReduce.executors.wordCount.WordCountMapper;
import com.ykongbam.task.mapReduce.map.MapperExecutor;
import com.ykongbam.task.mapReduce.executors.wordCount.ReduceExecutor;
import com.ykongbam.task.mapReduce.executors.wordCount.ShuffleExecutor;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/**
 * Created with IntelliJ IDEA.
 * User: ykongbam (ykongbam@gmail.com)
 * Date: 23/06/17
 */

public class Server {

    public static void main(String[] args) {
        NodeManager nodeManager = new LocalNodeManager(ImmutableList.of(new LocalThreadNode("device1"), new LocalThreadNode("device2")));
        TaskManager taskManager = new TaskManager();
//        Future<? extends TaskResponse> taskResponseFuture = taskManager.submit(new AdditionTask(nodeManager,
//                ImmutableSet.of(new Tuple<>(1), new Tuple<>(2), new Tuple<>(3), new Tuple<>(4), new Tuple<>(6.0),  new Tuple<>(8.0))));

        ImmutableSet<Tuple<Pair<String, String>>> tuples = ImmutableSet.of(
                new Tuple<>(ImmutablePair.of("file", "mary")),
                new Tuple<>(ImmutablePair.of("file", "mary")),
                new Tuple<>(ImmutablePair.of("file", "little")),
                new Tuple<>(ImmutablePair.of("file", "little")),
                new Tuple<>(ImmutablePair.of("file", "mary")),
                new Tuple<>(ImmutablePair.of("file", "lamb"))
        );
        Future<? extends TaskResponse> taskResponseFuture = taskManager.submit(
                new MapReduceTask(
                        nodeManager,
                        new MapperExecutor(new WordCountMapper()),
                        new ShuffleExecutor(),
                        new ReduceExecutor(),
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
