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

```commandline
java -jar target/benchmarks.jar -gc true -jvmArgsAppend="-XX:+UseG1GC -server -Xmx1024m -Xms1024m"
```

## Results

```
Benchmark                                (arraySize)   Mode  Cnt        Score       Error  Units
FilterBenchmark.multipleFilters                   10  thrpt  100  2075839.682 ? 44630.046  ops/s
FilterBenchmark.multipleFilters                  100  thrpt  100   818681.739 ? 12685.772  ops/s
FilterBenchmark.multipleFilters                 1000  thrpt  100   112650.014 ?  1564.992  ops/s
FilterBenchmark.multipleFilters                10000  thrpt  100    11865.622 ?   174.080  ops/s
FilterBenchmark.multipleFilters               100000  thrpt  100     1517.454 ?    33.562  ops/s
FilterBenchmark.multipleFilters              1000000  thrpt  100      128.234 ?     3.729  ops/s
FilterBenchmark.multipleFiltersParallel           10  thrpt  100    23671.753 ?   345.838  ops/s
FilterBenchmark.multipleFiltersParallel          100  thrpt  100    22223.019 ?   240.080  ops/s
FilterBenchmark.multipleFiltersParallel         1000  thrpt  100    19721.119 ?   226.894  ops/s
FilterBenchmark.multipleFiltersParallel        10000  thrpt  100    12232.491 ?   211.326  ops/s
FilterBenchmark.multipleFiltersParallel       100000  thrpt  100     3060.223 ?   100.179  ops/s
FilterBenchmark.multipleFiltersParallel      1000000  thrpt  100      420.251 ?    30.058  ops/s
FilterBenchmark.oldFashionFilters                 10  thrpt  100  5496434.401 ? 35947.633  ops/s
FilterBenchmark.oldFashionFilters                100  thrpt  100  1516603.187 ? 12593.856  ops/s
FilterBenchmark.oldFashionFilters               1000  thrpt  100   189934.246 ?   624.064  ops/s
FilterBenchmark.oldFashionFilters              10000  thrpt  100    19155.646 ?    93.957  ops/s
FilterBenchmark.oldFashionFilters             100000  thrpt  100     1892.971 ?     8.023  ops/s
FilterBenchmark.oldFashionFilters            1000000  thrpt  100      159.529 ?     5.704  ops/s
FilterBenchmark.oneFilter                         10  thrpt  100  2832471.559 ? 41248.974  ops/s
FilterBenchmark.oneFilter                        100  thrpt  100   898120.270 ? 42906.528  ops/s
FilterBenchmark.oneFilter                       1000  thrpt  100   133993.137 ?   698.773  ops/s
FilterBenchmark.oneFilter                      10000  thrpt  100    13844.118 ?   156.972  ops/s
FilterBenchmark.oneFilter                     100000  thrpt  100     1455.707 ?     9.031  ops/s
FilterBenchmark.oneFilter                    1000000  thrpt  100      128.531 ?     3.197  ops/s
FilterBenchmark.oneFilterParallel                 10  thrpt  100    23714.956 ?   342.890  ops/s
FilterBenchmark.oneFilterParallel                100  thrpt  100    22299.120 ?   244.461  ops/s
FilterBenchmark.oneFilterParallel               1000  thrpt  100    19493.699 ?   288.293  ops/s
FilterBenchmark.oneFilterParallel              10000  thrpt  100    12274.638 ?   423.781  ops/s
FilterBenchmark.oneFilterParallel             100000  thrpt  100     4613.809 ?   128.038  ops/s
FilterBenchmark.oneFilterParallel            1000000  thrpt  100      428.808 ?    37.583  ops/s
```


### 10 Element Array

<img width="599" alt="10 element array" src="https://user-images.githubusercontent.com/4140597/54838691-1ff41600-4cc1-11e9-9021-14ea1673a037.png">

### 100 Element Array

<img width="594" alt="100 element array" src="https://user-images.githubusercontent.com/4140597/54838780-5a5db300-4cc1-11e9-9579-4fd4a8845c2f.png">

### 1,000 Element Array

<img width="594" alt="1000 element array" src="https://user-images.githubusercontent.com/4140597/54838848-7a8d7200-4cc1-11e9-981e-28f4382e9021.png">

### 10,000 Element Array

<img width="592" alt="10000 element array" src="https://user-images.githubusercontent.com/4140597/54838893-97c24080-4cc1-11e9-941c-5d352f01ab8c.png">

### 100,000 Element Array

<img width="591" alt="100000 element array" src="https://user-images.githubusercontent.com/4140597/54838948-b4f70f00-4cc1-11e9-9ef6-a003e15d2f36.png">

### 1,000,000 Element Array

<img width="596" alt="1000000 element array" src="https://user-images.githubusercontent.com/4140597/54838993-d7892800-4cc1-11e9-9c75-80474bc6036f.png">

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
