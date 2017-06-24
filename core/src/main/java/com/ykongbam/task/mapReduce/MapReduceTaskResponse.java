package com.ykongbam.task.mapReduce;

import com.ykongbam.task.TaskResponse;
import com.ykongbam.task.Tuple;
import org.apache.commons.lang3.tuple.Pair;

import java.util.Set;

/**
 * Created with IntelliJ IDEA.
 * User: ykongbam.k (ykongbam.k@flipkart.com)
 * Date: 23/06/17
 */

public class MapReduceTaskResponse implements TaskResponse {
    private Set<Tuple<Pair<String, String>>> tuples;
}
