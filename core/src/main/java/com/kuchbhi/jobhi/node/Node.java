package com.kuchbhi.jobhi.node;

import com.kuchbhi.jobhi.task.PartialTask;
import com.kuchbhi.jobhi.task.PartialTaskResult;
import com.kuchbhi.jobhi.task.TaskExecutor;

import java.util.concurrent.Future;

/**
 * Created with IntelliJ IDEA.
 * User: yoihenba.k (yoihenba.k@flipkart.com)
 * Date: 23/06/17
 */

public interface Node {
    long getRemainingCapacity();
    Future<PartialTaskResult> submit(PartialTask partialTask, TaskExecutor<PartialTask> taskExecutor);
}
