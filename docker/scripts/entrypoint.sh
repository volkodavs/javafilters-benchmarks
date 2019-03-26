#!/bin/bash
set -e

java -jar /app/benchmarks.jar \
    -gc true \
    -jvmArgsAppend="-XX:+UseG1GC -server -Xmx1024m -Xms1024m"
