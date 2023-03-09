#!/bin/bash
echo "Starting test"

perf stat -r 10 -e cycles,instructions,cache-misses,cache-references -d -d -d $SANITY_JDK/java -Xverify:none -Xjit:count=1,disableAsyncCompilation,optlevel=hot -cp transaction-analyzer-2.1.jar:lib/* in.ac.iitmandi.compl.NonValueMain 100 > nonValueFieldContainerTest100.log 2>&1

echo "Finished NonValueFieldContainerTest 100"


perf stat -r 10 -e cycles,instructions,cache-misses,cache-references -d -d -d $SANITY_JDK/java -Xverify:none -XX:ValueTypeFlatteningThreshold=99999 -Xjit:count=1,disableAsyncCompilation,optlevel=hot -cp transaction-analyzer-2.1.jar:lib/* in.ac.iitmandi.compl.IntermediateValueMain 100 > iValueFieldContainerTest100.log 2>&1

echo "Finished IValueFieldContainerTest 100"


perf stat -r 10 -e cycles,instructions,cache-misses,cache-references -d -d -d $SANITY_JDK/java -Xverify:none -XX:ValueTypeFlatteningThreshold=99999 -Xjit:count=1,disableAsyncCompilation,optlevel=hot -cp transaction-analyzer-2.1.jar:lib/* in.ac.iitmandi.compl.ValueMainLarge 100 > ValueFieldContainerTestLarge100.log 2>&1

echo "Finished ValueFieldContainerTestLarge 100"

perf stat -r 10 -e cycles,instructions,cache-misses,cache-references -d -d -d $SANITY_JDK/java -Xverify:none -XX:ValueTypeFlatteningThreshold=99999 -Xjit:count=1,disableAsyncCompilation,optlevel=hot -cp transaction-analyzer-2.1.jar:lib/* in.ac.iitmandi.compl.ValueMainMeduim 100 > valueFieldContainerTestMeduim100.log 2>&1

echo "Finished ValueFieldContainerTestMeduim 100"

perf stat -r 10 -e cycles,instructions,cache-misses,cache-references -d -d -d $SANITY_JDK/java -Xverify:none -XX:ValueTypeFlatteningThreshold=99999 -Xjit:count=1,disableAsyncCompilation,optlevel=hot -cp transaction-analyzer-2.1.jar:lib/* in.ac.iitmandi.compl.ValueMain 100 > valueFieldContainerTest100.log 2>&1

echo "Finished ValueFieldContainerTest 100"


perf stat -r 10 -e cycles,instructions,cache-misses,cache-references -d -d -d $SANITY_JDK/java -Xverify:none -Xjit:count=1,disableAsyncCompilation,optlevel=hot -cp transaction-analyzer-2.1.jar:lib/* in.ac.iitmandi.compl.NonValueMain 1000 > nonValueFieldContainerTest1000.log 2>&1

echo "Finished NonValueFieldContainerTest 1000"


perf stat -r 10 -e cycles,instructions,cache-misses,cache-references -d -d -d $SANITY_JDK/java -Xverify:none -XX:ValueTypeFlatteningThreshold=99999 -Xjit:count=1,disableAsyncCompilation,optlevel=hot -cp transaction-analyzer-2.1.jar:lib/* in.ac.iitmandi.compl.IntermediateValueMain 1000 > iValueFieldContainerTest1000.log 2>&1

echo "Finished IValueFieldContainerTest 1000"


perf stat -r 10 -e cycles,instructions,cache-misses,cache-references -d -d -d $SANITY_JDK/java -Xverify:none -XX:ValueTypeFlatteningThreshold=99999 -Xjit:count=1,disableAsyncCompilation,optlevel=hot -cp transaction-analyzer-2.1.jar:lib/* in.ac.iitmandi.compl.ValueMain 1000 > valueFieldContainerTest1000.log 2>&1

echo "Finished ValueFieldContainerTest 1000"

perf stat -r 10 -e cycles,instructions,cache-misses,cache-references -d -d -d $SANITY_JDK/java -Xverify:none -XX:ValueTypeFlatteningThreshold=99999 -Xjit:count=1,disableAsyncCompilation,optlevel=hot -cp transaction-analyzer-2.1.jar:lib/* in.ac.iitmandi.compl.ValueMainLarge 1000 > ValueFieldContainerTestLarge1000.log 2>&1

echo "Finished ValueFieldContainerTestLarge 1000"

perf stat -r 10 -e cycles,instructions,cache-misses,cache-references -d -d -d $SANITY_JDK/java -Xverify:none -XX:ValueTypeFlatteningThreshold=99999 -Xjit:count=1,disableAsyncCompilation,optlevel=hot -cp transaction-analyzer-2.1.jar:lib/* in.ac.iitmandi.compl.ValueMainMeduim 1000 > valueFieldContainerTestMeduim1000.log 2>&1

echo "Finished ValueFieldContainerTestMeduim 1000"


perf stat -r 10 -e cycles,instructions,cache-misses,cache-references -d -d -d $SANITY_JDK/java -Xverify:none -Xjit:count=1,disableAsyncCompilation,optlevel=hot -cp transaction-analyzer-2.1.jar:lib/* in.ac.iitmandi.compl.NonValueMain 10000 > nonValueFieldContainerTest10000.log 2>&1

echo "Finished NonValueFieldContainerTest 10000"


perf stat -r 10 -e cycles,instructions,cache-misses,cache-references -d -d -d $SANITY_JDK/java -Xverify:none -XX:ValueTypeFlatteningThreshold=99999 -Xjit:count=1,disableAsyncCompilation,optlevel=hot -cp transaction-analyzer-2.1.jar:lib/* in.ac.iitmandi.compl.IntermediateValueMain 10000 > iValueFieldContainerTest10000.log 2>&1

echo "Finished IValueFieldContainerTest 10000"


perf stat -r 10 -e cycles,instructions,cache-misses,cache-references -d -d -d $SANITY_JDK/java -Xverify:none -XX:ValueTypeFlatteningThreshold=99999 -Xjit:count=1,disableAsyncCompilation,optlevel=hot -cp transaction-analyzer-2.1.jar:lib/* in.ac.iitmandi.compl.ValueMain 10000 > valueFieldContainerTest10000.log 2>&1

echo "Finished ValueFieldContainerTest 10000"

perf stat -r 10 -e cycles,instructions,cache-misses,cache-references -d -d -d $SANITY_JDK/java -Xverify:none -XX:ValueTypeFlatteningThreshold=99999 -Xjit:count=1,disableAsyncCompilation,optlevel=hot -cp transaction-analyzer-2.1.jar:lib/* in.ac.iitmandi.compl.ValueMainLarge 10000 > ValueFieldContainerTestLarge10000.log 2>&1

echo "Finished ValueFieldContainerTestLarge 10000"

perf stat -r 10 -e cycles,instructions,cache-misses,cache-references -d -d -d $SANITY_JDK/java -Xverify:none -XX:ValueTypeFlatteningThreshold=99999 -Xjit:count=1,disableAsyncCompilation,optlevel=hot -cp transaction-analyzer-2.1.jar:lib/* in.ac.iitmandi.compl.ValueMainMeduim 1000 > valueFieldContainerTestMeduim1000.log 2>&1

echo "Finished ValueFieldContainerTestMeduim 10000"

