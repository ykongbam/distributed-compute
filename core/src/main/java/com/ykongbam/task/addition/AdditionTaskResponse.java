package com.ykongbam.task.addition;

import com.ykongbam.task.TaskResponse;
import com.ykongbam.task.Tuple;
import lombok.AllArgsConstructor;
import lombok.ToString;

import java.util.Set;

/**
 * Created with IntelliJ IDEA.
 * User: ykongbam (ykongbam@gmail.com)
 * Date: 23/06/17
 */

@AllArgsConstructor
@ToString
public class AdditionTaskResponse implements TaskResponse{
    Set<Tuple<Number>> tuples;
}
