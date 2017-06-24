package com.ykongbam.task.addition;

import com.ykongbam.task.PartialTask;
import com.ykongbam.task.Tuple;
import lombok.AllArgsConstructor;
import lombok.ToString;

import java.util.Collection;

/**
 * Created with IntelliJ IDEA.
 * User: ykongbam.k (ykongbam.k@flipkart.com)
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
