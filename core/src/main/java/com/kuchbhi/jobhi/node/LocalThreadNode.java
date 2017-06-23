package com.kuchbhi.jobhi.node;

import com.kuchbhi.jobhi.task.PartialTask;
import com.kuchbhi.jobhi.task.PartialTaskResult;
import com.kuchbhi.jobhi.task.TaskExecutor;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * Created with IntelliJ IDEA.
 * User: yoihenba.k (yoihenba.k@flipkart.com)
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
