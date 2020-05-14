package com.ykongbam.node;

import com.ykongbam.task.PartialTask;
import com.ykongbam.task.PartialTaskResult;
import com.ykongbam.task.TaskExecutor;

import java.util.Collection;
import java.util.concurrent.Future;

/**
 * Created with IntelliJ IDEA.
 * User: ykongbam (ykongbam@gmail.com)
 * Date: 23/06/17
 */

public interface NodeManager {

    int getActiveNodeCount();
    Collection<Future<PartialTaskResult>> submit(Collection<PartialTask> partialTasks, TaskExecutor taskExecutor);
}
