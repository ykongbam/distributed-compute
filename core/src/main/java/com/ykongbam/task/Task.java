package com.ykongbam.task;

import java.util.concurrent.Future;

/**
 * Created with IntelliJ IDEA.
 * User: ykongbam (ykongbam@gmail.com)
 * Date: 23/06/17
 */

public interface Task {
    Future<? extends TaskResponse> process();
}
