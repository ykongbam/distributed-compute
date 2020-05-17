package com.ykongbam.task.mapReduce;

import com.ykongbam.task.TaskResponse;
import com.ykongbam.task.Tuple;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.apache.commons.lang3.tuple.Pair;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: ykongbam (ykongbam@gmail.com)
 * Date: 23/06/17
 */

@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class MapReduceTaskResponse implements TaskResponse {
    private final List<Tuple<Pair<String, String>>> tuples;
}
