package com.kuchbhi.jobhi.node;

import com.kuchbhi.jobhi.task.PartialTask;
import com.kuchbhi.jobhi.task.PartialTaskResult;
import com.kuchbhi.jobhi.task.TaskExecutor;
import lombok.AllArgsConstructor;

import java.util.*;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.Future;

/**
 * Created with IntelliJ IDEA.
 * User: yoihenba.k (yoihenba.k@flipkart.com)
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
