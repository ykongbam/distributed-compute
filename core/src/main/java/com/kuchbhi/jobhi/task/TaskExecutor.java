package com.kuchbhi.jobhi.task;

import java.util.function.Function;

/**
 * Created with IntelliJ IDEA.
 * User: yoihenba.k (yoihenba.k@flipkart.com)
 * Date: 23/06/17
 */

public interface TaskExecutor<PartialTask> extends Function<PartialTask, PartialTaskResult> {

}
