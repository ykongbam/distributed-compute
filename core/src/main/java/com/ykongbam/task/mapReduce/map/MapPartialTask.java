package com.ykongbam.task.mapReduce.map;

import com.ykongbam.task.PartialTask;
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
public class MapPartialTask implements PartialTask<Pair<String, String>> {
    Collection<Tuple<Pair<String, String>>> tuples;

    @Override
    public Collection<Tuple<Pair<String, String>>> getTuples() {
        return tuples;
    }
}
