package com.ykongbam.task.mapReduce.map;

import com.ykongbam.task.PartialTaskResult;
import com.ykongbam.task.TaskExecutor;
import com.ykongbam.task.Tuple;
import lombok.AllArgsConstructor;
import org.apache.commons.lang3.tuple.Pair;

import java.util.ArrayList;
import java.util.Collection;
import java.util.stream.Collectors;

/**
 * Created with IntelliJ IDEA.
 * User: ykongbam (ykongbam@gmail.com)
 * Date: 23/06/17
 */

@AllArgsConstructor
public class MapperExecutor implements TaskExecutor<MapPartialTask> {
    private Mapper<String, String, String, String> mapper;

    @Override
    public PartialTaskResult apply(MapPartialTask mapPartialTask) {
        Collection<Tuple<Pair<String, String>>> resultTuples = new ArrayList<>();
        Collection<Tuple<Pair<String, String>>> tuples = mapPartialTask.getTuples();

        for (Tuple<Pair<String, String>> inputTuple : tuples) {
            Collection<Pair<String, String>> mapResponse = mapper.map(inputTuple.get().getKey(), inputTuple.get().getValue());
            resultTuples.addAll(
                    mapResponse.stream()
                            .map(Tuple::new)
                            .collect(Collectors.toCollection(ArrayList::new)));
        }
        return new MapperResponse(resultTuples);
    }
}
