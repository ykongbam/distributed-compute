package com.kuchbhi.jobhi.task.additionTask;

import com.kuchbhi.jobhi.task.PartialTaskResult;
import com.kuchbhi.jobhi.task.Tuple;
import lombok.AllArgsConstructor;

import java.util.Collection;

/**
 * Created with IntelliJ IDEA.
 * User: yoihenba.k (yoihenba.k@flipkart.com)
 * Date: 23/06/17
 */

@AllArgsConstructor
public class AdditionPartialTaskResult implements PartialTaskResult<Number> {
    Collection<Tuple<Number>> tuples;

    @Override
    public Collection<Tuple<Number>> getTuples() {
        return tuples;
    }
}
