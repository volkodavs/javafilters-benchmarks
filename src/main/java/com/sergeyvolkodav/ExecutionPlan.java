package com.sergeyvolkodav;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

import org.openjdk.jmh.annotations.Level;
import org.openjdk.jmh.annotations.Param;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;

@State(Scope.Benchmark)
public class ExecutionPlan {

    private List<Double> doubles;

    @Param({"10", "100", "1000", "10000", "100000", "1000000"})
    int arraySize;

    @Setup(Level.Invocation)
    public void setUp() {
        doubles = new Random().doubles(arraySize, 1, 4)
                .boxed()
                .collect(Collectors.toList());
    }

    public List<Double> getDoubles() {
        return doubles;
    }
}
