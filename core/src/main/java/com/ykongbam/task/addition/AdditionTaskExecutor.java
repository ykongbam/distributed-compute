package com.ykongbam.task.addition;

import com.google.common.collect.ImmutableSet;
import com.ykongbam.task.PartialTaskResult;
import com.ykongbam.task.TaskExecutor;
import com.ykongbam.task.Tuple;

import java.util.Collection;

/**
 * Created with IntelliJ IDEA.
 * User: ykongbam (ykongbam@gmail.com)
 * Date: 23/06/17
 */

public class AdditionTaskExecutor implements TaskExecutor<AdditionPartialTask>{

    public PartialTaskResult apply(AdditionPartialTask additionPartialTask) {
        Collection<Tuple<Number>> resultTuples;
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
