package com.kuchbhi.jobhi;

import com.kuchbhi.jobhi.task.Task;
import com.kuchbhi.jobhi.task.TaskResponse;
import lombok.AllArgsConstructor;

import java.util.concurrent.Future;

/**
 * Created with IntelliJ IDEA.
 * User: yoihenba.k (yoihenba.k@flipkart.com)
 * Date: 23/06/17
 */

@AllArgsConstructor
public class TaskManager {

    Future<? extends TaskResponse> submit(Task task) {
        return task.process();
    }

}
