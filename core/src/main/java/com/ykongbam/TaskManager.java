package com.ykongbam;

import com.ykongbam.task.Task;
import com.ykongbam.task.TaskResponse;
import lombok.AllArgsConstructor;

import java.util.concurrent.Future;

/**
 * Created with IntelliJ IDEA.
 * User: ykongbam.k (ykongbam.k@flipkart.com)
 * Date: 23/06/17
 */

@AllArgsConstructor
public class TaskManager {

    Future<? extends TaskResponse> submit(Task task) {
        return task.process();
    }

}
