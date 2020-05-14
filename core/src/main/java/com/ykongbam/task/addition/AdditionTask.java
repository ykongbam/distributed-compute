package com.ykongbam.task.addition;

import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Iterables;
import com.ykongbam.node.NodeManager;
import com.ykongbam.task.PartialTask;
import com.ykongbam.task.PartialTaskResult;
import com.ykongbam.task.Task;
import com.ykongbam.task.Tuple;
import lombok.AllArgsConstructor;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * Created with IntelliJ IDEA.
 * User: ykongbam (ykongbam@gmail.com)
 * Date: 23/06/17
 */

@AllArgsConstructor
public class AdditionTask implements Task {
    NodeManager nodeManager;
    List<Tuple<Number>> tuples;

    @Override
    public Future<AdditionTaskResponse> process() {
        Set<PartialTask> partialTasks = splitTask();
        return joinTask(partialTasks);
    }


    private Set<PartialTask> splitTask() {
        int activeNodeCount = nodeManager.getActiveNodeCount();
        int partitionSize = tuples.size() / activeNodeCount;
        Set<PartialTask> partialTasks = new HashSet<>();
        for (List<Tuple<Number>> partialTuples : Iterables.partition(tuples, partitionSize)) {
            partialTasks.add(new AdditionPartialTask(partialTuples));
        }
        return partialTasks;
    }

    public Future<AdditionTaskResponse> joinTask(Set<PartialTask> partialTasks) {
        Collection<Future<PartialTaskResult>> results = nodeManager.submit(partialTasks, new AdditionTaskExecutor());

        return new Future<AdditionTaskResponse>() {
            @Override
            public boolean cancel(boolean mayInterruptIfRunning) {
                return false;
            }

            @Override
            public boolean isCancelled() {
                return false;
            }

            @Override
            public boolean isDone() {
                return false;
            }

            @Override
            public AdditionTaskResponse get() throws InterruptedException, ExecutionException {
                Set<AdditionPartialTaskResult> partialTaskResults = new HashSet<>();
                Double result = new Double("0.00");
                for (Future<PartialTaskResult> taskFuture : results) {
                    PartialTaskResult partialTaskResult = taskFuture.get();
                    partialTaskResults.add((AdditionPartialTaskResult) partialTaskResult);

                }
                for (AdditionPartialTaskResult partialTaskResult : partialTaskResults) {
                    for (Tuple<Number> tuple : partialTaskResult.getTuples())
                        result =  result + (Double) tuple.get();
                }
                return new AdditionTaskResponse(ImmutableSet.of(new Tuple<>(result)));
            }

            @Override
            public AdditionTaskResponse get(long timeout, TimeUnit unit) throws InterruptedException, ExecutionException, TimeoutException {
                return null;
            }
        };
    }
}
