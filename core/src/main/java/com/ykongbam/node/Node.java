package com.ykongbam.node;

import com.ykongbam.task.PartialTask;
import com.ykongbam.task.PartialTaskResult;
import com.ykongbam.task.TaskExecutor;

import java.util.concurrent.Future;

/**
 * Created with IntelliJ IDEA.
 * User: ykongbam (ykongbam@gmail.com)
 * Date: 23/06/17
 */

public interface Node {
    long getRemainingCapacity();
    Future<PartialTaskResult> submit(PartialTask partialTask, TaskExecutor<PartialTask> taskExecutor);
}
