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

<img width="881" alt="Summary table" src="https://user-images.githubusercontent.com/4140597/55191948-563b0500-519b-11e9-9564-ef3cd6cc1147.png">

## Bonus 

### Java 8 vs Java 11 

#### Params 

```
# openjdk version "11.0.2" 2019-01-15
# OpenJDK Runtime Environment (build 11.0.2+9-Debian-3bpo91)
# OpenJDK 64-Bit Server VM (build 11.0.2+9-Debian-3bpo91, mixed mode, sharing)
# VM invoker: /usr/lib/jvm/java-11-openjdk-amd64/bin/java
# VM options: -XX:+UseG1GC -server -Xmx1024m -Xms1024m
# Warmup: 10 iterations, 10 s each
# Measurement: 10 iterations, 1 s each
# Timeout: 10 min per iteration
# Threads: 1 thread, will synchronize iterations
# Benchmark mode: Throughput, ops/time
```

#### Comparison tables

<img width="881" alt="Summary table" src="https://user-images.githubusercontent.com/4140597/55243782-ce570880-5237-11e9-833e-ed79062f414d.png">


### Raw Results 
```
# Run complete. Total time: 05:01:30

Benchmark                                                                 (arraySize)   Mode  Cnt         Score         Error   Units
FilterBenchmark.complexFilter                                                      10  thrpt   50  12466361.755 ± 2030887.268   ops/s
FilterBenchmark.complexFilter:·gc.alloc.rate                                       10  thrpt   50      1676.932 ±     261.631  MB/sec
FilterBenchmark.complexFilter:·gc.alloc.rate.norm                                  10  thrpt   50       212.800 ±       3.200    B/op
FilterBenchmark.complexFilter:·gc.churn.G1_Eden_Space                              10  thrpt   50      1674.431 ±     271.704  MB/sec
FilterBenchmark.complexFilter:·gc.churn.G1_Eden_Space.norm                         10  thrpt   50       212.486 ±      13.449    B/op
FilterBenchmark.complexFilter:·gc.churn.G1_Old_Gen                                 10  thrpt   50         0.002 ±       0.001  MB/sec
FilterBenchmark.complexFilter:·gc.churn.G1_Old_Gen.norm                            10  thrpt   50        ≈ 10⁻⁴                  B/op
FilterBenchmark.complexFilter:·gc.count                                            10  thrpt   50       205.000                counts
FilterBenchmark.complexFilter:·gc.time                                             10  thrpt   50       479.000                    ms
FilterBenchmark.complexFilter                                                     100  thrpt   50   4063599.589 ±  516570.056   ops/s
FilterBenchmark.complexFilter:·gc.alloc.rate                                      100  thrpt   50       547.966 ±      67.818  MB/sec
FilterBenchmark.complexFilter:·gc.alloc.rate.norm                                 100  thrpt   50       212.801 ±       3.200    B/op
FilterBenchmark.complexFilter:·gc.churn.G1_Eden_Space                             100  thrpt   50       547.244 ±     105.012  MB/sec
FilterBenchmark.complexFilter:·gc.churn.G1_Eden_Space.norm                        100  thrpt   50       213.108 ±      29.999    B/op
FilterBenchmark.complexFilter:·gc.churn.G1_Old_Gen                                100  thrpt   50        ≈ 10⁻³                MB/sec
FilterBenchmark.complexFilter:·gc.churn.G1_Old_Gen.norm                           100  thrpt   50        ≈ 10⁻⁴                  B/op
FilterBenchmark.complexFilter:·gc.count                                           100  thrpt   50        67.000                counts
FilterBenchmark.complexFilter:·gc.time                                            100  thrpt   50       118.000                    ms
FilterBenchmark.complexFilter                                                    1000  thrpt   50    532272.455 ±   41340.119   ops/s
FilterBenchmark.complexFilter:·gc.alloc.rate                                     1000  thrpt   50        81.202 ±       6.305  MB/sec
FilterBenchmark.complexFilter:·gc.alloc.rate.norm                                1000  thrpt   50       240.079 ±       0.008    B/op
FilterBenchmark.complexFilter:·gc.churn.G1_Eden_Space                            1000  thrpt   50        89.813 ±      84.567  MB/sec
FilterBenchmark.complexFilter:·gc.churn.G1_Eden_Space.norm                       1000  thrpt   50       269.769 ±     259.466    B/op
FilterBenchmark.complexFilter:·gc.churn.G1_Survivor_Space                        1000  thrpt   50         0.027 ±       0.093  MB/sec
FilterBenchmark.complexFilter:·gc.churn.G1_Survivor_Space.norm                   1000  thrpt   50         0.110 ±       0.386    B/op
FilterBenchmark.complexFilter:·gc.count                                          1000  thrpt   50        11.000                counts
FilterBenchmark.complexFilter:·gc.time                                           1000  thrpt   50        17.000                    ms
FilterBenchmark.complexFilter                                                   10000  thrpt   50     16938.035 ±    1788.230   ops/s
FilterBenchmark.complexFilter:·gc.alloc.rate                                    10000  thrpt   50         2.843 ±       0.273  MB/sec
FilterBenchmark.complexFilter:·gc.alloc.rate.norm                               10000  thrpt   50       265.526 ±       3.045    B/op
FilterBenchmark.complexFilter:·gc.count                                         10000  thrpt   50           ≈ 0                counts
FilterBenchmark.complexFilter                                                  100000  thrpt   50      1379.043 ±       2.495   ops/s
FilterBenchmark.complexFilter:·gc.alloc.rate                                   100000  thrpt   50         2.543 ±       0.001  MB/sec
FilterBenchmark.complexFilter:·gc.alloc.rate.norm                              100000  thrpt   50      2909.022 ±       4.842    B/op
FilterBenchmark.complexFilter:·gc.count                                        100000  thrpt   50           ≈ 0                counts
FilterBenchmark.complexFilter                                                 1000000  thrpt   50       128.641 ±       2.489   ops/s
FilterBenchmark.complexFilter:·gc.alloc.rate                                  1000000  thrpt   50        23.335 ±       0.258  MB/sec
FilterBenchmark.complexFilter:·gc.alloc.rate.norm                             1000000  thrpt   50    299161.311 ±    6295.658    B/op
FilterBenchmark.complexFilter:·gc.churn.G1_Eden_Space                         1000000  thrpt   50        12.465 ±       8.184  MB/sec
FilterBenchmark.complexFilter:·gc.churn.G1_Eden_Space.norm                    1000000  thrpt   50    169922.118 ±  112188.585    B/op
FilterBenchmark.complexFilter:·gc.churn.G1_Survivor_Space                     1000000  thrpt   50         0.209 ±       0.317  MB/sec
FilterBenchmark.complexFilter:·gc.churn.G1_Survivor_Space.norm                1000000  thrpt   50      2787.940 ±    4227.418    B/op
FilterBenchmark.complexFilter:·gc.count                                       1000000  thrpt   50        25.000                counts
FilterBenchmark.complexFilter:·gc.time                                        1000000  thrpt   50      1433.000                    ms
FilterBenchmark.complexFilterParallel                                              10  thrpt   50     21711.018 ±     234.118   ops/s
FilterBenchmark.complexFilterParallel:·gc.alloc.rate                               10  thrpt   50        27.596 ±       0.298  MB/sec
FilterBenchmark.complexFilterParallel:·gc.alloc.rate.norm                          10  thrpt   50      2000.099 ±       0.075    B/op
FilterBenchmark.complexFilterParallel:·gc.churn.G1_Eden_Space                      10  thrpt   50        40.705 ±      61.065  MB/sec
FilterBenchmark.complexFilterParallel:·gc.churn.G1_Eden_Space.norm                 10  thrpt   50      2966.422 ±    4451.229    B/op
FilterBenchmark.complexFilterParallel:·gc.count                                    10  thrpt   50         5.000                counts
FilterBenchmark.complexFilterParallel:·gc.time                                     10  thrpt   50        17.000                    ms
FilterBenchmark.complexFilterParallel                                             100  thrpt   50     20458.673 ±      97.058   ops/s
FilterBenchmark.complexFilterParallel:·gc.alloc.rate                              100  thrpt   50        90.908 ±       0.433  MB/sec
FilterBenchmark.complexFilterParallel:·gc.alloc.rate.norm                         100  thrpt   50      6992.275 ±       0.079    B/op
FilterBenchmark.complexFilterParallel:·gc.churn.G1_Eden_Space                     100  thrpt   50        81.665 ±      81.676  MB/sec
FilterBenchmark.complexFilterParallel:·gc.churn.G1_Eden_Space.norm                100  thrpt   50      6308.781 ±    6310.018    B/op
FilterBenchmark.complexFilterParallel:·gc.count                                   100  thrpt   50        10.000                counts
FilterBenchmark.complexFilterParallel:·gc.time                                    100  thrpt   50        13.000                    ms
FilterBenchmark.complexFilterParallel                                            1000  thrpt   50     19317.632 ±     341.610   ops/s
FilterBenchmark.complexFilterParallel:·gc.alloc.rate                             1000  thrpt   50        76.695 ±       1.351  MB/sec
FilterBenchmark.complexFilterParallel:·gc.alloc.rate.norm                        1000  thrpt   50      6247.717 ±       3.616    B/op
FilterBenchmark.complexFilterParallel:·gc.churn.G1_Eden_Space                    1000  thrpt   50        81.661 ±      81.671  MB/sec
FilterBenchmark.complexFilterParallel:·gc.churn.G1_Eden_Space.norm               1000  thrpt   50      6603.066 ±    6608.396    B/op
FilterBenchmark.complexFilterParallel:·gc.churn.G1_Old_Gen                       1000  thrpt   50        ≈ 10⁻⁵                MB/sec
FilterBenchmark.complexFilterParallel:·gc.churn.G1_Old_Gen.norm                  1000  thrpt   50         0.002 ±       0.005    B/op
FilterBenchmark.complexFilterParallel:·gc.count                                  1000  thrpt   50        10.000                counts
FilterBenchmark.complexFilterParallel:·gc.time                                   1000  thrpt   50        16.000                    ms
FilterBenchmark.complexFilterParallel                                           10000  thrpt   50     13878.605 ±      85.435   ops/s
FilterBenchmark.complexFilterParallel:·gc.alloc.rate                            10000  thrpt   50        55.351 ±       0.337  MB/sec
FilterBenchmark.complexFilterParallel:·gc.alloc.rate.norm                       10000  thrpt   50      6277.596 ±       0.220    B/op
FilterBenchmark.complexFilterParallel:·gc.churn.G1_Eden_Space                   10000  thrpt   50        40.691 ±      61.045  MB/sec
FilterBenchmark.complexFilterParallel:·gc.churn.G1_Eden_Space.norm              10000  thrpt   50      4626.341 ±    6941.242    B/op
FilterBenchmark.complexFilterParallel:·gc.count                                 10000  thrpt   50         5.000                counts
FilterBenchmark.complexFilterParallel:·gc.time                                  10000  thrpt   50        19.000                    ms
FilterBenchmark.complexFilterParallel                                          100000  thrpt   50      5347.627 ±      26.503   ops/s
FilterBenchmark.complexFilterParallel:·gc.alloc.rate                           100000  thrpt   50        23.516 ±       0.106  MB/sec
FilterBenchmark.complexFilterParallel:·gc.alloc.rate.norm                      100000  thrpt   50      6936.524 ±       3.505    B/op
FilterBenchmark.complexFilterParallel:·gc.churn.G1_Eden_Space                  100000  thrpt   50        32.295 ±      54.766  MB/sec
FilterBenchmark.complexFilterParallel:·gc.churn.G1_Eden_Space.norm             100000  thrpt   50      9614.029 ±   16303.557    B/op
FilterBenchmark.complexFilterParallel:·gc.churn.G1_Survivor_Space              100000  thrpt   50         0.013 ±       0.047  MB/sec
FilterBenchmark.complexFilterParallel:·gc.churn.G1_Survivor_Space.norm         100000  thrpt   50         3.959 ±      13.859    B/op
FilterBenchmark.complexFilterParallel:·gc.count                                100000  thrpt   50         4.000                counts
FilterBenchmark.complexFilterParallel:·gc.time                                 100000  thrpt   50        20.000                    ms
FilterBenchmark.complexFilterParallel                                         1000000  thrpt   50       700.078 ±      24.806   ops/s
FilterBenchmark.complexFilterParallel:·gc.alloc.rate                          1000000  thrpt   50        26.183 ±       0.335  MB/sec
FilterBenchmark.complexFilterParallel:·gc.alloc.rate.norm                     1000000  thrpt   50     61587.928 ±    1962.624    B/op
FilterBenchmark.complexFilterParallel:·gc.churn.G1_Eden_Space                 1000000  thrpt   50        17.698 ±      15.006  MB/sec
FilterBenchmark.complexFilterParallel:·gc.churn.G1_Eden_Space.norm            1000000  thrpt   50     45276.106 ±   38670.516    B/op
FilterBenchmark.complexFilterParallel:·gc.churn.G1_Survivor_Space             1000000  thrpt   50         0.831 ±       1.131  MB/sec
FilterBenchmark.complexFilterParallel:·gc.churn.G1_Survivor_Space.norm        1000000  thrpt   50      1991.526 ±    2710.666    B/op
FilterBenchmark.complexFilterParallel:·gc.count                               1000000  thrpt   50        18.000                counts
FilterBenchmark.complexFilterParallel:·gc.time                                1000000  thrpt   50      1495.000                    ms
FilterBenchmark.multipleFilters                                                    10  thrpt   50   5357130.745 ±  708583.946   ops/s
FilterBenchmark.multipleFilters:·gc.alloc.rate                                     10  thrpt   50      1594.550 ±     200.010  MB/sec
FilterBenchmark.multipleFilters:·gc.alloc.rate.norm                                10  thrpt   50       470.400 ±       3.920    B/op
FilterBenchmark.multipleFilters:·gc.churn.G1_Eden_Space                            10  thrpt   50      1600.917 ±     219.347  MB/sec
FilterBenchmark.multipleFilters:·gc.churn.G1_Eden_Space.norm                       10  thrpt   50       472.554 ±      29.928    B/op
FilterBenchmark.multipleFilters:·gc.churn.G1_Old_Gen                               10  thrpt   50         0.002 ±       0.001  MB/sec
FilterBenchmark.multipleFilters:·gc.churn.G1_Old_Gen.norm                          10  thrpt   50        ≈ 10⁻³                  B/op
FilterBenchmark.multipleFilters:·gc.count                                          10  thrpt   50       196.000                counts
FilterBenchmark.multipleFilters:·gc.time                                           10  thrpt   50       368.000                    ms
FilterBenchmark.multipleFilters                                                   100  thrpt   50   1892344.050 ±  254389.853   ops/s
FilterBenchmark.multipleFilters:·gc.alloc.rate                                    100  thrpt   50       569.503 ±      77.631  MB/sec
FilterBenchmark.multipleFilters:·gc.alloc.rate.norm                               100  thrpt   50       473.603 ±       3.920    B/op
FilterBenchmark.multipleFilters:·gc.churn.G1_Eden_Space                           100  thrpt   50       571.740 ±     122.528  MB/sec
FilterBenchmark.multipleFilters:·gc.churn.G1_Eden_Space.norm                      100  thrpt   50       471.991 ±      91.824    B/op
FilterBenchmark.multipleFilters:·gc.churn.G1_Old_Gen                              100  thrpt   50         0.001 ±       0.001  MB/sec
FilterBenchmark.multipleFilters:·gc.churn.G1_Old_Gen.norm                         100  thrpt   50        ≈ 10⁻³                  B/op
FilterBenchmark.multipleFilters:·gc.count                                         100  thrpt   50        70.000                counts
FilterBenchmark.multipleFilters:·gc.time                                          100  thrpt   50       122.000                    ms
FilterBenchmark.multipleFilters                                                  1000  thrpt   50    163634.561 ±   20582.764   ops/s
FilterBenchmark.multipleFilters:·gc.alloc.rate                                   1000  thrpt   50        49.938 ±       6.278  MB/sec
FilterBenchmark.multipleFilters:·gc.alloc.rate.norm                              1000  thrpt   50       480.267 ±       0.036    B/op
FilterBenchmark.multipleFilters:·gc.churn.G1_Eden_Space                          1000  thrpt   50        57.091 ±      70.758  MB/sec
FilterBenchmark.multipleFilters:·gc.churn.G1_Eden_Space.norm                     1000  thrpt   50       546.789 ±     706.165    B/op
FilterBenchmark.multipleFilters:·gc.churn.G1_Old_Gen                             1000  thrpt   50        ≈ 10⁻⁴                MB/sec
FilterBenchmark.multipleFilters:·gc.churn.G1_Old_Gen.norm                        1000  thrpt   50         0.002 ±       0.008    B/op
FilterBenchmark.multipleFilters:·gc.count                                        1000  thrpt   50         7.000                counts
FilterBenchmark.multipleFilters:·gc.time                                         1000  thrpt   50        16.000                    ms
FilterBenchmark.multipleFilters                                                 10000  thrpt   50     10339.167 ±    1052.948   ops/s
FilterBenchmark.multipleFilters:·gc.alloc.rate                                  10000  thrpt   50         3.384 ±       0.292  MB/sec
FilterBenchmark.multipleFilters:·gc.alloc.rate.norm                             10000  thrpt   50       517.826 ±       6.407    B/op
FilterBenchmark.multipleFilters:·gc.count                                       10000  thrpt   50           ≈ 0                counts
FilterBenchmark.multipleFilters                                                100000  thrpt   50       919.656 ±       2.521   ops/s
FilterBenchmark.multipleFilters:·gc.alloc.rate                                 100000  thrpt   50         2.612 ±       0.001  MB/sec
FilterBenchmark.multipleFilters:·gc.alloc.rate.norm                            100000  thrpt   50      4481.503 ±      11.367    B/op
FilterBenchmark.multipleFilters:·gc.count                                      100000  thrpt   50           ≈ 0                counts
FilterBenchmark.multipleFilters                                               1000000  thrpt   50        88.546 ±       1.102   ops/s
FilterBenchmark.multipleFilters:·gc.alloc.rate                                1000000  thrpt   50        23.294 ±       0.273  MB/sec
FilterBenchmark.multipleFilters:·gc.alloc.rate.norm                           1000000  thrpt   50    434715.070 ±    5591.225    B/op
FilterBenchmark.multipleFilters:·gc.churn.G1_Eden_Space                       1000000  thrpt   50        13.432 ±       8.456  MB/sec
FilterBenchmark.multipleFilters:·gc.churn.G1_Eden_Space.norm                  1000000  thrpt   50    262019.285 ±  165765.627    B/op
FilterBenchmark.multipleFilters:·gc.churn.G1_Survivor_Space                   1000000  thrpt   50         0.172 ±       0.302  MB/sec
FilterBenchmark.multipleFilters:·gc.churn.G1_Survivor_Space.norm              1000000  thrpt   50      3325.667 ±    5833.705    B/op
FilterBenchmark.multipleFilters:·gc.count                                     1000000  thrpt   50        25.000                counts
FilterBenchmark.multipleFilters:·gc.time                                      1000000  thrpt   50      1462.000                    ms
FilterBenchmark.multipleFiltersParallel                                            10  thrpt   50     20937.463 ±     311.480   ops/s
FilterBenchmark.multipleFiltersParallel:·gc.alloc.rate                             10  thrpt   50        38.747 ±       0.576  MB/sec
FilterBenchmark.multipleFiltersParallel:·gc.alloc.rate.norm                        10  thrpt   50      2912.103 ±       0.079    B/op
FilterBenchmark.multipleFiltersParallel:·gc.churn.G1_Eden_Space                    10  thrpt   50        40.705 ±      61.065  MB/sec
FilterBenchmark.multipleFiltersParallel:·gc.churn.G1_Eden_Space.norm               10  thrpt   50      3068.570 ±    4605.469    B/op
FilterBenchmark.multipleFiltersParallel:·gc.count                                  10  thrpt   50         5.000                counts
FilterBenchmark.multipleFiltersParallel:·gc.time                                   10  thrpt   50        17.000                    ms
FilterBenchmark.multipleFiltersParallel                                           100  thrpt   50     19801.562 ±     109.412   ops/s
FilterBenchmark.multipleFiltersParallel:·gc.alloc.rate                            100  thrpt   50       123.023 ±       0.680  MB/sec
FilterBenchmark.multipleFiltersParallel:·gc.alloc.rate.norm                       100  thrpt   50      9776.285 ±       0.082    B/op
FilterBenchmark.multipleFiltersParallel:·gc.churn.G1_Eden_Space                   100  thrpt   50       122.513 ±      93.583  MB/sec
FilterBenchmark.multipleFiltersParallel:·gc.churn.G1_Eden_Space.norm              100  thrpt   50      9784.874 ±    7474.610    B/op
FilterBenchmark.multipleFiltersParallel:·gc.count                                 100  thrpt   50        15.000                counts
FilterBenchmark.multipleFiltersParallel:·gc.time                                  100  thrpt   50        20.000                    ms
FilterBenchmark.multipleFiltersParallel                                          1000  thrpt   50     17768.898 ±      95.230   ops/s
FilterBenchmark.multipleFiltersParallel:·gc.alloc.rate                           1000  thrpt   50        98.486 ±       0.528  MB/sec
FilterBenchmark.multipleFiltersParallel:·gc.alloc.rate.norm                      1000  thrpt   50      8722.299 ±       0.093    B/op
FilterBenchmark.multipleFiltersParallel:·gc.churn.G1_Eden_Space                  1000  thrpt   50        89.839 ±      84.592  MB/sec
FilterBenchmark.multipleFiltersParallel:·gc.churn.G1_Eden_Space.norm             1000  thrpt   50      7977.133 ±    7512.054    B/op
FilterBenchmark.multipleFiltersParallel:·gc.count                                1000  thrpt   50        11.000                counts
FilterBenchmark.multipleFiltersParallel:·gc.time                                 1000  thrpt   50        16.000                    ms
FilterBenchmark.multipleFiltersParallel                                         10000  thrpt   50     12450.718 ±     276.309   ops/s
FilterBenchmark.multipleFiltersParallel:·gc.alloc.rate                          10000  thrpt   50        69.241 ±       1.532  MB/sec
FilterBenchmark.multipleFiltersParallel:·gc.alloc.rate.norm                     10000  thrpt   50      8753.047 ±       0.729    B/op
FilterBenchmark.multipleFiltersParallel:·gc.churn.G1_Eden_Space                 10000  thrpt   50        57.159 ±      70.843  MB/sec
FilterBenchmark.multipleFiltersParallel:·gc.churn.G1_Eden_Space.norm            10000  thrpt   50      7278.806 ±    9031.549    B/op
FilterBenchmark.multipleFiltersParallel:·gc.churn.G1_Old_Gen                    10000  thrpt   50        ≈ 10⁻⁴                MB/sec
FilterBenchmark.multipleFiltersParallel:·gc.churn.G1_Old_Gen.norm               10000  thrpt   50         0.025 ±       0.087    B/op
FilterBenchmark.multipleFiltersParallel:·gc.count                               10000  thrpt   50         7.000                counts
FilterBenchmark.multipleFiltersParallel:·gc.time                                10000  thrpt   50        12.000                    ms
FilterBenchmark.multipleFiltersParallel                                        100000  thrpt   50      3781.631 ±     415.889   ops/s
FilterBenchmark.multipleFiltersParallel:·gc.alloc.rate                         100000  thrpt   50        23.239 ±       2.299  MB/sec
FilterBenchmark.multipleFiltersParallel:·gc.alloc.rate.norm                    100000  thrpt   50      9731.839 ±      87.625    B/op
FilterBenchmark.multipleFiltersParallel:·gc.churn.G1_Eden_Space                100000  thrpt   50        32.299 ±      54.773  MB/sec
FilterBenchmark.multipleFiltersParallel:·gc.churn.G1_Eden_Space.norm           100000  thrpt   50     14358.034 ±   24824.112    B/op
FilterBenchmark.multipleFiltersParallel:·gc.churn.G1_Survivor_Space            100000  thrpt   50         0.013 ±       0.047  MB/sec
FilterBenchmark.multipleFiltersParallel:·gc.churn.G1_Survivor_Space.norm       100000  thrpt   50         6.572 ±      23.005    B/op
FilterBenchmark.multipleFiltersParallel:·gc.count                              100000  thrpt   50         4.000                counts
FilterBenchmark.multipleFiltersParallel:·gc.time                               100000  thrpt   50        20.000                    ms
FilterBenchmark.multipleFiltersParallel                                       1000000  thrpt   50       488.838 ±      68.437   ops/s
FilterBenchmark.multipleFiltersParallel:·gc.alloc.rate                        1000000  thrpt   50        26.152 ±       0.416  MB/sec
FilterBenchmark.multipleFiltersParallel:·gc.alloc.rate.norm                   1000000  thrpt   50     92652.338 ±    9400.643    B/op
FilterBenchmark.multipleFiltersParallel:·gc.churn.G1_Eden_Space               1000000  thrpt   50        18.108 ±      15.223  MB/sec
FilterBenchmark.multipleFiltersParallel:·gc.churn.G1_Eden_Space.norm          1000000  thrpt   50     65525.131 ±   56079.578    B/op
FilterBenchmark.multipleFiltersParallel:·gc.churn.G1_Survivor_Space           1000000  thrpt   50         0.859 ±       1.070  MB/sec
FilterBenchmark.multipleFiltersParallel:·gc.churn.G1_Survivor_Space.norm      1000000  thrpt   50      3093.630 ±    3997.049    B/op
FilterBenchmark.multipleFiltersParallel:·gc.count                             1000000  thrpt   50        18.000                counts
FilterBenchmark.multipleFiltersParallel:·gc.time                              1000000  thrpt   50      1443.000                    ms
FilterBenchmark.oldFashionFilters                                                  10  thrpt   50  46261391.527 ± 1745620.107   ops/s
FilterBenchmark.oldFashionFilters:·gc.alloc.rate                                   10  thrpt   50         0.001 ±       0.001  MB/sec
FilterBenchmark.oldFashionFilters:·gc.alloc.rate.norm                              10  thrpt   50        ≈ 10⁻⁴                  B/op
FilterBenchmark.oldFashionFilters:·gc.count                                        10  thrpt   50           ≈ 0                counts
FilterBenchmark.oldFashionFilters                                                 100  thrpt   50   6247881.806 ±  153023.348   ops/s
FilterBenchmark.oldFashionFilters:·gc.alloc.rate                                  100  thrpt   50         0.004 ±       0.001  MB/sec
FilterBenchmark.oldFashionFilters:·gc.alloc.rate.norm                             100  thrpt   50         0.001 ±       0.001    B/op
FilterBenchmark.oldFashionFilters:·gc.count                                       100  thrpt   50           ≈ 0                counts
FilterBenchmark.oldFashionFilters                                                1000  thrpt   50    498523.478 ±    2361.365   ops/s
FilterBenchmark.oldFashionFilters:·gc.alloc.rate                                 1000  thrpt   50         0.026 ±       0.001  MB/sec
FilterBenchmark.oldFashionFilters:·gc.alloc.rate.norm                            1000  thrpt   50         0.082 ±       0.003    B/op
FilterBenchmark.oldFashionFilters:·gc.count                                      1000  thrpt   50           ≈ 0                counts
FilterBenchmark.oldFashionFilters                                               10000  thrpt   50     27134.481 ±     461.569   ops/s
FilterBenchmark.oldFashionFilters:·gc.alloc.rate                                10000  thrpt   50         0.261 ±       0.001  MB/sec
FilterBenchmark.oldFashionFilters:·gc.alloc.rate.norm                           10000  thrpt   50        15.152 ±       0.266    B/op
FilterBenchmark.oldFashionFilters:·gc.count                                     10000  thrpt   50           ≈ 0                counts
FilterBenchmark.oldFashionFilters                                              100000  thrpt   50      1958.948 ±      20.090   ops/s
FilterBenchmark.oldFashionFilters:·gc.alloc.rate                               100000  thrpt   50         2.332 ±       0.002  MB/sec
FilterBenchmark.oldFashionFilters:·gc.alloc.rate.norm                          100000  thrpt   50      1879.812 ±      20.180    B/op
FilterBenchmark.oldFashionFilters:·gc.churn.G1_Eden_Space                      100000  thrpt   50         3.368 ±       5.053  MB/sec
FilterBenchmark.oldFashionFilters:·gc.churn.G1_Eden_Space.norm                 100000  thrpt   50      2904.252 ±    4356.998    B/op
FilterBenchmark.oldFashionFilters:·gc.count                                    100000  thrpt   50         5.000                counts
FilterBenchmark.oldFashionFilters:·gc.time                                     100000  thrpt   50        35.000                    ms
FilterBenchmark.oldFashionFilters                                             1000000  thrpt   50       191.030 ±       2.196   ops/s
FilterBenchmark.oldFashionFilters:·gc.alloc.rate                              1000000  thrpt   50        23.420 ±       0.246  MB/sec
FilterBenchmark.oldFashionFilters:·gc.alloc.rate.norm                         1000000  thrpt   50    201533.144 ±    2422.021    B/op
FilterBenchmark.oldFashionFilters:·gc.churn.G1_Eden_Space                     1000000  thrpt   50         9.133 ±       7.493  MB/sec
FilterBenchmark.oldFashionFilters:·gc.churn.G1_Eden_Space.norm                1000000  thrpt   50     83325.948 ±   68154.777    B/op
FilterBenchmark.oldFashionFilters:·gc.count                                   1000000  thrpt   50        20.000                counts
FilterBenchmark.oldFashionFilters:·gc.time                                    1000000  thrpt   50      1089.000                    ms
```

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
