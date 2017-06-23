package com.kuchbhi.jobhi.task.mapReduce;

import com.kuchbhi.jobhi.task.TaskResponse;
import com.kuchbhi.jobhi.task.Tuple;
import org.apache.commons.lang3.tuple.Pair;

import java.util.Set;

/**
 * Created with IntelliJ IDEA.
 * User: yoihenba.k (yoihenba.k@flipkart.com)
 * Date: 23/06/17
 */

public class MapReduceTaskResponse implements TaskResponse {
    private Set<Tuple<Pair<String, String>>> tuples;
}
