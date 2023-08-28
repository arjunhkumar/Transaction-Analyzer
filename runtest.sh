#!/bin/bash
echo "Starting test"
for k in 1000 5000 10000 20000
do
    perf stat -r 10 -e cycles,instructions,cache-misses,cache-references -d -d -d $SANITY_JDK/java -Xverify:none -XX:-EnableHCR -XX:ValueTypeFlatteningThreshold=100000 -XX:+EnableArrayFlattening -Xjit:count=1,disableAsyncCompilation,optlevel=hot -cp transaction-analyzer-PRIMITIVE-1.0.jar:lib/* in.ac.iitmandi.compl.ValueMain $k > primitiveInstancePerf$k.log 2>&1

    echo "Finished VT $k"


    perf stat -r 10 -e cycles,instructions,cache-misses,cache-references -d -d -d $SANITY_JDK/java -Xverify:none -XX:-EnableHCR -XX:+EnableArrayFlattening -Xjit:count=1,disableAsyncCompilation,optlevel=hot -cp transaction-analyzer-PRIMITIVE-1.0.jar:lib/* in.ac.iitmandi.compl.ValueMain $k > nonprimitiveInstancePerf$k.log 2>&1

    echo "Finished NVT $k"
echo "Finished Transaction-Analysis-$k"
done


