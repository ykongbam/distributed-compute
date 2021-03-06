package com.ykongbam.task;

import java.util.function.Function;

/**
 * Created with IntelliJ IDEA.
 * User: ykongbam (ykongbam@gmail.com)
 * Date: 23/06/17
 */

public interface TaskExecutor<PartialTask> extends Function<PartialTask, PartialTaskResult> {

}
