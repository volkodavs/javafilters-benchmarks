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

#### Results

```
Benchmark                                (arraySize)   Mode  Cnt        Score       Error  Units
FilterBenchmark.multipleFilters                   10  thrpt  100  1364546.517 ±  3579.906  ops/s
FilterBenchmark.multipleFilters                  100  thrpt  100   687438.224 ±  5884.973  ops/s
FilterBenchmark.multipleFilters                 1000  thrpt  100   110489.928 ±   800.637  ops/s
FilterBenchmark.multipleFilters                10000  thrpt  100    12290.655 ±   104.239  ops/s
FilterBenchmark.multipleFilters               100000  thrpt  100     1282.548 ±    36.517  ops/s
FilterBenchmark.multipleFilters              1000000  thrpt  100      136.211 ±     2.938  ops/s
FilterBenchmark.multipleFiltersParallel           10  thrpt  100    22482.268 ±   167.280  ops/s
FilterBenchmark.multipleFiltersParallel          100  thrpt  100    23932.211 ±   105.224  ops/s
FilterBenchmark.multipleFiltersParallel         1000  thrpt  100    20546.812 ±   207.322  ops/s
FilterBenchmark.multipleFiltersParallel        10000  thrpt  100    12826.451 ±   105.562  ops/s
FilterBenchmark.multipleFiltersParallel       100000  thrpt  100     3965.370 ±    58.927  ops/s
FilterBenchmark.multipleFiltersParallel      1000000  thrpt  100      344.808 ±    34.809  ops/s
FilterBenchmark.oldFashionFilters                 10  thrpt  100  2241545.032 ±  5936.237  ops/s
FilterBenchmark.oldFashionFilters                100  thrpt  100  1067280.430 ±  2898.776  ops/s
FilterBenchmark.oldFashionFilters               1000  thrpt  100   171715.214 ±   543.459  ops/s
FilterBenchmark.oldFashionFilters              10000  thrpt  100    19263.648 ±    26.834  ops/s
FilterBenchmark.oldFashionFilters             100000  thrpt  100     2038.695 ±     6.303  ops/s
FilterBenchmark.oldFashionFilters            1000000  thrpt  100      172.616 ±     2.984  ops/s
FilterBenchmark.oneFilter                         10  thrpt  100  1620558.903 ± 14856.116  ops/s
FilterBenchmark.oneFilter                        100  thrpt  100   667080.168 ± 23007.882  ops/s
FilterBenchmark.oneFilter                       1000  thrpt  100   118228.039 ±  6173.876  ops/s
FilterBenchmark.oneFilter                      10000  thrpt  100    14281.845 ±    18.418  ops/s
FilterBenchmark.oneFilter                     100000  thrpt  100     1468.491 ±     2.975  ops/s
FilterBenchmark.oneFilter                    1000000  thrpt  100      130.511 ±     2.109  ops/s
FilterBenchmark.oneFilterParallel                 10  thrpt  100    23464.490 ±   200.224  ops/s
FilterBenchmark.oneFilterParallel                100  thrpt  100    23596.068 ±   222.463  ops/s
FilterBenchmark.oneFilterParallel               1000  thrpt  100    20413.603 ±   290.502  ops/s
FilterBenchmark.oneFilterParallel              10000  thrpt  100    12082.934 ±   344.165  ops/s
FilterBenchmark.oneFilterParallel             100000  thrpt  100     4171.593 ±   357.832  ops/s
FilterBenchmark.oneFilterParallel            1000000  thrpt  100      397.795 ±    47.321  ops/s
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

#### Results

```
Benchmark                                (arraySize)   Mode  Cnt        Score       Error  Units
FilterBenchmark.multipleFilters                   10  thrpt   50  1191638.476 ? 17681.179  ops/s
FilterBenchmark.multipleFilters                  100  thrpt   50   543876.880 ?  8671.057  ops/s
FilterBenchmark.multipleFilters                 1000  thrpt   50    83373.960 ?  2184.504  ops/s
FilterBenchmark.multipleFilters                10000  thrpt   50     9017.336 ?    60.694  ops/s
FilterBenchmark.multipleFilters               100000  thrpt   50      895.696 ?    30.036  ops/s
FilterBenchmark.multipleFilters              1000000  thrpt   50       75.430 ?     2.536  ops/s
FilterBenchmark.multipleFiltersParallel           10  thrpt   50    25249.068 ?   480.633  ops/s
FilterBenchmark.multipleFiltersParallel          100  thrpt   50    18345.598 ?   239.063  ops/s
FilterBenchmark.multipleFiltersParallel         1000  thrpt   50    16118.267 ?   203.401  ops/s
FilterBenchmark.multipleFiltersParallel        10000  thrpt   50    10220.834 ?   225.201  ops/s
FilterBenchmark.multipleFiltersParallel       100000  thrpt   50     2197.861 ?   271.955  ops/s
FilterBenchmark.multipleFiltersParallel      1000000  thrpt   50      164.429 ?    31.597  ops/s
FilterBenchmark.oldFashionFilters                 10  thrpt   50  1970841.768 ? 51513.878  ops/s
FilterBenchmark.oldFashionFilters                100  thrpt   50  1027088.435 ? 12909.005  ops/s
FilterBenchmark.oldFashionFilters               1000  thrpt   50   182719.991 ?   509.940  ops/s
FilterBenchmark.oldFashionFilters              10000  thrpt   50    19592.779 ?    41.382  ops/s
FilterBenchmark.oldFashionFilters             100000  thrpt   50     1970.837 ?    45.844  ops/s
FilterBenchmark.oldFashionFilters            1000000  thrpt   50      158.571 ?     7.507  ops/s
FilterBenchmark.oneFilter                         10  thrpt   50  1437753.617 ? 21550.203  ops/s
FilterBenchmark.oneFilter                        100  thrpt   50   732987.926 ?  8312.437  ops/s
FilterBenchmark.oneFilter                       1000  thrpt   50   113708.602 ?   482.573  ops/s
FilterBenchmark.oneFilter                      10000  thrpt   50    13218.784 ?    19.637  ops/s
FilterBenchmark.oneFilter                     100000  thrpt   50     1316.214 ?    27.559  ops/s
FilterBenchmark.oneFilter                    1000000  thrpt   50      111.053 ?     4.475  ops/s
FilterBenchmark.oneFilterParallel                 10  thrpt   50    26917.613 ?   925.582  ops/s
FilterBenchmark.oneFilterParallel                100  thrpt   50    20038.156 ?   379.706  ops/s
FilterBenchmark.oneFilterParallel               1000  thrpt   50    17164.433 ?   202.813  ops/s
FilterBenchmark.oneFilterParallel              10000  thrpt   50    12041.588 ?   117.785  ops/s
FilterBenchmark.oneFilterParallel             100000  thrpt   50     3453.958 ?   427.210  ops/s
FilterBenchmark.oneFilterParallel            1000000  thrpt   50      278.163 ?    74.132  ops/s
```

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
