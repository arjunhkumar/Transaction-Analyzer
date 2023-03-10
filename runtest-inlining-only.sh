#!/bin/bash
echo "Starting test"

$SANITY_JDK/java -Djmh.ignoreLock=true -Xjit:count=1,disableAsyncCompilation,optlevel=hot -XX:ValueTypeFlatteningThreshold=999999999999 -XX:+EnableArrayFlattening -cp transaction-analyzer-5.2-Inlining-Depth.jar:lib/* in.ac.iitmandi.compl.Harness | tee runLogInliningDepth.log 2>&1

