#!/bin/bash
set -e

java -jar /app/benchmarks.jar \
    -jvmArgsAppend="-XX:+UseG1GC -server -Xmx1024m -Xms1024m" \
    ${JMH_PARAMS}
