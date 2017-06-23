package com.kuchbhi.jobhi.task;

import java.util.concurrent.Future;

/**
 * Created with IntelliJ IDEA.
 * User: yoihenba.k (yoihenba.k@flipkart.com)
 * Date: 23/06/17
 */

public interface Task {
    Future<? extends TaskResponse> process();
}
