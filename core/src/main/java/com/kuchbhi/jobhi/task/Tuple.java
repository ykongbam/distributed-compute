package com.kuchbhi.jobhi.task;

import lombok.AllArgsConstructor;
import lombok.ToString;

/**
 * Created with IntelliJ IDEA.
 * User: yoihenba.k (yoihenba.k@flipkart.com)
 * Date: 23/06/17
 */

@AllArgsConstructor
@ToString
public class Tuple<T> {
    private T value;
    public T get() {
        return value;
    }
}
