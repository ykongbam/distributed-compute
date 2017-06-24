package com.ykongbam.node;

import com.ykongbam.task.PartialTask;
import com.ykongbam.task.PartialTaskResult;
import com.ykongbam.task.TaskExecutor;

import java.util.Set;
import java.util.concurrent.Future;

/**
 * Created with IntelliJ IDEA.
 * User: ykongbam.k (ykongbam.k@flipkart.com)
 * Date: 23/06/17
 */

public interface NodeManager {

    int getActiveNodeCount();
    Set<Future<PartialTaskResult>> submit(Set<PartialTask> partialTasks, TaskExecutor taskExecutor);
}
