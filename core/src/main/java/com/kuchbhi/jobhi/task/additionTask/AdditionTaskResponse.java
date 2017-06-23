package com.kuchbhi.jobhi.task.additionTask;

import com.kuchbhi.jobhi.task.TaskResponse;
import com.kuchbhi.jobhi.task.Tuple;
import lombok.AllArgsConstructor;
import lombok.ToString;

import java.util.Set;

/**
 * Created with IntelliJ IDEA.
 * User: yoihenba.k (yoihenba.k@flipkart.com)
 * Date: 23/06/17
 */

@AllArgsConstructor
@ToString
public class AdditionTaskResponse implements TaskResponse{
    Set<Tuple<Number>> tuples;
}
