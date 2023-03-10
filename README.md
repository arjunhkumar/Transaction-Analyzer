# Transaction-Analyzer
Microbenchmark using JMH that analyzes transactions and performs various operations. The parameter of Inlining depth is considered in this branch.

Building
--------
Use mvn clean package to build the benchmark.
Ensure that the jdk is supporting value-types and -XDenablePrimitiveClasses flag is set as compiler args.

Running the experiment
----------------------
Use the value-types compliant jdk to run the generated jar file appropriate arguments.
The one used to generate results in Results dir is described in runtest-inlining-only.sh.

Results
-------
Results obtained are described in the Results directory.
