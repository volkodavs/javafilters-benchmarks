# Java Filter Benchmarks

## Introduction

We're going to perform benchmarks against the different way to filter values in the array 

* stream + one filter
* stream + multiple filters 
* parallel stream + one filter 
* parallel stream + multiple filters
* old fashion java iteration 

### Stream + one filter

```java
public void oneFilter(ExecutionPlan plan, Blackhole blackhole) {
        long count = plan.getDoubles()
                .stream()
                .filter(d -> d < Math.PI
                        && d > Math.E
                        && d != 3
                        && d != 2)
                .count();

        blackhole.consume(count);
    }
```

### Stream + multiple filters 

```java
public void multipleFilters(ExecutionPlan plan, Blackhole blackhole) {
        long count = plan.getDoubles()
                .stream()
                .filter(d -> d > Math.PI)
                .filter(d -> d < Math.E)
                .filter(d -> d != 3)
                .filter(d -> d != 2)
                .count();

        blackhole.consume(count);
    }
```

### Parallel stream + one filter 

```java
public void oneFilterParallel(ExecutionPlan plan, Blackhole blackhole) {
        long count = plan.getDoubles()
                .stream()
                .parallel()
                .filter(d -> d < Math.PI
                        && d > Math.E
                        && d != 3
                        && d != 2)
                .count();

        blackhole.consume(count);
    }
```

### Parallel stream + multiple filters

```java
public void multipleFiltersParallel(ExecutionPlan plan, Blackhole blackhole) {
        long count = plan.getDoubles()
                .stream()
                .parallel()
                .filter(d -> d > Math.PI)
                .filter(d -> d < Math.E)
                .filter(d -> d != 3)
                .filter(d -> d != 2)
                .count();

        blackhole.consume(count);
    }

```

### Old fashion java iteration 

```java
public void oldFashionFilters(ExecutionPlan plan, Blackhole blackhole) {
        long count = 0;
        for (int i = 0; i < plan.getDoubles().size(); i++) {
            if (plan.getDoubles().get(i) > Math.PI
                    && plan.getDoubles().get(i) > Math.E
                    && plan.getDoubles().get(i) != 3
                    && plan.getDoubles().get(i) != 2) {
                count = count + 1;
            }
        }

        blackhole.consume(count);
    }

```

## Environment 

* 8 CPU
* 64 GB RAM 
* OS version: 16.04.1 LTS (Xenial Xerus)
 

## Run benchmarks

### Java 8

```commandline
docker run -it volkodav/java-filter-benchmark:java8
```

#### Params

```
# VM version: JDK 1.8.0_181, VM 25.181-b13
# VM invoker: /usr/lib/jvm/java-8-openjdk-amd64/jre/bin/java
# VM options: -XX:+UseG1GC -server -Xmx1024m -Xms1024m
# Warmup: 10 iterations, 1 s each
# Measurement: 10 iterations, 1000 ms each
# Timeout: 10 min per iteration
# Threads: 1 thread, will synchronize iterations
# Benchmark mode: Throughput, ops/time
# Benchmark: com.sergeyvolkodav.FilterBenchmark.multipleFilters
```

#### Results

```

```


### Java 12 
```commandline
docker run -it volkodav/java-filter-benchmark:java12
```

#### Params 

```
# VM version: JDK 12, VM 12+33
# VM invoker: /usr/java/openjdk-12/bin/java
# VM options: -XX:+UseG1GC -server -Xmx1024m -Xms1024m
# Warmup: 10 iterations, 1 s each
# Measurement: 10 iterations, 1000 ms each
# Timeout: 10 min per iteration
# Threads: 1 thread, will synchronize iterations
# Benchmark mode: Throughput, ops/time
# Benchmark: com.sergeyvolkodav.FilterBenchmark.multipleFilters
```

#### Results

```
```



### 10 Element Array

### 100 Element Array

### 1,000 Element Array

### 10,000 Element Array

### 100,000 Element Array

### 1,000,000 Element Array


## Summary 

On a relatively small array old fashion loop with one if clause shows the best performance results, 
but while an array is growing java parallel streams show better results. 
According to performance results, for a performance perspective, one filter is better than multiple filters.

## License
-------
    MIT License
    
    Permission is hereby granted, free of charge, to any person obtaining a copy
    of this software and associated documentation files (the "Software"), to deal
    in the Software without restriction, including without limitation the rights
    to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
    copies of the Software, and to permit persons to whom the Software is
    furnished to do so, subject to the following conditions:
    
    The above copyright notice and this permission notice shall be included in all
    copies or substantial portions of the Software.
