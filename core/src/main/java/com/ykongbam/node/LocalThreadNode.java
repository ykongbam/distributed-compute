package com.ykongbam.node;

import com.ykongbam.task.PartialTask;
import com.ykongbam.task.PartialTaskResult;
import com.ykongbam.task.TaskExecutor;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * Created with IntelliJ IDEA.
 * User: ykongbam.k (ykongbam.k@flipkart.com)
 * Date: 23/06/17
 */

public class LocalThreadNode implements Node {
    private String name;

    private ExecutorService executorService;

    public LocalThreadNode(String name) {
        this.name = name;
        this.executorService = Executors.newFixedThreadPool(1);
    }

    @Override
    public long getRemainingCapacity() {
        return 0;
    }

    public Future<PartialTaskResult> submit(PartialTask partialTask, TaskExecutor<PartialTask> taskExecutor) {
        System.out.println(partialTask + name);
        return executorService.submit(() -> taskExecutor.apply(partialTask));
    }
}
