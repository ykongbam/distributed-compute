package com.ykongbam.task;

import java.util.Collection;

/**
 * Created with IntelliJ IDEA.
 * User: ykongbam.k (ykongbam.k@flipkart.com)
 * Date: 23/06/17
 */

public interface PartialTask<T> {
    Collection<Tuple<T>> getTuples();
}
