# Java Filter Benchmarks

## Introduction

We're going to perform benchmarks against the different way to filter values in the array 

* stream + complex filter
* stream + multiple filters 
* parallel stream + complex filter 
* parallel stream + multiple filters
* old fashion java iteration 

### Stream + complex filter

```java
public void complexFilter(ExecutionPlan plan, Blackhole blackhole) {
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

### Parallel stream + complex filter 

```java
public void complexFilterParallel(ExecutionPlan plan, Blackhole blackhole) {
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
# OpenJDK Runtime Environment (build 1.8.0_181-8u181-b13-2~deb9u1-b13)
# OpenJDK 64-Bit Server VM (build 25.181-b13, mixed mode)
# VM invoker: /usr/lib/jvm/java-8-openjdk-amd64/jre/bin/java
# VM options: -XX:+UseG1GC -server -Xmx1024m -Xms1024m
# Warmup: 10 iterations, 1 s each
# Measurement: 10 iterations, 1000 ms each
# Timeout: 10 min per iteration
# Threads: 1 thread, will synchronize iterations
# Benchmark mode: Throughput, ops/time
# Benchmark: com.sergeyvolkodav.FilterBenchmark.multipleFilters
# CPU: 8 cores
```

#### Raw Results

```
# Run complete. Total time: 04:36:29

Benchmark                                (arraySize)   Mode  Cnt         Score         Error  Units
FilterBenchmark.complexFilter                     10  thrpt   50   5947577.648 ±  257535.736  ops/s
FilterBenchmark.complexFilter                    100  thrpt   50   3131081.555 ±   72868.963  ops/s
FilterBenchmark.complexFilter                   1000  thrpt   50    489666.688 ±    6539.466  ops/s
FilterBenchmark.complexFilter                  10000  thrpt   50     17297.424 ±      93.890  ops/s
FilterBenchmark.complexFilter                 100000  thrpt   50      1398.702 ±      72.820  ops/s
FilterBenchmark.complexFilter                1000000  thrpt   50        81.309 ±       0.547  ops/s
FilterBenchmark.complexFilterParallel             10  thrpt   50     24515.743 ±     450.363  ops/s
FilterBenchmark.complexFilterParallel            100  thrpt   50     25584.773 ±     290.249  ops/s
FilterBenchmark.complexFilterParallel           1000  thrpt   50     24313.066 ±     425.817  ops/s
FilterBenchmark.complexFilterParallel          10000  thrpt   50     11909.085 ±      51.534  ops/s
FilterBenchmark.complexFilterParallel         100000  thrpt   50      3260.864 ±     522.565  ops/s
FilterBenchmark.complexFilterParallel        1000000  thrpt   50       406.297 ±      96.590  ops/s
FilterBenchmark.multipleFilters                   10  thrpt   50   3785766.911 ±   27971.998  ops/s
FilterBenchmark.multipleFilters                  100  thrpt   50   1806210.041 ±   11578.529  ops/s
FilterBenchmark.multipleFilters                 1000  thrpt   50    211435.445 ±   28585.969  ops/s
FilterBenchmark.multipleFilters                10000  thrpt   50     12614.670 ±     370.086  ops/s
FilterBenchmark.multipleFilters               100000  thrpt   50      1228.127 ±      21.208  ops/s
FilterBenchmark.multipleFilters              1000000  thrpt   50        99.149 ±       1.370  ops/s
FilterBenchmark.multipleFiltersParallel           10  thrpt   50     23896.812 ±     255.117  ops/s
FilterBenchmark.multipleFiltersParallel          100  thrpt   50     25314.613 ±     169.724  ops/s
FilterBenchmark.multipleFiltersParallel         1000  thrpt   50     23113.388 ±     305.605  ops/s
FilterBenchmark.multipleFiltersParallel        10000  thrpt   50     12676.057 ±     119.555  ops/s
FilterBenchmark.multipleFiltersParallel       100000  thrpt   50      3373.367 ±     211.108  ops/s
FilterBenchmark.multipleFiltersParallel      1000000  thrpt   50       477.870 ±      70.878  ops/s
FilterBenchmark.oldFashionFilters                 10  thrpt   50  45874144.758 ± 2210325.177  ops/s
FilterBenchmark.oldFashionFilters                100  thrpt   50   4902625.828 ±   60397.844  ops/s
FilterBenchmark.oldFashionFilters               1000  thrpt   50    662102.438 ±    5038.465  ops/s
FilterBenchmark.oldFashionFilters              10000  thrpt   50     29390.911 ±     257.311  ops/s
FilterBenchmark.oldFashionFilters             100000  thrpt   50      1999.032 ±       6.829  ops/s
FilterBenchmark.oldFashionFilters            1000000  thrpt   50       200.564 ±       1.695  ops/s
```

### Java 12 

```commandline
docker run -it volkodav/java-filter-benchmark:java12
```

#### Params 

```
# OpenJDK Runtime Environment (build 12+33)
# OpenJDK 64-Bit Server VM (build 12+33, mixed mode, sharing)
# VM invoker: /usr/java/openjdk-12/bin/java
# VM options: -XX:+UseG1GC -server -Xmx1024m -Xms1024m
# Warmup: 10 iterations, 1 s each
# Measurement: 10 iterations, 1000 ms each
# Timeout: 10 min per iteration
# Threads: 1 thread, will synchronize iterations
# Benchmark mode: Throughput, ops/time
# Benchmark: com.sergeyvolkodav.FilterBenchmark.multipleFilters
# CPU: 8 cores
```

#### Raw Results

```
# Run complete. Total time: 04:36:20

Benchmark                                (arraySize)   Mode  Cnt         Score         Error  Units
FilterBenchmark.complexFilter                     10  thrpt   50  10338525.553 ? 1677693.433  ops/s
FilterBenchmark.complexFilter                    100  thrpt   50   4381301.188 ?  287299.598  ops/s
FilterBenchmark.complexFilter                   1000  thrpt   50    607572.430 ?    9367.026  ops/s
FilterBenchmark.complexFilter                  10000  thrpt   50     30643.286 ?     472.033  ops/s
FilterBenchmark.complexFilter                 100000  thrpt   50      1450.341 ?       3.730  ops/s
FilterBenchmark.complexFilter                1000000  thrpt   50       138.996 ?       2.052  ops/s
FilterBenchmark.complexFilterParallel             10  thrpt   50     21289.444 ?     183.245  ops/s
FilterBenchmark.complexFilterParallel            100  thrpt   50     20105.239 ?     124.759  ops/s
FilterBenchmark.complexFilterParallel           1000  thrpt   50     19418.830 ?     141.664  ops/s
FilterBenchmark.complexFilterParallel          10000  thrpt   50     13874.585 ?     104.418  ops/s
FilterBenchmark.complexFilterParallel         100000  thrpt   50      5334.947 ?      25.452  ops/s
FilterBenchmark.complexFilterParallel        1000000  thrpt   50       781.046 ?       9.687  ops/s
FilterBenchmark.multipleFilters                   10  thrpt   50   5460308.048 ?  478157.935  ops/s
FilterBenchmark.multipleFilters                  100  thrpt   50   2227583.836 ?  113078.932  ops/s
FilterBenchmark.multipleFilters                 1000  thrpt   50    287157.190 ?    1114.346  ops/s
FilterBenchmark.multipleFilters                10000  thrpt   50     16268.016 ?     704.735  ops/s
FilterBenchmark.multipleFilters               100000  thrpt   50      1531.516 ?       2.729  ops/s
FilterBenchmark.multipleFilters              1000000  thrpt   50       123.881 ?       1.525  ops/s
FilterBenchmark.multipleFiltersParallel           10  thrpt   50     20403.993 ?     147.247  ops/s
FilterBenchmark.multipleFiltersParallel          100  thrpt   50     19426.222 ?      96.979  ops/s
FilterBenchmark.multipleFiltersParallel         1000  thrpt   50     17692.433 ?      67.606  ops/s
FilterBenchmark.multipleFiltersParallel        10000  thrpt   50     12108.482 ?      34.500  ops/s
FilterBenchmark.multipleFiltersParallel       100000  thrpt   50      3782.756 ?      22.044  ops/s
FilterBenchmark.multipleFiltersParallel      1000000  thrpt   50       589.972 ?      71.448  ops/s
FilterBenchmark.oldFashionFilters                 10  thrpt   50  41024334.062 ? 1374663.440  ops/s
FilterBenchmark.oldFashionFilters                100  thrpt   50   6011852.027 ?  246202.642  ops/s
FilterBenchmark.oldFashionFilters               1000  thrpt   50    553243.594 ?    2217.912  ops/s
FilterBenchmark.oldFashionFilters              10000  thrpt   50     29188.753 ?     580.958  ops/s
FilterBenchmark.oldFashionFilters             100000  thrpt   50      2061.738 ?       8.456  ops/s
FilterBenchmark.oldFashionFilters            1000000  thrpt   50       196.105 ?       3.203  ops/s
```

## Graphs

### 10 Element Array

<img width="597" alt="10 Element Array" src="https://user-images.githubusercontent.com/4140597/55191371-e5471d80-5199-11e9-99bf-0bd4d9cb3160.png">


### 100 Element Array

<img width="592" alt="100 Element Array" src="https://user-images.githubusercontent.com/4140597/55191427-0871cd00-519a-11e9-8859-bc92cae9e4a9.png">


### 1,000 Element Array

<img width="594" alt="1,000 Element Array" src="https://user-images.githubusercontent.com/4140597/55191470-2f300380-519a-11e9-8b89-890229d6fd40.png">

### 10,000 Element Array

<img width="592" alt="10,000 Element Array" src="https://user-images.githubusercontent.com/4140597/55191531-58509400-519a-11e9-8b06-c6799646bf6b.png">

### 100,000 Element Array

<img width="597" alt="100,000 Element Array" src="https://user-images.githubusercontent.com/4140597/55191579-7918e980-519a-11e9-822d-934322543b81.png">

### 1,000,000 Element Array

<img width="594" alt="1,000,000 Element Array" src="https://user-images.githubusercontent.com/4140597/55191634-9948a880-519a-11e9-9761-6e1b7c2909f9.png">

## Summary 

On a relatively small array old fashion loop with one if clause shows the best performance results, 
but while an array is growing java parallel streams show better results. 
According to performance results, for a performance perspective, complex filter is better than multiple filters.

### Comparison tables

<img width="881" alt="Summary table" src="https://user-images.githubusercontent.com/4140597/55191273-a4e79f80-5199-11e9-8c39-0b1541481049.png">


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
