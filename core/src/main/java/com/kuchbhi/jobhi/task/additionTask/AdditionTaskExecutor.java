package com.kuchbhi.jobhi.task.additionTask;

import com.google.common.collect.ImmutableSet;
import com.kuchbhi.jobhi.task.PartialTaskResult;
import com.kuchbhi.jobhi.task.TaskExecutor;
import com.kuchbhi.jobhi.task.Tuple;

import java.util.Collection;

/**
 * Created with IntelliJ IDEA.
 * User: yoihenba.k (yoihenba.k@flipkart.com)
 * Date: 23/06/17
 */

public class AdditionTaskExecutor implements TaskExecutor<AdditionPartialTask>{

    public PartialTaskResult apply(AdditionPartialTask additionPartialTask) {
        Collection<Tuple<Number>> resultTuples = null;
        if (additionPartialTask.getTuples() == null)
            resultTuples = ImmutableSet.of(new Tuple<>(0));
        else if(additionPartialTask.getTuples().size() == 1) {
            resultTuples =  additionPartialTask.getTuples();
        } else {
            Number result = additionPartialTask.getTuples().stream()
                    .map(Tuple::get)
                    .reduce((a, b) -> a.doubleValue() + b.doubleValue()).get();
            resultTuples = ImmutableSet.of(new Tuple<>(result));
        }
        return new AdditionPartialTaskResult(resultTuples);
    }
}
