package com.kuchbhi.jobhi;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSet;
import com.kuchbhi.jobhi.node.LocalNodeManager;
import com.kuchbhi.jobhi.node.LocalThreadNode;
import com.kuchbhi.jobhi.node.NodeManager;
import com.kuchbhi.jobhi.task.TaskResponse;
import com.kuchbhi.jobhi.task.Tuple;
import com.kuchbhi.jobhi.task.additionTask.AdditionTask;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/**
 * Created with IntelliJ IDEA.
 * User: yoihenba.k (yoihenba.k@flipkart.com)
 * Date: 23/06/17
 */

public class Server {

    public static void main(String[] args) {
        NodeManager nodeManager = new LocalNodeManager(ImmutableList.of(new LocalThreadNode("device1"), new LocalThreadNode("device2")));
        TaskManager taskManager = new TaskManager();
        Future<? extends TaskResponse> taskResponseFuture = taskManager.submit(new AdditionTask(nodeManager, ImmutableSet.of(new Tuple<>(1), new Tuple<>(2), new Tuple<>(3), new Tuple<>(4),new Tuple<>(6.0))));
        TaskResponse taskResponse = null;
        try {
            taskResponse = taskResponseFuture.get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        System.out.println(taskResponse);
    }
}
