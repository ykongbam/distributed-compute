package com.ykongbam.task.mapReduce.map;

import com.ykongbam.task.PartialTaskResult;
import com.ykongbam.task.Tuple;
import lombok.AllArgsConstructor;
import org.apache.commons.lang3.tuple.Pair;

import java.util.Collection;

/**
 * Created with IntelliJ IDEA.
 * User: ykongbam.k (ykongbam.k@flipkart.com)
 * Date: 23/06/17
 */

@AllArgsConstructor
public class MapperResponse implements PartialTaskResult<Pair<String, String>> {
    Collection<Tuple<Pair<String, String>>> tuples;
    @Override
    public Collection<Tuple<Pair<String, String>>> getTuples() {
        return null;
    }
}
