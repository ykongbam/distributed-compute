package com.ykongbam;

import com.google.common.collect.ImmutableList;
import com.ykongbam.node.LocalNodeManager;
import com.ykongbam.node.LocalThreadNode;
import com.ykongbam.node.NodeManager;
import com.ykongbam.task.TaskResponse;
import com.ykongbam.task.Tuple;
import com.ykongbam.task.addition.AdditionTask;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/**
 * Created with IntelliJ IDEA.
 * User: ykongbam (ykongbam@gmail.com)
 * Date: 23/06/17
 */

public class AdderExample {

    public static void main(String[] args) {
        NodeManager nodeManager = new LocalNodeManager(ImmutableList.of(new LocalThreadNode("device1"), new LocalThreadNode("device2")));
        TaskManager taskManager = new TaskManager();
        Future<? extends TaskResponse> taskResponseFuture = taskManager.submit(new AdditionTask(nodeManager,
                ImmutableList.of(new Tuple<>(1), new Tuple<>(2), new Tuple<>(3), new Tuple<>(4), new Tuple<>(6.0),  new Tuple<>(8.0))));

        TaskResponse taskResponse = null;
        try {
            taskResponse = taskResponseFuture.get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        System.out.println(taskResponse);
    }
}
