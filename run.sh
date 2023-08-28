#!/bin/bash
echo "Starting Expt"
rm -r out

for k in 1000 5000 10000 20000
do
    echo "-----------------------------------------------------------------------" >> out/out.log
    for i in 1 2 3 4 5 6 7 8 9 10 11 12 13 14 15 16 17 18 19 20 21 22 23 24 25 26 27 28 29 30
    do
        $SANITY_JDK/java -Xverify:none -XX:-EnableHCR -XX:ValueTypeFlatteningThreshold=100000 -XX:+EnableArrayFlattening -Xjit:count=1,disableAsyncCompilation,optlevel=hot -cp transaction-analyzer-PRIMITIVE-1.0.jar:lib/* in.ac.iitmandi.compl.ValueMain $k >> primitiveInstance$k.log 2>&1
    done

    echo "-----------------------------------------------------------------------" >> out/out.log
    echo "Completed Usecase - Primitive $k"
    echo "Completed Usecase - Primitive $k" >> out/out.log

    echo "-----------------------------------------------------------------------" >> out/out.log
    for i in 1 2 3 4 5 6 7 8 9 10 11 12 13 14 15 16 17 18 19 20 21 22 23 24 25 26 27 28 29 30
    do
        $SANITY_JDK/java -Xverify:none -XX:-EnableHCR -XX:+EnableArrayFlattening -Xjit:count=1,disableAsyncCompilation,optlevel=hot -cp transaction-analyzer-PRIMITIVE-1.0.jar:lib/* in.ac.iitmandi.compl.ValueMain $k >> nonprimitiveInstance$k.log 2>&1
    done
    echo "-----------------------------------------------------------------------" >> out/out.log
    echo "Completed Usecase - Non-Primitive $k"
    echo "Completed Usecase - Non-Primitive $k" >> out/out.log

echo "Finished Transaction-Analysis-$k"
echo "Finished Transaction-Analysis-$k" >> out/out.log
done
