package com.ykongbam.task.mapReduce.map;

import com.ykongbam.task.PartialTaskResult;
import com.ykongbam.task.Tuple;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import org.apache.commons.lang3.tuple.Pair;

import java.util.Collection;

/**
 * Created with IntelliJ IDEA.
 * User: ykongbam (ykongbam@gmail.com)
 * Date: 23/06/17
 */

@AllArgsConstructor
@EqualsAndHashCode
public class MapperResponse implements PartialTaskResult<Pair<String, String>> {
    final Collection<Tuple<Pair<String, String>>> tuples;
    @Override
    public Collection<Tuple<Pair<String, String>>> getTuples() {
        return tuples;
    }
}
