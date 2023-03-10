#!/bin/bash
echo "Starting test"

/home/arjun/OpenJ9/builds/jdk/bin/java -Djmh.ignoreLock=true -Xjit:count=1,disableAsyncCompilation,optlevel=hot -XX:ValueTypeFlatteningThreshold=999999999999 -XX:+EnableArrayFlattening -cp transaction-analyzer-5.2-Inlining-Depth.jar:lib/* in.ac.iitmandi.compl.Harness | tee runLogInliningDepth.log 2>&1

