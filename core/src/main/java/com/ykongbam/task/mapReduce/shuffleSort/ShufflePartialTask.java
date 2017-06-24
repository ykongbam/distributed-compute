package com.ykongbam.task.mapReduce.shuffleSort;

import com.ykongbam.task.PartialTask;
import com.ykongbam.task.Tuple;
import lombok.AllArgsConstructor;
import lombok.ToString;
import org.apache.commons.lang3.tuple.Pair;

import java.util.Collection;

/**
 * Created with IntelliJ IDEA.
 * User: ykongbam.k (ykongbam.k@flipkart.com)
 * Date: 23/06/17
 */

@AllArgsConstructor
@ToString
public class ShufflePartialTask implements PartialTask<Pair<String, String>> {
    Collection<Tuple<Pair<String, String>>> tuples;

    @Override
    public Collection<Tuple<Pair<String, String>>> getTuples() {
        return tuples;
    }
}
