package com.ykongbam.task.mapReduce.shuffleSort;

import com.ykongbam.task.PartialTaskResult;
import com.ykongbam.task.Tuple;
import lombok.AllArgsConstructor;
import lombok.ToString;
import org.apache.commons.lang3.tuple.Pair;

import java.util.PriorityQueue;

/**
 * Created with IntelliJ IDEA.
 * User: ykongbam (ykongbam@gmail.com)
 * Date: 23/06/17
 */

@AllArgsConstructor
@ToString
public class ShuffleResponse implements PartialTaskResult<Pair<String, String>> {
    PriorityQueue<Tuple<Pair<String, String>>> tuples;

    @Override
    public PriorityQueue<Tuple<Pair<String, String>>> getTuples() {
        return tuples;
    }
}
