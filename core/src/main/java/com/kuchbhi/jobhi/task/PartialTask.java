package com.kuchbhi.jobhi.task;

import java.util.Collection;

/**
 * Created with IntelliJ IDEA.
 * User: yoihenba.k (yoihenba.k@flipkart.com)
 * Date: 23/06/17
 */

public interface PartialTask<T> {
    Collection<Tuple<T>> getTuples();
}
