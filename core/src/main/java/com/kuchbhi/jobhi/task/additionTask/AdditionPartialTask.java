package com.kuchbhi.jobhi.task.additionTask;

import com.kuchbhi.jobhi.task.PartialTask;
import com.kuchbhi.jobhi.task.Tuple;
import lombok.AllArgsConstructor;
import lombok.ToString;

import java.util.Collection;

/**
 * Created with IntelliJ IDEA.
 * User: yoihenba.k (yoihenba.k@flipkart.com)
 * Date: 23/06/17
 */

@AllArgsConstructor
@ToString
public class AdditionPartialTask implements PartialTask<Number>{
    Collection<Tuple<Number>> tuples;

    @Override
    public Collection<Tuple<Number>> getTuples() {
        return tuples;
    }
}
