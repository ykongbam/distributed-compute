package com.ykongbam.node;

import com.ykongbam.task.PartialTask;
import com.ykongbam.task.PartialTaskResult;
import com.ykongbam.task.TaskExecutor;
import lombok.AllArgsConstructor;

import java.util.*;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.Future;

/**
 * Created with IntelliJ IDEA.
 * User: ykongbam.k (ykongbam.k@flipkart.com)
 * Date: 23/06/17
 */

@AllArgsConstructor
public class LocalNodeManager implements NodeManager{
    List<Node> nodes;

    @Override
    public int getActiveNodeCount() {
        return nodes.size();
    }

    @Override
    public Set<Future<PartialTaskResult>> submit(Set<PartialTask> partialTasks, TaskExecutor taskExecutor) {
        Queue<PartialTask> queue = new ConcurrentLinkedQueue<>(partialTasks);
        Set<Future<PartialTaskResult>> response = new HashSet<>();
        while (queue.size() != 0) {
            for (Node node : nodes) {
                PartialTask task = queue.poll();
                if(task != null) {
                    Future<PartialTaskResult> futureTaskResult = node.submit(task, taskExecutor);
                    response.add(futureTaskResult);
                }
            }
        }
        return response;
    }
}
