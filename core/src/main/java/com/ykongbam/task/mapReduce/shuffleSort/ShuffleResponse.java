package com.ykongbam.task.mapReduce.shuffleSort;

import com.ykongbam.task.PartialTaskResult;
import com.ykongbam.task.Tuple;
import lombok.AllArgsConstructor;
import lombok.ToString;
import org.apache.commons.lang3.tuple.Pair;

import java.util.Collection;

/**
 * Created with IntelliJ IDEA.
 * User: ykongbam (ykongbam@gmail.com)
 * Date: 23/06/17
 */

@AllArgsConstructor
@ToString
public class ShuffleResponse implements PartialTaskResult<Pair<String, Collection<String>>> {
    Collection<Tuple<Pair<String,Collection<String>>>> tuples;

    @Override
    public Collection<Tuple<Pair<String,Collection<String>>>> getTuples() {
        return tuples;
    }
}
